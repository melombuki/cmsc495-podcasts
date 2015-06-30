package podcasts

import edu.umuc.cmsc495.User

class SettingsController {
    def index() {
        return [user:User.findByEmail(session.user)]
    }

    def updateEmail() {
        def user = User.findByEmail(session.user)
        user.email = params.email

        if(!saveUser(user)) {
            return
        }

        // we updated the user's email address, so we need to update it in the session as well
        // so that we can still look it up on other pages
        session.user = user.email

        flash.message = "Email address updated successfully."
        redirect(action: 'index')
    }

    def updatePassword(){
        def user = User.findByEmail(session.user)
        if(params.password != params.password2) {
            user.errors.rejectValue('password', 'user.password.dont.match', "Your passwords do not match, please try again.")

            render(view:'index', model:[user:user])
            return
        }  else {
            user.password = params.password
            if(!saveUser(user)) {
                return
            }
        }

        flash.message = "Password address updated successfully."
        redirect(action: 'index')
    }

    private def saveUser(User user) {
        if(!user.validate() || !user.save()) {
            render(view:'index', model:[user:user])
            return false
        }
        return true
    }

    def askUser(){
        render(view:'deleteAccount')
    }

    def deleteUser(){
        def user = User.findByEmail(session.user)
        user.delete()
        session.invalidate()
        redirect(uri: '/')

    }
}
