package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
* @author syedusman
* 
* This is a demo application to show case a lightweight OAuth 2.0 authorization server.
* Following  functionalities has been implemented
* 
* - Authorization endPoint to get a client's authorization request and validate it.
* - Built in Spring Boot Login Form to authenticate user
* - Obtain an authorization decision from the resource owner after a successful authentication.
* - When the access request is granted, redirect the user-agent to the provided
*   client redirect URI and provide an authorization code to the client application.
* - Implement a token endPoint. Upon receiving an access token request, 
*   validate client's credentials and the request itself.
* - When the access token request is valid and authorized, 
*   return an access token to the client application.
* 
* 
*/
@SpringBootApplication
public class OAuth2Application {

	/**
	 * 
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(OAuth2Application.class, args);
	}

}


/*g */