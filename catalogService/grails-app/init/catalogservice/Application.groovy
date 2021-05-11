package catalogservice

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

import groovy.transform.CompileStatic
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.client.discovery.EnableDiscoveryClient


@CompileStatic
@EnableEurekaClient
@EnableDiscoveryClient

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }
}