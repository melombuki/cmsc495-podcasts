package edu.umuc.cmsc495

import java.security.MessageDigest

class User {

    static hasMany = [subscriptions:Subscription]

    static constraints = {
        email unique:true
    }

    String email
    String password

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    def beforeInsert() {
        encodePassword()
    }

    private String encodePassword() {
        password = encodePassword(password)
    }

    private String encodePassword(str) {
        def messageDigest = MessageDigest.getInstance("SHA-512")
        messageDigest.reset()
        messageDigest.update(str.bytes)
        def digest = messageDigest.digest()
        return digest.encodeBase64().toString()
    }

    def isPasswordEqual(passwd) {
        return password == encodePassword(passwd)
    }

}
