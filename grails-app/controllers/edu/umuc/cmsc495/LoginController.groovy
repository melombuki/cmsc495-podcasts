package edu.umuc.cmsc495

class LoginController {

    def index() {

    }

    def auth() {
        if(!params.email) {
            flash.error = "Please enter an email address."
            redirect(action:'index')
            return
        }

        def user = User.findByEmail(params.email)
        if(!user || !user.isPasswordEqual(params.password)) {
            flash.error = "No account found for that email/password."
            redirect(action:'index')
            return
        }

        session.user = user.email

        redirect(uri:'/')
    }
}
