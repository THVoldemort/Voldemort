package com.voldemort.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.voldemort.service.UserService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	DataSource datasource;
	
	@Autowired
	public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
		// @formatter:off
		 auth.inMemoryAuthentication()
		 .withUser("john").password(passwordEncoder.encode("123")).roles("USER").and()
		 .withUser("tom").password(passwordEncoder.encode("111")).roles("ADMIN").and()
		 .withUser("user1").password(passwordEncoder.encode("pass")).roles("USER").and()
		 .withUser("admin").password(passwordEncoder.encode("nimda")).roles("ADMIN");
		 auth.jdbcAuthentication().dataSource(datasource).usersByUsernameQuery(
				   "select username,password, 1 as enabled from user where username=?")
		  .authoritiesByUsernameQuery(
		   "select username, password as role from user where username=?");

//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService);
//		authProvider.setPasswordEncoder(passwordEncoder);
//		auth.authenticationProvider(authProvider);
	}// @formatter:on

	@Autowired
	private UserService userDetailsService;

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth)
	// throws Exception {
	// auth.authenticationProvider(authenticationProvider());
	// }
	//
	// @Bean
	// public DaoAuthenticationProvider authenticationProvider() {
	// DaoAuthenticationProvider authProvider
	// = new DaoAuthenticationProvider();
	// authProvider.setUserDetailsService(userDetailsService);
	// authProvider.setPasswordEncoder(passwordEncoder);
	// return authProvider;
	// }

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		// @formatter:off
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/oauth/token/revokeById/**").permitAll()
				.antMatchers("/tokens/**").permitAll().anyRequest().authenticated().and().formLogin().permitAll().and()
				.csrf().disable();
		// @formatter:on
	}

}
