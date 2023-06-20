package com.pixels.parquediversiones.web.security;

import com.pixels.parquediversiones.domain.Role;
import com.pixels.parquediversiones.domain.UserAccount;
import com.pixels.parquediversiones.persistence.UsuarioRepository;
import com.pixels.parquediversiones.persistence.entity.Rol;
import com.pixels.parquediversiones.persistence.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UsuarioRepository usuarioRepository;

    @Autowired
    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Collection<GrantedAuthority> mapToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount userAccount = usuarioRepository.getByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with this email: " + email));
        return new User(userAccount.getEmail(), userAccount.getPassword(), mapToAuthorities(Collections.singletonList(userAccount.getEmployee().getRole())));
    }
}
