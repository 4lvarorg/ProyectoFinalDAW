package com.helpmybrain.configuracion;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ConfiguracionSeguridad {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        // Autenticación para los usuarios
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select email, password, 1 as enabled from usuario where email=?")
                .authoritiesByUsernameQuery(
                        "select u.email, r.nombre from usuario u inner join role r on u.role_id=r.id where u.email=?")
                .passwordEncoder(passwordEncoder());

        // Autenticación para los psicólogos
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select email, password, 1 as enabled from psicologo where email=?")
                .authoritiesByUsernameQuery(
                        "select p.email, r.nombre from psicologo p inner join role r on p.role_id=r.id where p.email=?")
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/v1/usuarios/**").hasAnyRole("USUARIO", "ADMIN")
                .antMatchers("/v1/psicologos/**").hasAnyRole("PSICOLOGO", "ADMIN")
                .antMatchers("/v1/citas/**").hasAnyRole("USUARIO", "PSICOLOGO", "ADMIN")
                .antMatchers("/v1/roles/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successForwardUrl("/")
                .failureUrl("/error")
                .permitAll()
                .and()
                .logout().permitAll();

        http.csrf().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
