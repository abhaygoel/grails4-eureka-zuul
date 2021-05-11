package com.example.zuulproxyspringboot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableEurekaClient
class ZuulProxySpringBootApplication {

    static void main(String[] args) {
        SpringApplication.run(ZuulProxySpringBootApplication, args)
    }

}
