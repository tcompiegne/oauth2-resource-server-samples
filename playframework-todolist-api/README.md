# OAuth2 Todo List API using Play Framework

Use case to show you how to protect restricted resources via OAuth2 Protocol.

The [Authorization Server](https://github.com/tcompiegne/oauth2-server "Authorization Server") will be use as OAuth 2 Authorization Server.

You can check the [parent project](https://github.com/tcompiegne/oauth2-resource-server-samples) to get more details about the other parts of this project (OAuth2 Authorization Server and OAuth2 Client Application).

## Server side
* [Play Framework 2.3.8](https://www.playframework.com/)

## Building

```
$ git clone https://github.com/tcompiegne/oauth2-resource-server-samples
$ cd oauth2-resource-server-samples/playframework-todolist
$ activator "~run 9001"
...
(app starts and listens on port 9001)
browse the following url http://localhost:9001 and click on "apply this script"
```
