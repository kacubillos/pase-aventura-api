package com.pixels.parquediversiones.persistence;

import com.pixels.parquediversiones.persistence.crud.EmpleadoCrudRepository;
import com.pixels.parquediversiones.persistence.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmpleadoRepository {
    @Autowired
    private EmpleadoCrudRepository empleadoCrudRepository;

    public List<Empleado> getAll() {
        return (List<Empleado>) empleadoCrudRepository.findAll();
    }

    public List<Empleado> getByRol(int idCategoria) {
        return empleadoCrudRepository.findByIdRol(idCategoria);
    }
}
