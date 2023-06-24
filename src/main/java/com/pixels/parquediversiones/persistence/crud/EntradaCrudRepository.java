package com.pixels.parquediversiones.persistence.crud;

import com.pixels.parquediversiones.persistence.entity.Entrada;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EntradaCrudRepository extends CrudRepository<Entrada, Integer> {
    Optional<List<Entrada>> findByIdComprador(int idComprador);
}
