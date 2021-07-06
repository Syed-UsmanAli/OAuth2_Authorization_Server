package com.example.demo;

import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
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


/**
 * 
 * @author syedusman
 * 
 * Junit class to test the grant_type=password workflow of OAuth2
 *
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes=OAuth2Application.class)
class OAuth2ApplicationTests {
	
	@Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
          .addFilter(springSecurityFilterChain).build();
    }
    
    @Test
    public void getAccessToken() throws Exception {
    	
        final String accessToken =  obtainAccessToken("user1","pass1");
        assertNotNull(accessToken);
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
        
        assertNotNull(jsonParser.parseMap(resultString).get("access_token").toString());
        
        
        return jsonParser.parseMap(resultString).get("access_token").toString();
        
        }
        catch ( Exception e) {
        	
        	e.printStackTrace();
        }
        
        return null;
        
    }
    
    

	
	
	
   

}
