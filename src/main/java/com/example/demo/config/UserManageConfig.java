package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserManageConfig  extends WebSecurityConfigurerAdapter {
	
	
	/**
	 * 
	 * @author syedusman
	 * 
	 * This method is used to create the User information In-Memory for demo purpose only
	 * In real world application this information will be obtained from data stores
	 * and never be part of code.
	 * 
	 **/
	 
	@Bean
	public UserDetailsService userDetailsService() {
		
		InMemoryUserDetailsManager user = new InMemoryUserDetailsManager();
		
		user.createUser(User.withUsername("user1").password(pe().encode("pass1")).authorities("read").build());
			
	    
		return user;
	}
	
	
	/**
	 * 
	 * This is PasswordEncoder interface that uses the BCrypt strong hashing function 
	 * to encode the password
	 * 
	 */
	@Bean
	public PasswordEncoder pe( ) {
		
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * 
	 *  This method to expose the AuthenticationManager from configure(AuthenticationManagerBuilder) 
	 *  to be exposed as a Bean
	 * 
	 */
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		
		return super.authenticationManagerBean();
	}
	
	/**
	 *  
	 * Built in Spring Boot Login Form to authenticate every url on any request
	 * 
	 **/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.formLogin();
		
		http.authorizeRequests().anyRequest().authenticated();
	}
	

}
