package com.helpmybrain.service.security;

import com.helpmybrain.entity.Psicologo;
import com.helpmybrain.entity.Usuario;
import com.helpmybrain.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;

    private String nombre;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Usuario usuario) {
        this.username = usuario.getEmail();
        this.password = usuario.getPassword();
        this.nombre = usuario.getNombre();
        this.authorities = translateRoles(usuario.getRole());
    }

    public CustomUserDetails(Psicologo psicologo) {
        this.username = psicologo.getEmail();
        this.password = psicologo.getPassword();
        this.nombre = psicologo.getNombre();
        this.authorities = translateRoles(psicologo.getRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private List<GrantedAuthority> translateRoles(Role role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // Ahora el rol es una entidad Role, por lo que obtenemos el nombre del rol de la entidad.
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getNombre()));
        return authorities;
    }
}
