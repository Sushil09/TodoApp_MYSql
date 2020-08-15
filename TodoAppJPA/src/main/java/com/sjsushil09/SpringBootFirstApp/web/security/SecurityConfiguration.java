package com.sjsushil09.SpringBootFirstApp.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	//Create users
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception {
		auth.inMemoryAuthentication()
        .passwordEncoder(NoOpPasswordEncoder.getInstance())
    		.withUser("Varun").password("dummy")
                .roles("USER", "ADMIN");
		auth.inMemoryAuthentication()
        .passwordEncoder(NoOpPasswordEncoder.getInstance())
    		.withUser("Sushil").password("dummy")
                .roles("USER", "ADMIN");
	}
	
	// Below method is used to provide the default login page for secure login
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login", "/h2-console/**").permitAll()
                .antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
                .formLogin();
        
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

}
