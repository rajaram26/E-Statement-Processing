package com.raja.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    DataSource dataSource;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
    			.antMatchers("/home","/upload","/search","/about","/search-clients","/search-clientsbydate","/export-csv",
    					"/export-excel","/export-pdf","/exportbydate-csv","/exportbydate-excel","/exportbydate-pdf").hasAnyRole("USER","ADMIN")
    			.antMatchers("/add-users").hasRole("ADMIN")
    			.and()
    			.formLogin()
	    			.loginPage("/login")
	    			.defaultSuccessUrl("/home")
	    			.permitAll()
	    		.and()
	    		.logout()
	    			.invalidateHttpSession(true)
	    			.logoutSuccessUrl("/login")
	    			.permitAll();
    }

    
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}