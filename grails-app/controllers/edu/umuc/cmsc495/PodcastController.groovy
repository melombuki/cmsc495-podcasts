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

        // some basic URL validation
        try {
            def url = new URL(podcastUrl)
            if(!['http', 'https'].contains(url.getProtocol())) {
                throw new IllegalArgumentException("URL (${podcastUrl} is not HTTP or HTTPS")
            }
        } catch (e) {
            log.error e.message, e
            flash.error = "Could not add new podcast subscription, ${podcastUrl} is not a valid URL."
            redirect(action:'list')
            return
        }

        def podcast = Podcast.findByUrl(podcastUrl)

        // Add the podcast to the database if it doesn't exist
        if(!podcast) {
            try {
                podcast = podcastService.updatePodcast(podcastUrl)
            } catch (e) {
                log.error e.message, e
                flash.error = "Could not add new podcast subscription, ${podcastUrl} is not a valid Podcast."
                redirect(action:'list')
                return
            }
        }

        // If the podcast was not created silently, then bail
        if(podcast.id == null) {
            flash.error = "Could not add new podcast subscription, ${podcastUrl} is not a valid Podcast."
            redirect(action:'list')
            return
        }

        def user = User.findByEmail(session.user)

        println "Podcast.id = ${podcast?.id}"

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

        def podcastID = subscription.podcast.id

        subscription.delete(flush: true)

        def allSubscribedPodcasts = Subscription.list().collect {it.podcast.id}

        // Check for any remaining subscriptions, delete podcast if none
        if(!allSubscribedPodcasts.contains(podcastID)) {
            def podcastToDelete = Podcast.findById(podcastID)
            podcastToDelete.entries*.delete()
            podcastToDelete.delete()
        }

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
        def currentUser = User.findByEmail(session.user)
        def allSubscriptions = currentUser.subscriptions

        allSubscriptions.each{
            podcastService.updatePodcast(it.podcast)
            println "Updated " + it.podcast.title

        }

        flash.message = "Updated subscriptions successfully."
        redirect(action: 'list')
    }
}