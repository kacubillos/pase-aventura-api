package com.pixels.parquediversiones.persistence;

import com.pixels.parquediversiones.persistence.crud.EntradaCrudRepository;
import com.pixels.parquediversiones.persistence.entity.Entrada;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EntradaRepository {
    private EntradaCrudRepository entradaCrudRepository;

    public List<Entrada> getAll() {
        return (List<Entrada>) entradaCrudRepository.findAll();
    }

    public Optional<List<Entrada>> getByIdComprador(int idComprador) {
        return entradaCrudRepository.findByIdComprador(idComprador);
    }
}
