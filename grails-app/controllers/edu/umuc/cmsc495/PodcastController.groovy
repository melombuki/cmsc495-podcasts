package edu.umuc.cmsc495

class PodcastController {
    def podcastService
    def updateAllFeedsJob
    static defaultAction = "list"

    def list() {
        // Get the current users
        def currentUser = User.findByEmail(session.user)

        [ subscriptions: currentUser.subscriptions.sort {it.podcast.title} ]
    }

    def add() {
        if(!params.podcastUrl) {
            redirect(action:'list')
            return
        }
        def podcastUrl = params.podcastUrl

        //TODO: Validate the podcast URL

        def podcast = Podcast.findByUrl(podcastUrl)

        // Add the podcast to the database if it doesn't exist
        if(!podcast) {
            podcastService.updatePodcast(podcastUrl)
            podcast = Podcast.findByUrl(podcastUrl)
        }

        def user = User.findByEmail(session.user)

        println "Podcast.id = ${podcast.id}"

        // Prevent subscribing to the same podcast multiple times
        def podcastId = user.subscriptions.find() { it.podcast.id == podcast.id }
        if (!podcastId) {
            def subscription = new Subscription()
            subscription.user = user
            subscription.podcast = podcast
            subscription.save()
        }

        redirect(action:'list')
    }

    def delete() {
        def subscription = getSubscription()
        if(!subscription) {
            return
        }

        subscription.delete()
        redirect(action:'list')
    }

    def show() {
        def subscription = getSubscription()
        if(!subscription) {
            return
        }

        [subscription:subscription]
    }

    private getSubscription() {
        if(!params.id) {
            redirect(action:'list')
            return null
        }
        def subscription = Subscription.get(params.id)
        if(!subscription) {
            redirect(action: 'list')
            return null
        }

        return subscription
    }

    def play() {
        def subscription = getSubscription()
        if(!subscription) {
            return
        }

        def entry = subscription.podcast.entries.find { it.id == params.entry?.toLong() }
        if(!entry) {
            redirect(action:'show', id:subscription.id)
            return
        }

        [subscription: subscription, entry:entry]
    }

    def updateSubscriptions(){
        def allPodcasts = Podcast.list()

        allPodcasts.each{
            podcastService.updatePodcast(it)
            println "Updated " + it.title

        }

        flash.message = "Updated subscriptions successfully."
        redirect(action: 'list')
    }
}