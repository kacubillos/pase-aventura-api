package com.pixels.parquediversiones.persistence;

import com.pixels.parquediversiones.domain.Game;
import com.pixels.parquediversiones.domain.repository.GameRepository;
import com.pixels.parquediversiones.persistence.crud.JuegoCrudRepository;
import com.pixels.parquediversiones.persistence.entity.Juego;
import com.pixels.parquediversiones.persistence.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class JuegoRepository implements GameRepository {
    @Autowired
    private JuegoCrudRepository juegoCrudRepository;
    @Autowired
    private GameMapper mapper;

    @Override
    public List<Game> getAll() {
        List<Juego> juegos =(List<Juego>) juegoCrudRepository.findAll();
        return mapper.toGames(juegos);
    }

    @Override
    public Optional<Game> getGame(int gameId) {
        return juegoCrudRepository.findById(gameId).map(juego -> mapper.toGame(juego));
    }

    @Override
    public Game save(Game game) {
        Juego juego = mapper.toJuego(game);
        return mapper.toGame(juegoCrudRepository.save(juego));
    }

    @Override
    public void delete(int idJuego){
        juegoCrudRepository.deleteById(idJuego);
    }
}
