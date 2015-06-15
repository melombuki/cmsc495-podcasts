package edu.umuc.cmsc495

class PodcastController {
    static defaultAction = "list"

    def list() {
        // Get the current users
        def currentUser = User.findByEmail(session.user)

        [ subscriptions: currentUser.subscriptions.sort {it.podcast.title} ]
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
}