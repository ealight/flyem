package com.ddm.flyem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/api/healthcheck", "/api/registration").permitAll()
                .anyRequest().permitAll()
                .and().formLogin()
                .and().csrf().disable();
    }
}
