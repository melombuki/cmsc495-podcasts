package edu.umuc.cmsc495

import java.text.NumberFormat

class PodcastTagLib {
    static namespace = "podcast"

    private static def secsInHour = 3600
    private static def secsInMinute = 60

    //static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def isLoggedIn = {attrs, body ->
        if(session.user) {
            out << body()
        }
    }

    def duration = { attrs, body ->
        if(!attrs.entry) {
            throw IllegalArgumentException("Podcast duration tag requires an entry object to be passed in")
        }

        def entry = attrs.entry
        def duration = (entry.duration / 1000).toInteger() // convert to seconds

        def hours = (duration / secsInHour).toInteger()
        def minutes = (duration / secsInMinute).toInteger() % secsInMinute
        def seconds = (duration % secsInHour).toInteger()

        def formatter = NumberFormat.getInstance()
        formatter.maximumFractionDigits = 0
        formatter.maximumIntegerDigits = 2
        formatter.minimumIntegerDigits = 2

        out << "${hours}:${formatter.format(minutes)}:${formatter.format(seconds)}"
    }
}
