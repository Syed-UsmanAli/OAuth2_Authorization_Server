package com.example.demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import io.restassured.module.jsv.JsonSchemaValidator.*;

import io.restassured.matcher.RestAssuredMatchers.*;



import java.util.Base64;


//@SpringBootTest(classes=OAuth2Application.class)
@WebAppConfiguration
public class RestAssuredOAuth2 {

    public static String clientId = "myappclient";
    public static String redirectUri = "http://localhost:8083";
    public static String scope = "read";
    public static String username = "user1";
    public static String password = "pass1";

    public static String encode(String str1, String str2) {
        return new String(Base64.getEncoder().encode((str1 + ":" + str2).getBytes()));
        
       
    }
    
    @Test
    public void myTest() {
    	
    	try {
    		
    		 given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200);
    		
    	}
    	catch (Exception e) {
    		
    		e.printStackTrace();
    	}
    	
    }

    public static Response getCode() {
    	
        String authorization = encode(username, password);
        
        try {

        	/*return
                given()
                        .header("authorization", "Basic " + authorization)
                        .contentType(ContentType.URLENC)
                        .formParam("response_type", "code")
                        .queryParam("client_id", clientId)
                        .queryParam("redirect_uri", redirectUri)
                        .queryParam("scope", scope).
                  when()      
                        .post("/oauth2/authorize")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();*/
        	
        	
        	
        	/*return
                    given()
                            .header("authorization", "Bearer " + authorization)
                            .contentType(ContentType.URLENC)
                            .queryParam("response_type", "code")
                            .queryParam("client_id", clientId)
                            .queryParam("redirect_uri", redirectUri)
                            .queryParam("scope", scope).
                      when()      
                            .post("/oauth2/authorize")
                            .then()
                            .statusCode(200)
                            .extract()
                            .response();*/
        			
        			return null;
            
        
       
        }
        catch ( Exception e) {
        	
        	e.printStackTrace();
        }
		return null;
        
   
    }

    public static String parseForOAuth2Code(Response response) {
        return response.jsonPath().getString("code");
    }

    @BeforeAll
    public static void setup() {
        //RestAssured.baseURI = "http://localhost:8080";
    }

  
    public void iShouldGetCode() {
    	
    	
        Response response = getCode();
        String code = parseForOAuth2Code(response);

        Assertions.assertNotNull(code);
    }
}