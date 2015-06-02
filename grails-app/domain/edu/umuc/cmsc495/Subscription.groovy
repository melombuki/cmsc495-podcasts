package edu.umuc.cmsc495

class Subscription {

    static belongsTo = [user:User]

    static constraints = {
    }

    Podcast podcast
}
