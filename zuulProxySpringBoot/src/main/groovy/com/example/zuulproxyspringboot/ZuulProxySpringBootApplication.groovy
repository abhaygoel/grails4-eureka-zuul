package com.example.zuulproxyspringboot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
class ZuulProxySpringBootApplication {

    static void main(String[] args) {
        SpringApplication.run(ZuulProxySpringBootApplication, args)
    }

}
