package edu.umuc.cmsc495

class Podcast {

    static hasMany = [entries:Entry]

    static constraints = {
        url unique: true
        subtitle nullable:true
        lastModified nullable:true
        lastEtag nullable:true
        author nullable:true
        publisherName nullable:true
        publisherEmail nullable:true
        image nullable:true
        summary nullable:true
        description nullable:true
    }

    static mapping = {
        summary sqlType: 'longtext'
        description sqlType: 'longtext'
    }

    String url
    String title
    String subtitle
    String description
    String summary
    String link
    String image
    String author
    String publisherName
    String publisherEmail

    // to keep track of when to update
    String lastModified
    String lastEtag

    Date dateCreated
    Date lastUpdated
}
