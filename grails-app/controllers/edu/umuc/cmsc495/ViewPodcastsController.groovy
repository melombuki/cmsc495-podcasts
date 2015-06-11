package edu.umuc.cmsc495

class ViewPodcastsController {

    def index() {
        if(!session.user) {
            println 'No user in session.'
            redirect(uri:'/')
        }

        // Get the current users
        def currentUser = User.findByEmail(session.user)

        // Get a list of the user's subscriptions
        def subscriptions = Subscription.findAllByUser(currentUser)

        // Add the Podcast URLs to the list
        def podcasts = []
        subscriptions.each {
            podcasts << Podcast.findById(it.id)
        }

        [ podcasts: podcasts ]
    }
}