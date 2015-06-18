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

    def create() {
        def user = new User()
        [user:user]
    }

    def save() {
        def user = new User()
        user.email = params.email
        user.password = params.password

        if(params.password != params.password2) {
            user.errors.rejectValue('password', 'user.password.dont.match', "Your passwords do not match, please try again.")
        }

        // NPE bug to work around: https://github.com/grails/grails-core/issues/698
        if(user.hasErrors() || !user.validate()) {
            render(view:'create', model:[user:user])
            return
        }
        user.save()

        redirect(action:'index')
    }
}
