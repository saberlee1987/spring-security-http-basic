package com.saber.springsecurityhttpbasic.config;

import com.saber.springsecurityhttpbasic.authentication.CustomBasicAuthenticationEntryPoint;
import com.saber.springsecurityhttpbasic.authentication.CustomBasicUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class HttpBasicSpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	private final CustomBasicUserDetailsService basicUserDetailsService;
	private final CustomBasicAuthenticationEntryPoint basicAuthenticationEntryPoint;
	
	public HttpBasicSpringSecurityConfig(CustomBasicUserDetailsService basicUserDetailsService, CustomBasicAuthenticationEntryPoint basicAuthenticationEntryPoint) {
		this.basicUserDetailsService = basicUserDetailsService;
		this.basicAuthenticationEntryPoint = basicAuthenticationEntryPoint;
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(basicUserDetailsService).passwordEncoder(passwordEncoder()).and()
				.eraseCredentials(true);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
				.realmName("saber")
				.authenticationEntryPoint(basicAuthenticationEntryPoint)
				.and()
				.csrf().disable()
				.cors();
		http.authorizeRequests().anyRequest().authenticated()
				.and();
	}
}
