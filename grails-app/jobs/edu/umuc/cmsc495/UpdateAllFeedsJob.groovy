package edu.umuc.cmsc495

class UpdateAllFeedsJob {
    def podcastService

    static triggers = {
    	cron name:'updateAllFeeds', cronExpression: '0 0 */4 * * ?' // run every 4 hours
    }

    def execute() {
        boolean isUsed = false
        if (log.debugEnabled) log.debug "entered UpdateAllFeedsJob.execute()"

        // Update all poscast feeds
        def allSubscriptions = Subscription.list()
        def allPodcasts = Podcast.list()
        allPodcasts.each {
            def currentPodcast = it

            allSubscriptions.each {
                if (it.podcast.is(currentPodcast)) {
                    podcastService.updatePodcast(it)
                    isUsed = true
                }
            }
            if (!isUsed){
                println it.title + " was deleted."
                currentPodcast.delete()
            }
            isUsed = false
        }

        if (log.debugEnabled) log.debug "exit UpdateAllFeedsJob.execute()"
    }
}
