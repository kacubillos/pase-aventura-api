package com.pixels.parquediversiones.persistence.crud;

import com.pixels.parquediversiones.persistence.entity.Venta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VentaCrudRepository extends CrudRepository<Venta, Integer> {
    Optional<List<Venta>> findByIdComprador(int idComprador);
}
