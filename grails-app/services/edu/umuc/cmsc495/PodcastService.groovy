package edu.umuc.cmsc495

import com.rometools.rome.feed.synd.*
import com.rometools.rome.io.SyndFeedInput
import com.rometools.modules.itunes.*
import org.xml.sax.InputSource
import grails.transaction.Transactional

@Transactional
class PodcastService {

    def updatePodcast(String url) {
        if (log.debugEnabled) log.debug "entered updatePodcast(String)"
        if (log.traceEnabled) log.trace "url: ${url}"

        def podcast = Podcast.findByUrl(url)
        if(!podcast) {
            podcast = new Podcast()
            podcast.url = url
        }

        updatePodcast(podcast)

        if (log.debugEnabled) log.debug "exit updatePodcast(String)"
    }

    def updatePodcast(Podcast podcast) {
        if (log.debugEnabled) log.debug "entered updatePodcast(Podcast)"
        if (log.traceEnabled) log.trace "podcast id: ${podcast.id} / podcast url: ${podcast.url}"

        def response = fetchPodcast(podcast)
        if(!response) {
            // no response when fetching feed, nothing to be done
            return
        }

        def feedReader = new SyndFeedInput()
        def syndFeed
        try {
            syndFeed = feedReader.build(new InputSource(response))
        } catch (Exception e) {
            log.error "error parsing feed ${podcast.url}", e
            return
        }

        podcast.title = syndFeed.title
        podcast.link = syndFeed.link
        podcast.description = syndFeed.description?.value
        podcast.image = syndFeed.image?.url

        def itunesInfo = syndFeed.getModule(ITunes.URI)
        if(itunesInfo) {
            podcast.subtitle = itunesInfo.subtitle
            podcast.summary = itunesInfo.summary
            podcast.author = itunesInfo.author
            podcast.publisherName = itunesInfo.ownerName
            podcast.publisherEmail = itunesInfo.ownerEmailAddress

            if(itunesInfo.image) {
                podcast.image = itunesInfo.image.toString()
            }
        }

        if(!podcast.save()) {
            log.error "error saving podcast ${podcast.url}: ${podcast.errors.allErrors.join('\n')}"
            return
        }

        syndFeed.entries.each {feedEntry ->
            if(!feedEntry.uri) return // doesn't make sense to save if no URI, these are unique identifiers

            def entry = Entry.findByPodcastAndGuid(podcast, feedEntry.uri)
            if(!entry) {
                entry = new Entry()
                entry.podcast = podcast
                entry.guid = feedEntry.uri
            }
            entry.publishedDate = feedEntry.publishedDate
            entry.title = feedEntry.title
            entry.description = feedEntry.description?.value?.trim()

            println "${feedEntry.uri} / ${feedEntry.description.type} / ${feedEntry.description.value.length()}"

            entry.link = feedEntry.link

            if(feedEntry.enclosures) {
                entry.file = feedEntry.enclosures[0].url
            }

            def itunesEntryInfo = feedEntry.getModule(ITunes.URI)
            if(itunesEntryInfo) {
                entry.summary = itunesEntryInfo.summary
                entry.duration = itunesEntryInfo.duration ? itunesEntryInfo.duration.milliseconds : 0
            }

            if(!entry.save()) {
                log.error "error saving ${entry.guid} for ${podcast.url}: ${entry.errors.allErrors.join('\n')}"
            }
        }

        if (log.debugEnabled) log.debug "exit updatePodcast(Podcast)"
    }

    private def fetchPodcast(Podcast podcast) {
        def url = podcast.url.toURI().toURL()
        def conn = url.openConnection()

        if(podcast.lastModified) {
            conn.setRequestProperty('If-Modified-Since', podcast.lastModified)
        }

        if(podcast.lastEtag) {
            conn.setRequestProperty('If-None-Match', podcast.lastEtag)
        }

        conn.connect()

        def responseCode = conn.responseCode
        if (log.debugEnabled) log.debug "responseCode: ${responseCode}"
        if(responseCode != conn.HTTP_OK) {
            log.error "error requesting ${podcast.url}: ${conn.responseMessage}"
            return null
        }

        podcast.lastModified = conn.getHeaderField('Last-Modified')
        podcast.lastEtag = conn.getHeaderField('ETag')

        return conn.content
    }
}
