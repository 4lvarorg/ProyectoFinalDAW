package com.helpmybrain.service.security;

import com.helpmybrain.entity.Psicologo;
import com.helpmybrain.entity.Usuario;
import com.helpmybrain.repository.PsicologoRepository;
import com.helpmybrain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PsicologoRepository psicologoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.obtenerUsuarioPorEmail(email);
        if (usuario != null) {
            return new CustomUserDetails(usuario);
        }

        Psicologo psicologo = psicologoRepository.obtenerPsicologoPorEmail(email);
        if (psicologo != null) {
            return new CustomUserDetails(psicologo);
        }

        throw new UsernameNotFoundException("No user found with username: " + email);
    }
}
