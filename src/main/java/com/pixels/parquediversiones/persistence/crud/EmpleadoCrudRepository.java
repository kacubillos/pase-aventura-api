package com.pixels.parquediversiones.persistence.crud;

import com.pixels.parquediversiones.persistence.entity.Empleado;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmpleadoCrudRepository extends CrudRepository<Empleado, Integer> {
    List<Empleado> findByIdRol(int idRol);
    Optional<Empleado> findByNumeroDocumento(int numeroDocumento);
}
