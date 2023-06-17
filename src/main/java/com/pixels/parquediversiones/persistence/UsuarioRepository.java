package com.pixels.parquediversiones.persistence;

import com.pixels.parquediversiones.persistence.crud.UsuarioCrudRepository;
import com.pixels.parquediversiones.persistence.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {
    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    public List<Usuario> getAll() {
        return (List<Usuario>) usuarioCrudRepository.findAll();
    }

    public Optional<Usuario> getUser(int userId) {
        return usuarioCrudRepository.findById(userId);
    }

    public Optional<Usuario> findByEmail(String email) {
        return usuarioCrudRepository.findByCorreoElectronico(email);
    }

    public boolean existsUser(String email) {
        return usuarioCrudRepository.existsByCorreoElectronico(email);
    }
}
