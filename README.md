Spring-jwt
==========================

Spring boot web using spring security with JWT.

Used APIs,

- Java 8
- Spring boot 2.1.1.RELEASE
- Spring Security 5.1.2.RELEASE
- RestAPI
- H2 database

how to use,

- 1 POST https://spring-jwt-dev.herokuapp.com/register {username, admin} to save new account
- 2 GET https://spring-jwt-dev.herokuapp.com/authenticate {username, admin} to connect using the new account and sore the token from the response body
- 3 GET https://spring-jwt-dev.herokuapp.com/futures {authorisation_headr, jwt}  to get futures list
- 4 GET https://spring-jwt-dev.herokuapp.com/futures/{ticker} {authorisation_headr, jwt}  to get a future list

You can visit the live version via [Spring-jwt](https://spring-jwt-dev.herokuapp.com//).


