package edu.umuc.cmsc495

class LogoutController {

    def index() {
        session.invalidate()
        redirect(uri: '/')
    }
}
