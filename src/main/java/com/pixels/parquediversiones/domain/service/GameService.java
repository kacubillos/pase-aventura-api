package com.pixels.parquediversiones.domain.service;

import com.pixels.parquediversiones.domain.Game;
import com.pixels.parquediversiones.domain.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    public List<Game> getAll(){
        return gameRepository.getAll();
    }
    public Optional<Game> getGame(int gameId){
        return gameRepository.getGame(gameId);
    }
    public Game save(Game game){
        return gameRepository.save(game);
    }
    public boolean delete(int gameId){
        return getGame(gameId).map(amusement -> {
            gameRepository.delete(gameId);
            return true;
        }).orElse(false);
    }
}
