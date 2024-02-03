package com.gl.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.gl.springboot.service.UserDetailsService‫Impl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	
	
	@Bean
	public UserDetailsService userDetailsService() {
//		UserDetails admin = User.withUsername("Ath")
//                .password(encoder.encode("123"))
//                .roles("ADMIN")
//                .build();
//		UserDetails user = User.withUsername("Ath1")
//				.password(encoder.encode("123"))
//                .roles("USER")
//                .build();
//		return new InMemoryUserDetailsManager(admin, user);		
		
		return new UserDetailsService‫Impl();
				
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/StudentManagement/welcome","/StudentManagement/error").permitAll()
		.requestMatchers("/StudentManagement/list","/StudentManagement/addForm","/StudentManagement/saveStudent").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
		.requestMatchers("/StudentManagement/updateForm/{id}","/StudentManagement/deleteStudent/{id}").hasAuthority("ROLE_ADMIN")
        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
        .and().formLogin()
        .defaultSuccessUrl("/StudentManagement/list", true) 
        .and().logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login") 
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .permitAll()
        .and()
        .exceptionHandling()
            .accessDeniedPage("/StudentManagement/error")
        .and().build();
		
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
}
