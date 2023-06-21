package com.pixels.parquediversiones.persistence.crud;

import com.pixels.parquediversiones.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioCrudRepository extends CrudRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);
    Boolean existsByCorreoElectronico(String correoElectronico);
}
