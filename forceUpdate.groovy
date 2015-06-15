package edu.umuc.cmsc495

Podcast.withNewSession {session ->
    Podcast.list().each {
        it.lastEtag = null
        it.lastModified = null
        ctx.podcastService.updatePodcast(it)
    }
}

return null