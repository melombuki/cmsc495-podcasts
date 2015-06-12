package edu.umuc.cmsc495

class PodcastTagLib {
    static namespace = "podcast"

    //static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def isLoggedIn = {attrs, body ->
        if(session.user) {
            out << body()
        }
    }
}
