package edu.umuc.cmsc495

class Entry {

    static belongsTo = [podcast:Podcast]

    static constraints = {
        guid unique: true
        publishedDate nullable:true
        summary nullable:true
        description nullable:true
    }

    static mapping = {
        summary sqlType: 'longtext'
        description sqlType: 'longtext'
    }

    String guid
    String link
    String title
    String file
    String summary
    String description
    Long duration = 0
    Date publishedDate

    Date dateCreated
    Date lastUpdated
}
