package com.example.demo;

import java.util.Base64;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.web.WebAppConfiguration;

import  io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
    
    //@Test
    public void myTest() {
    	
    	try {
    		
    		RestAssured.given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200);
    		
    	}
    	catch (Exception e) {
    		
    		e.printStackTrace();
    	}
    	
    }

    public static Response getCode() {
    	
        String authorization = encode(username, password);
        
        
        
        
        try {
        	


        	RestAssured.baseURI = "http://localhost:8080";
        	      
        	
        	return
        			RestAssured.given()
                            .header("authorization", "Basic " + authorization)
                            .contentType(ContentType.URLENC)
                            .formParam("response_type", "code")
                            .queryParam("client_id", clientId)
                            .queryParam("redirect_uri", redirectUri)
                            .queryParam("scope", scope)
                            .post("/oauth2/authorize")
                            .then()
                            .statusCode(200)
                            .extract()
                            .response();
        	
        	

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
        			//	return null;
            
        
       
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
        RestAssured.baseURI = "http://localhost:8080";
      
    }

   // @Test
    public void iShouldGetCode() {
    	
    	
        Response response = getCode();
        
    
        String code = parseForOAuth2Code(response);

        Assertions.assertNotNull(code);
    }
}