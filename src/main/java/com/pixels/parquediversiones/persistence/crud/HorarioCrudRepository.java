package com.pixels.parquediversiones.persistence.crud;

import com.pixels.parquediversiones.persistence.entity.Horario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HorarioCrudRepository extends CrudRepository<Horario, Integer> {
    Optional<List<Horario>> findByIdJuego(int idJuego);
    Optional<List<Horario>> findByDiaSemana(String diaSemana);
}
