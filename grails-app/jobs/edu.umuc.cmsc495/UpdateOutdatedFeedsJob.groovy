package edu.umuc.cmsc495

class UpdateOutdatedFeedsJob {
    static triggers = {
        // Triggers update 0.5 seconds after start and never repeats
        simple name:'updateOutdatedFeedsTrigger', startDelay:500, repeatCount: 0

        // Triggers update every 12 hours after the app is started (or any length of time)
        //cron name:'updateDailyTrigger', startDelay:0, cronExpression: '0 0 12 1/1 * ? *'
    }

    def execute() {
        if (log.debugEnabled) log.debug "entered UpdateOutdatedFeedsJob.execute()"

        // Get the current time
        def now = new Date()

        // Update poscast feeds with lastModified older than 12 hours
        def allPodcasts = Podcast.list()
        def timeDifference
        allPodcasts.each {
            timeDifference = now.getTime() - new Date(it.lastModified).getTime()
            if (timeDifference >= hoursToMillis(12)) {
                podcastService.updatePodcast(it)
            }
        }

        if (log.debugEnabled) log.debug "exit UpdateOutdatedFeedsJob.execute()"
    }

    // Returns the number of milliseconds in the parameter hours
    private long hoursToMillis(long hours) {
        return hours * 60 * 60 * 1000
    }
}
