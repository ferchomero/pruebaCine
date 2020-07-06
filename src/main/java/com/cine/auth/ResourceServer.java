package com.cine.auth;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableResourceServer
@RestController
public class ResourceServer extends ResourceServerConfigurerAdapter{

	 @RequestMapping("/api")
	  public String api() {
	    return "Api privado";
	  }

	  @Override
		public void configure(HttpSecurity http) throws Exception {
			http
			.authorizeRequests().antMatchers("/oauth/token", "/oauth/authorize**").permitAll()
			.antMatchers(HttpMethod.POST, "/api/**").authenticated()
		    .antMatchers(HttpMethod.PUT, "/api/**").authenticated()
		    .antMatchers(HttpMethod.DELETE, "/api/**").authenticated()
		    .antMatchers(HttpMethod.GET, "/api/**").authenticated()
			.and().authorizeRequests()
			.antMatchers("/api/**").access("hasRole('ADMIN')");
		}   
}
