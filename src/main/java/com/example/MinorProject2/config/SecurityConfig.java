package com.example.MinorProject2.config;

import org.aspectj.weaver.patterns.DeclareTypeErrorOrWarning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.MinorProject2.enums.Authorities;

@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers("/saveStudent/**").permitAll()
		.antMatchers("/student/**").hasAuthority(Authorities.std.toString())
		.antMatchers("/admin/**").hasAnyAuthority(Authorities.Adm.toString())
		.antMatchers("/saveBook/**").hasAuthority(Authorities.Adm.toString())
		.antMatchers("/book/search/**").hasAnyAuthority(Authorities.Adm.toString(), Authorities.std.toString())
		.antMatchers("/home").permitAll()
		.and().formLogin();
	}
	
}
