package com.pixels.parquediversiones.persistence.crud;

import com.pixels.parquediversiones.persistence.entity.Juego;
import org.springframework.data.repository.CrudRepository;


public interface JuegoCrudRepository extends CrudRepository<Juego, Integer> {
}
