package com.example.demo;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes=OAuth2Application.class)
public class OAuth2AuthCodeTests {
	

	
	@Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;
    

    public static String clientId = "myappclient";
    public static String redirectUri = "http://localhost:8083";
    public static String scope = "read";
    public static String username = "user1";
    public static String password = "pass1";
    

   // @Before
    public void setup() {
      
    }
    
    
   // @Test
    public void iShouldGetCode() {
    	
        String code = getCode();
        

        Assertions.assertNotNull(code);
    }
    
    
    
	public String getCode() {
	        	
	        	
	        	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
	      	          .addFilter(springSecurityFilterChain).build();
	      	 
	      	 
	          MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	          params.add("response_type", "code");
	          params.add("client_id", "myappclient");
	          params.add("scope", "read");
	          params.add("redirectUri", "http://localhost:8083");
	         
	       

	          ResultActions result;
			try {
				result = mockMvc .perform(post("/oauth/authorize")
	              .params(params)
	              .with(httpBasic("myappclient","myappsecret"))
	              .accept("application/json;charset=UTF-8"));
	              //.andExpect(status().isOk());
	             // .andExpect((ResultMatcher) content().contentType("application/x-www-form-urlencoded"));
			
	          
	             
	          String resultString = result.andReturn().getResponse().getContentAsString();

	          JacksonJsonParser jsonParser = new JacksonJsonParser();
	          
	          return jsonParser.parseMap(resultString).get("code").toString();
	          
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	          
	          
	          return null;
	   
	    }
	    
    
  
    
    
    
   /* @Test
    public void getAccessToken() throws Exception {
    	
        final String accessToken =  obtainAccessToken("user1","pass1");
        System.out.println(accessToken);
       // log().info("access_token={}", accessToken);
    }
    
    
    public String obtainAccessToken(String username, String password) throws Exception {
    	
    	 this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
    	          .addFilter(springSecurityFilterChain).build();
    	 
    	 
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", "myclient");
        params.add("username", username);
        params.add("password", password);
        
        try {

        ResultActions result 
          = mockMvc.perform(post("/oauth/token")
            .params(params)
            .with(httpBasic("myclient","mysecret"))
            .accept("application/json;charset=UTF-8"))
            .andExpect(status().isOk())
            .andExpect((ResultMatcher) content().contentType("application/json;charset=UTF-8"));
        
           
        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        
        
        return jsonParser.parseMap(resultString).get("access_token").toString();
        
        }
        catch ( Exception e) {
        	
        	e.printStackTrace();
        }
        
        return null;
        
    }*/
    
    

	//@Test
	void contextLoads() {
	}
	
	
   

}
