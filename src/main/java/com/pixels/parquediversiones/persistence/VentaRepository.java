package com.pixels.parquediversiones.persistence;

import com.pixels.parquediversiones.persistence.crud.VentaCrudRepository;
import com.pixels.parquediversiones.persistence.entity.Venta;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VentaRepository{
    private VentaCrudRepository ventaCrudRepository;

    public List<Venta> getAll() {
        return (List<Venta>) ventaCrudRepository.findAll();
    }

    public Optional<List<Venta>> getByIdComprador(int idComprador) {
        return ventaCrudRepository.findByIdComprador(idComprador);
    }
}
