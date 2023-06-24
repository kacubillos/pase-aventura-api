package com.pixels.parquediversiones.persistence;

import com.pixels.parquediversiones.domain.UserAccount;
import com.pixels.parquediversiones.domain.repository.UserRepository;
import com.pixels.parquediversiones.persistence.crud.UsuarioCrudRepository;
import com.pixels.parquediversiones.persistence.entity.Usuario;
import com.pixels.parquediversiones.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository implements UserRepository {
    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;
    @Autowired
    private UserMapper mapper;

    @Override
    public List<UserAccount> getAll() {
        List<Usuario> usuarios = (List<Usuario>) usuarioCrudRepository.findAll();
        return mapper.toUsers(usuarios);
    }

    @Override
    public Optional<UserAccount> getUser(int userId) {
        return usuarioCrudRepository.findById(userId).map(user -> mapper.toUser(user));
    }

    @Override
    public Optional<UserAccount> getByEmail(String email) {
        return usuarioCrudRepository.findByCorreoElectronico(email).map(user -> mapper.toUser(user));
    }

    @Override
    public boolean existsUser(String email) {
        return usuarioCrudRepository.existsByCorreoElectronico(email);
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        Usuario usuario = mapper.toUsuario(userAccount);
        return mapper.toUser(usuarioCrudRepository.save(usuario));
    }

    @Override
    public void delete(int userId) {
        usuarioCrudRepository.deleteById(userId);
    }
}
