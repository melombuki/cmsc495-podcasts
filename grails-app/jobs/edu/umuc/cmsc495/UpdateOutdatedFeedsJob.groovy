package edu.umuc.cmsc495

class UpdateOutdatedFeedsJob {
    static int hoursUpdateInterval = 12 // number of hours before feed is "out of date"

    static triggers = {
    }

    def execute(context) {
        if (log.debugEnabled) log.debug "entered UpdateOutdatedFeedsJob.execute()"

        // Get the current time
        def now = new Date()

        //def allPodcasts = Podcast.list()

        // Update poscast feeds with lastModified older than 12 hours
        def podcastService = new PodcastService()

        // Get a list of the user's subscriptions
        def email = context.mergedJobDataMap.get('email')

        println email

        def user = User.findByEmail(email)

        def subscriptions = Subscription.findAllByUser(user)

        // Add the Podcast URLs to the list
        def allPodcasts = []
        subscriptions.each {
            allPodcasts << Podcast.findById(it.id)
        }

        // Call update on each podcast
        def timeDifference // time in milliseconds
        allPodcasts.each {
            timeDifference = now.getTime() - it.lastUpdated.time // using last time the database record was updated
            if (timeDifference >= hoursToMillis(hoursUpdateInterval)) {
                podcastService.updatePodcast(it)
            }
        }

        if (log.debugEnabled) log.debug "exit UpdateOutdatedFeedsJob.execute()"
    }

    // Returns the number of milliseconds in the parameter hours
    private static long hoursToMillis(long hours) {
        return hours * 60 * 60 * 1000
    }
}
