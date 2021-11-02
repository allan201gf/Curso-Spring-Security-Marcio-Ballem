package com.mballem.curso.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Utilizando a configuraçao do SpringSecurity
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http); caso queira utilizar a configuração padrão do Spring Security
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/image/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .anyRequest()
                .authenticated();
    }
}
