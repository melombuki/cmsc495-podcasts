class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"podcast")
        "/help"(controller:"help")
        "/settings"(controller:"settings")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
