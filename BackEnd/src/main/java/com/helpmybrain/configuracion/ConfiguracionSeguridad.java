package com.helpmybrain.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
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
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // <-- Línea nueva
                .antMatchers("/citas/**").hasAnyAuthority("ADMIN", "USUARIO", "PSICOLOGO")
                .antMatchers("/psicologos/**").hasAnyAuthority("ADMIN", "PSICOLOGO")
                .antMatchers("/roles/**").hasAuthority("ADMIN")
                .antMatchers("/usuarios/**").hasAnyAuthority("ADMIN", "USUARIO")
                .antMatchers("/loginUsuario").permitAll()
                .antMatchers("/login/loginUsuario").permitAll()
                .antMatchers("/login/loginPsicologo").permitAll()
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
