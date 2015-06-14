package podcasts

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.springframework.context.EnvironmentAware
import org.springframework.core.env.*

class Application extends GrailsAutoConfiguration implements EnvironmentAware {
    static void main(String[] args) {
        GrailsApp.run(Application)
    }

    @Override
    void setEnvironment(Environment env) {
        def res = "${System.getProperty('user.home')}/.grails/${env.getProperty("info.app.name")}LocalConfig.groovy" as File

        println "\n\nChecking for local config: ${res.absolutePath}"

        if(res.exists()) {
            println "Local config exists: ${res.absolutePath}"
            def config = new ConfigSlurper().parse( res.toURI().toURL() )
            env.propertySources.addFirst( new MapPropertySource("Local Config", config) )
        } else {
            println "!! Could not find local config: ${res.absolutePath}"
        }
    }
}