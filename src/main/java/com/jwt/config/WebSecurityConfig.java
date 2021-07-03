package com.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.security.CustomAccessDeniedHandler;
import com.jwt.security.JwtAuthenticationEntryPoint;
import com.jwt.security.JwtAuthenticationFilter;
import com.jwt.service.impl.CustomUserDetailsService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private CustomUserDetailsService userDetailsService;
		
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private CustomAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/api/login", "/login").permitAll();
		
		http.authorizeRequests().antMatchers("/api/admin/*").access("hasRole('ROLE_ADMIN')");
		
		http.authorizeRequests().antMatchers("/api/profile").access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')");
		
		http.formLogin().loginPage("/login");
		
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
								.accessDeniedHandler(accessDeniedHandler);
								
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
