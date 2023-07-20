package com.example.assignment.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.assignment.userserviceimpl.CustomUserDetailService;

import org.springframework.context.annotation.Bean;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomUserDetailService customuserdetailservice;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeHttpRequests(requests -> requests
                        .antMatchers("/signup","/userregister","/login")
                        .permitAll()
                        .antMatchers("/user/**").hasRole("USER")        
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .loginProcessingUrl("/processlogin")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/logout"))
                .exceptionHandling(withDefaults());

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customuserdetailservice)
				.passwordEncoder(passwordEncoder());

	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers("/resources/**", "/static/**", "/css/**", "/photos/**", "/documents/**",
						"classpath:/static/documents/**");
	}
}
