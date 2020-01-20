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
                .antMatchers("/api/healthcheck").permitAll()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
                .and().formLogin()
                .and().csrf().disable();
    }
}
