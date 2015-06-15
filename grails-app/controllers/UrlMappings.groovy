class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"viewPodcasts")
        "/Help"(controller:"viewHelp")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
