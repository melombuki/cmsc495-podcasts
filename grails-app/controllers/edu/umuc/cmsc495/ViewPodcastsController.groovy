package edu.umuc.cmsc495

class ViewPodcastsController {

    def index() {
        // Get the current users
        def currentUser = User.findByEmail(session.user)

        [ podcasts: currentUser.subscriptions*.podcast.sort {it.title} ]
    }
}