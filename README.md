# demo-reactive

## How to build, deploy and run

Project is using MySQL database with configuration (hardcoded in Java files):
```
address:  localhost:3306
database: test01122017
username: test01122017
password: test01122017
```

You need to start mysql with database first. Then go to [JLupin Next Server download page](https://jlupinnextserver.io/download) to get server. Run it (go to `server/start` directory and run `./start.sh` or `./start.cmd`) and then use maven command to build and deploy example `mvn clean package jlupin-next-server:deploy`. To check on which port microservice is listening run command `./control.sh show servicePort users` from `server/start` directory. Project is configured for server version 1.4.0.4.

## Basic information

Project contains one microservice named `users` written using Spring Boot 2. It exposes two groups of endpoints: one is using standard controllers and one is using new reactive features.

Endpoints list:
```
Standard:
  GET /info
  POST   /person/create (used by frontend application written with Angular)
  GET    /person/find/{id}
  DELETE /person/delete/{id}
  GET    /person/list
Reactive:
  GET    /reactive/info
  POST   /reactive/person/create (used by frontend application written with Angular)
  GET    /reactive/person/find/{id}
  DELETE /reactive/person/delete/{id}
  GET    /reactive/person/list (used by frontend application written with Angular) -> it is configured to simulate long computations and returs one record every 3 seconds
```

Microservice also serves very simple Angular application on root location (http://localhost:[servicePort]/). You can add new users here and list them (note that there is added long computations simulation so records will be shown one by one very 3 senods).

Also example Java client application is added in module `users/client`.
