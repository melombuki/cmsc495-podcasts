package edu.umuc.cmsc495

class UpdateOutdatedFeedsJob {
    def podcastService

    static int hoursUpdateInterval = 12 // number of hours before feed is "out of date"

    static triggers = {
    }

    def execute(context) {
        if (log.debugEnabled) log.debug "entered UpdateOutdatedFeedsJob.execute()"

        // Get the current time
        def now = new Date()

        // Get a list of the user's subscriptions
        def email = context.mergedJobDataMap.get('email')

        def user = User.findByEmail(email)

        // Add the Podcast URLs to the list
        def allPodcasts = user.subscriptions*.podcast

        // Call update on each podcast
        def timeDifference // time in milliseconds
        allPodcasts.each {
            timeDifference = now.time - it.lastUpdated.time // using last time the database record was updated
            if (timeDifference >= hoursToMillis(hoursUpdateInterval)) {
                podcastService.updatePodcast(it)
            }
            println "Updated: ${it.url}"
        }

        if (log.debugEnabled) log.debug "exit UpdateOutdatedFeedsJob.execute()"
    }

    // Returns the number of milliseconds in the parameter hours
    private static long hoursToMillis(long hours) {
        return hours * 60 * 60 * 1000
    }
}
