# oauth2-resource-server-samples
OAuth2 Resource Server samples

Use cases to show you how to protect restricted resources via OAuth2 Protocol.

The [Authorization Server](https://github.com/tcompiegne/oauth2-server "Authorization Server") will be use as OAuth 2 Authorization Server.

About TodoList OAuth 2 API
=================================

Retrieve your Todo List with the TodoList API.

The goal is to implement the same application (API RESTful) using different technologies to show you how you can protected your resources thanks to OAuth2.

[OAuth 2 Client Apps](https://github.com/tcompiegne/oauth2-client-samples) are used to retrieve the data and [OAuth 2 Server](https://github.com/tcompiegne/oauth2-server) is used for the OAuth2 Authentication Server.

How to use it ?
=================================

1 . First of all checkout my other projects to retrieve and run the OAuth2 Authorization Server :

<pre>
- git clone https://github.com/tcompiegne/oauth2-server.git
- cd oauth2-server
- mvn jetty:run (listen on port 8080)
</pre>

2 . Build and run the OAuth2 Resource Server

Choose a specific project and follow the instructions in README file to get ready.

Get your data !
=================================

<pre>
Get All Todo :

=============== =================================================
Request         GET /rest/todos
Header          Authorization   Bearer cb86832e-5c0f-4480-bcc9-be0daddd7725 (i.e your access token)
Response Codes  200 OK
                401 Unauthorized
Response Body   ::

                  [
                      {
                          "id": 1,
                          "description": "Todo 1",
                          "username": "userTest"
                      },
                      {
                          "id": 2,
                          "description": "Todo 2",
                          "username": "userTest"
                      },
                      {
                          "id": 3,
                          "description": "Todo 3",
                          "username": "userTest"
                      }
                  ]

=============== =================================================

Get User Todo list :

=============== =================================================
Request         GET /rest/todos/|username|
Header          Authorization   Bearer cb86832e-5c0f-4480-bcc9-be0daddd7725 (i.e your access token)
Response Codes  200 OK
                401 Unauthorized
Response Body   ::

                  [
                      {
                          "id": 1,
                          "description": "Todo 1",
                          "username": "userTest"
                      },
                      {
                          "id": 2,
                          "description": "Todo 2",
                          "username": "userTest"
                      },
                      {
                          "id": 3,
                          "description": "Todo 3",
                          "username": "userTest"
                      }
                  ]

=============== =================================================

Add Todo

=============== =================================================
Request         POST /rest/todos/add
Headers         Authorization   Bearer cb86832e-5c0f-4480-bcc9-be0daddd7725 (i.e your access token)
                Content-Type    application/json
Request Body    ::

                  {
                    "username" : "userTest",
                    "description" : "Todo 4"
                  }
                  
Response Codes  200 OK
                401 Unauthorized
Response Body   ::

                  {
                      "id": 4,
                      "description": "Todo 4",
                      "username": "userTest"
                  }

=============== =================================================

Delete Todo

=============== =================================================
Request         POST /rest/todos/|todo_id|/delete
Headers         Authorization   Bearer cb86832e-5c0f-4480-bcc9-be0daddd7725 (i.e your access token)
Response Codes  200 OK
                401 Unauthorized
Response Body   ::

                "Todo with id |todo_id| successfully deleted"

=============== =================================================

</pre>

Community
===================================

I hope you will enjoy my work, any feedbacks will be grateful
