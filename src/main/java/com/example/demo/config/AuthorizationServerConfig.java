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

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig  extends AuthorizationServerConfigurerAdapter {
	
	
	@Autowired
	AuthenticationManager authenticationManager;
		
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		
		clients.inMemory().
		
		withClient("myclient").secret("mysecret").scopes("read").authorizedGrantTypes("password","refresh_token").
		and().
		
		
		withClient("myappclient").secret("myappsecret").scopes("read").authorizedGrantTypes("authorization_code","refresh_token")
		.redirectUris("http://localhost:8083");
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// TODO Auto-generated method stub
		endpoints.authenticationManager(authenticationManager);
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		
		security.passwordEncoder(NoOpPasswordEncoder.getInstance())
		.checkTokenAccess("isAuthenticated()");
	}

}
