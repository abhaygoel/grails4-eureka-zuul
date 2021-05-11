This project was created as a Poc for our attempt to migrate legacy Grails 2.x apps to a newer microservices architecture using:
* Grails 4 for microservices
* Spring Security and Security Rest 
* Eureka for service discovery built on Springboot
* Zuul as a Gateway/service Proxy built on springboot

The idea was to have authentication and authorization in a single service and use JWT. Since the architecture of Spring Security Rest is such that for JWT it just validates the token signature and creates a user object from details in the token.

It works well in our scenario where we have included authentication in one microservice. This service generates the token which is used across different micro-services.

There are two app services:
1. Book Store
2. Catalog

Each of the app services register themselves in Eureka using the application name as defined in the spring block:
1. book-store
2. catalog

```
spring:
    application:
        name: book-store
        
eureka:
    client:
        registryFetchIntervalSeconds: 5
        serviceUrl:
            defaultZone: http://10.0.0.8:8761/eureka/
    instance:
        preferIpAddress: true
        leaseRenewalIntervalInSeconds: 10
        metadataMap:
            instanceId: ${spring.application.name}:${spring.application.instance_id}:${random.int}
```

Zuul uses the discovery client to to route incoming requests based on service names. Routes are specified in application.yml

```
zuul:
    sensitiveHeaders: Cookie,Set-Cookie
    routes:
        book-store: /book-store/**
        catalog: /catalog/**
```

***How to Test?***

Use the following curl commands to test:

***Direct Without Proxy***

- Login (This returns an access token to be used in subsequent requests)
```
curl -X POST -H 'Content-type: application/json' -H 'Accept: application/json' --data '{"username":"me", "password":"password"}' http://localhost:8091/api/login
```

- Create a new Book

```
curl -X POST -H 'Content-type: application/json' -H 'Accept: application/json' -H 'Authorization: Bearer {access token}' --data '{"title":"New Book1","isbn":"isbn002","pages":210}' http://localhost:8091/book/save
```

- List Books

```
curl -X GET -H 'Content-type: application/json' -H 'Accept: application/json' -H 'Authorization: Bearer {access token}' http://localhost:8091/book/index
```

- Create New Author

```
curl -X POST -H 'Content-type: application/json' -H 'Accept: application/json' -H 'Authorization: Bearer {access token}' --data '{"name":"New Author","surname":"last name"}' http://localhost:8092/author/save
```

- List Author
```
curl -X GET -H 'Content-type: application/json' -H 'Accept: application/json' -H 'Authorization: Bearer {access token}' http://localhost:8092/author/index
```

***Zuul API Gateway***

- Zuul is running on port 8765
- Use the following syntax to request services via the API Gateway
```
curl -X <METHOD> -H 'Content-type: application/json' -H 'Accept: application/json' -H 'Authorization: Bearer {access token}' http://localhost:8765/<Service Name>/<controller>/<action>
```


