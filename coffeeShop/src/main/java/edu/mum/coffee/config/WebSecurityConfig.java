package edu.mum.coffee.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.authorizeRequests()
		.antMatchers("/","/resources/static/**","/assets/**", "/home", "/index","/product","/resources/**", "/registration","/login","/api/**")
		.permitAll()
		.anyRequest()
		/*.fullyAuthenticated()
		.and().formLogin()
		.permitAll()
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    	.logoutSuccessUrl("/")
        .permitAll();*/
		//for rest
		.permitAll();
		httpSecurity.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("Bizen").password("123").roles("Admin")
		.and().withUser("Helen").password("123").roles("User");
	}
}