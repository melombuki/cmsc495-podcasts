import edu.umuc.cmsc495.*

println "Starting data load"

def podcasts = [
    'http://atp.fm/episodes?format=rss', 
    'http://www.relay.fm/connected/feed', 
    'http://www.relay.fm/rocket/feed', 
    'http://www.relay.fm/rd/feed', 
    'http://feeds.twit.tv/mbw.xml', 
    'http://feeds.soundcloud.com/users/soundcloud:users:38128127/sounds.rss', 
    'http://www.theskepticsguide.org/feed/', 
    'http://feeds.feedburner.com/debugshow', 
    'http://feeds.feedburner.com/meltonshow', 
    'http://daringfireball.net/thetalkshow/rss'
]

def podcastService = ctx.podcastService

println "loading podcasts..."
podcasts.each { podcast ->
    podcastService.updatePodcast(podcast)
}


def users = [
    'tom.comer@gmail.com',
    'melombuki@gmail.com',
    'bnjmnsims@gmail.com',
]

User.withNewSession {session ->
    users.each { email ->
        def user = User.findByEmail(email)
        if(!user) {
            user = new User()
            user.email = email
            user.password = 'passwd'
            user.save()
        }
    }
}

def subscriptions = [
    [0, 1, 3, 7],
    [0, 2, 4, 5],
    [0, 8, 9]
]

Subscription.withNewSession {session ->
    subscriptions.eachWithIndex { indicies, userIndex ->
        def email = users[userIndex]
        def user = User.findByEmail(email)
        
        indicies.each {index ->
            def url = podcasts[index]
            def podcast = Podcast.findByUrl(url)
            
            def subscription = new Subscription()
            subscription.user = user
            subscription.podcast = podcast
            subscription.save()
        }
    }
}

return null