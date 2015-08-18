# OAuth2 Todo List API using Spring Framework

Use case to show you how to protect restricted resources via OAuth2 Protocol.

The [Authorization Server](https://github.com/tcompiegne/oauth2-server "Authorization Server") will be use as OAuth 2 Authorization Server.

You can check the [parent project](https://github.com/tcompiegne/oauth2-resource-server-samples) to get more details about the other parts of this project (OAuth2 Authorization Server and OAuth2 Client Application).

## Server side
* [Spring Boot](http://projects.spring.io/spring-boot/)
* [Spring Security OAuth2](http://projects.spring.io/spring-security-oauth/)

## Building

```
$ git clone https://github.com/tcompiegne/oauth2-resource-server-samples
$ cd oauth2-resource-server-samples/spring-todolist-api
$ mvn spring-boot:run
...
(app starts and listens on port 9001)
```
