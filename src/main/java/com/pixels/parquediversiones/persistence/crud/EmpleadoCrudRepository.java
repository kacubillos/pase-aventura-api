package com.pixels.parquediversiones.persistence.crud;

import com.pixels.parquediversiones.persistence.entity.Empleado;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmpleadoCrudRepository extends CrudRepository<Empleado, Integer> {
    List<Empleado> findByIdRol(int idRol);

}
