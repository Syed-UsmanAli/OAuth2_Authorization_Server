package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;


/**
 * 
 *
 * @author syedusman
 * 
 * This class is to implement and enable an Authorization Server 
 * (i.e. an AuthorizationEndpoint and a TokenEndpoint) in the current application context.
 * 
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig  extends AuthorizationServerConfigurerAdapter {
	
	
	@Autowired
	AuthenticationManager authenticationManager;
		
	
	/**
	 * 
	 * @author syedusman
	 * 
	 * This Method is to configure  Client applications In-Memory for Demo Purpose only.
	 * In real world applications, client secrets should not be plainly visible in Code.
	 * 
	 * It has been implemented for 2 types of OAuth2 Flow
	 * A) Grant Type - "Password" Flow
	 * B) Grant Type - "Authorization Code" Flow
	**/
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		
		clients.inMemory().
		
		//In Memory Client Credentials for Grant Type -"Password" workflow
		
		withClient("myclient").secret("mysecret").scopes("read").authorizedGrantTypes("password","refresh_token").
		and().
		
		
		//In Memory Client Credentials for Grant Type - "Authorization Code" workflow
		// In Addition to Client ID & Client Secrets, on successful validation of generated Auth code, the
		// Generated Access Token along with Refresh Token will be passed over to the mentioned Redirect URI
		//For Demo Purpose only, the uri is redirected to the local host on a different port
		// In real world applications, it will be redirected to a page within the Client application.
		
		withClient("myappclient").secret("myappsecret").scopes("read").authorizedGrantTypes("authorization_code","refresh_token")
		.redirectUris("http://localhost:8083");
	}
	
	/**
	 * 
	 * The AuthenticationManager for the password grant.
	 * 
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
	
		endpoints.authenticationManager(authenticationManager);
	}
	
	/**
	 * This method is to check if the token is from the valid Client Application to 
	 * the Authorization Server and it allows only if valid credentials are passed along
	 * 
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		
		security.passwordEncoder(NoOpPasswordEncoder.getInstance())
		.checkTokenAccess("isAuthenticated()");
	}

}
