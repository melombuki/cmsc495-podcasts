package podcasts

import edu.umuc.cmsc495.User

class SettingsController {
    def index() {
    }

    def updateEmail(){

        def user = User.findByEmail(session.user)

        user.email = params.email
        user.save()

        redirect(action: 'index')
    }

    def updatePassword(){

        def user = User.findByEmail(session.user)

        if(params.password1 != params.password2) {
            user.errors.reject('user.password.dont.match', "Your passwords do not match, please try again.")
            user.errors.rejectValue('password', 'user.password.dont.match', "Your passwords do not match, please try again.")
        }
        else{
            user.password = params.password
            user.save()

            redirect(uri: 'index')
        }

    }
}
