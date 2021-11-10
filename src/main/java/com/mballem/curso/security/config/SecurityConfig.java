package com.mballem.curso.security.config;

import com.mballem.curso.security.serice.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService service;

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

                // acessos privados admin
                .antMatchers("/u/**").hasAuthority("ADMIN")

                // acessos privados medicos
                .antMatchers("/medicos/**").hasAuthority("MEDICO")

                .anyRequest()
                .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/login-error")
                    .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")

                .and()
                .exceptionHandling()
                .accessDeniedPage("/acesso-negado"); // captura a exceção
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());

    }
}
