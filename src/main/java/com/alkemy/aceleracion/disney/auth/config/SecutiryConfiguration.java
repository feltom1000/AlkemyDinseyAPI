package com.alkemy.aceleracion.disney.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alkemy.aceleracion.disney.auth.filter.JwtRequestFilter;
import com.alkemy.aceleracion.disney.auth.service.UserDetailsCustomService;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecutiryConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsCustomService userDetailsCustomService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Autowired
	private BCryptPasswordEncoder bCryptPass;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsCustomService).passwordEncoder(bCryptPass);
	}

    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
				.authorizeRequests().antMatchers("/auth/*").permitAll()
				.anyRequest().authenticated()
				.and().exceptionHandling()
				.and().formLogin()
				.and().httpBasic()
				.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
