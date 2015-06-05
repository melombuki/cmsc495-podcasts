package edu.umuc.cmsc495

class UpdateAllFeedsJob {

    static triggers = {
    	cron name:'updateAllFeeds', cronExpression: ''0 0 */4 * * ?' // run every 4 hours
    }

    def execute() {
        if (log.debugEnabled) log.debug "entered UpdateAllFeedsJob.execute()"

        // Update all poscast feeds
        def allPodcasts = Podcast.list()
        allPodcasts.each {
            podcastService.updatePodcast(it)
        }

        if (log.debugEnabled) log.debug "exit UpdateAllFeedsJob.execute()"
    }
}
