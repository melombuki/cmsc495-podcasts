package edu.umuc.cmsc495


class SecurityInterceptor {
    int order = HIGHEST_PRECEDENCE

    SecurityInterceptor() {
        matchAll().excludes(controller:'login').excludes(uri: "/assets/**")
    }

    boolean before() {
        if(!session.user) {
            // this can be fixed for grails 3.0.2, see https://github.com/grails/grails-core/issues/600
            //redirect(controller: 'login')

            // this can be removed for grails 3.0.2
            response.setHeader("Refresh", "0; url=/login")
            return false
        }
        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }

}
