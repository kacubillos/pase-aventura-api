package com.pixels.parquediversiones.persistence.crud;

import com.pixels.parquediversiones.persistence.entity.Comprador;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CompradorCrudRepository extends CrudRepository<Comprador,Integer > {

    List<Comprador> findByfechaNacimientoLessThan(LocalDateTime fechaNacimiento);
    List<Comprador> findByfechaNacimientoGreaterThan(LocalDateTime fechaNacimiento);

}
