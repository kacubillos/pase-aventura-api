package com.pixels.parquediversiones.domain.repository;

import com.pixels.parquediversiones.domain.Game;

import java.util.List;
import java.util.Optional;

public interface GameRepository {
    List<Game> getAll();
    Optional<Game> getGame(int gameId);
    Game save(Game game);
    void delete(int gameId);



}
