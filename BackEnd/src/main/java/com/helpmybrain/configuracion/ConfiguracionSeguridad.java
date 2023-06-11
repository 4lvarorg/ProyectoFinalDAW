package com.helpmybrain.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.helpmybrain.service.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/v1/citas/**").hasAnyAuthority("ADMIN", "USUARIO", "PSICOLOGO")
                .antMatchers("/v1/psicologos/**").hasAnyAuthority("ADMIN", "PSICOLOGO")
                .antMatchers("/v1/roles/**").hasAuthority("ADMIN")
                .antMatchers("/v1/usuarios/**").hasAnyAuthority("ADMIN", "USUARIO")
                .antMatchers("/loginUsuario").permitAll()
                .antMatchers("/loginPsicologo").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .csrf().disable()
                .logout();
    }
}
