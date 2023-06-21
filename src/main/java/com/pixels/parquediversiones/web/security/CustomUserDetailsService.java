package com.pixels.parquediversiones.web.security;

import com.pixels.parquediversiones.domain.Role;
import com.pixels.parquediversiones.domain.UserAccount;
import com.pixels.parquediversiones.persistence.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

    /**
     * Methods to get Authorities from a list of roles
     * @param roles the roles that the user has in the system.
     * @return {@link Collection} Authorities
     */
    public Collection<GrantedAuthority> mapToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    /**
     * Method to obtain a user by email
     * @param email the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount userAccount = usuarioRepository.getByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with this email: " + email));
        return new User(userAccount.getEmail(), userAccount.getPassword(), mapToAuthorities(Collections.singletonList(userAccount.getEmployee().getRole())));
    }
}
