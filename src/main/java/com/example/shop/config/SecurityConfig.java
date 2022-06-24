package com.example.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity (prePostEnabled = true)
public class SecurityConfig {
		 private final UserDetailsService userDetailsService;
	 	 @Autowired
	     public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
	         this.userDetailsService = userDetailsService;
	     }
	 	 
	 	 
	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    	http
	    		.csrf().disable()
	    		.authorizeRequests()
	    		.antMatchers("/").permitAll()
	    		.antMatchers(HttpMethod.POST, "/users").permitAll()
	    		.antMatchers(HttpMethod.GET, "/users").permitAll()
	    		.antMatchers(HttpMethod.GET, "/users/userinfo").permitAll()
	    		.antMatchers(HttpMethod.GET, "/books").permitAll()
	    		.anyRequest()
	    		.authenticated()
	    		.and()
	    		.formLogin()
	    		.loginPage("/auth/login").permitAll()
	    		.defaultSuccessUrl("/auth/success")
	    		.and()
	    		.logout()
	    		.logoutRequestMatcher(new AntPathRequestMatcher ("/auth/logout", "POST"))
	    		.invalidateHttpSession(true)
	    		.clearAuthentication(true)
	    		.deleteCookies("JSESSIOID")
	    		.logoutSuccessUrl("/auth/login");
	    	return http.build(); 
	    }
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	    	return new BCryptPasswordEncoder(12);
	    }

	   
	    @Bean
	    protected DaoAuthenticationProvider daoAuthenticationProvider() {
	        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
	        return daoAuthenticationProvider;
	    }
	    @Primary
	    @Bean
	    public AuthenticationManagerBuilder configure(AuthenticationManagerBuilder auth ) throws Exception {
			return auth.authenticationProvider(daoAuthenticationProvider());
	    	
	    }
	  
}

