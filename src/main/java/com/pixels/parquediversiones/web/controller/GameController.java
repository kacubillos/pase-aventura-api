package com.pixels.parquediversiones.web.controller;

import com.pixels.parquediversiones.domain.Game;
import com.pixels.parquediversiones.domain.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;
    @GetMapping
    public ResponseEntity<List<Game>> getAll(){
        return new ResponseEntity<>(gameService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable("id") int gameId){
        return gameService.getGame(gameId)
                .map(game -> new ResponseEntity<>(game,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public ResponseEntity<Game> save(@RequestBody Game game){
        return new ResponseEntity<>(gameService.save(game),HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Game> update(@RequestBody Game game) {
        return gameService.getGame(game.getGameId())
                .map(emp -> new ResponseEntity<>(gameService.save(game), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int gameId){
        if(gameService.delete(gameId)){
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
