package com.example.gamerentservice.controller;

import com.example.gamerentservice.dto.EditGameDto;
import com.example.gamerentservice.dto.GameDto;
import com.example.gamerentservice.service.GameRentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GameRentController{
    private final GameRentService gameRentService;

    @GetMapping("/show-games")
    public List<GameDto> showGameList(){
        log.info("GET : gameList");
        return gameRentService.showAllGame();
    }

    @GetMapping("show-game/{gameTitle}")
    public GameDto showGame(@PathVariable  String gameTitle){
        log.info("GET - get-game : "+gameTitle);
        gameRentService.updateView(gameTitle);
        return gameRentService.showGame(gameTitle);
    }

    @PostMapping("/create-game")
    public GameDto createGame(
            @RequestBody GameDto request){
        log.info("POST - CREATE GAME : ",request);
        return gameRentService.createGame(request);
    }

    @PutMapping("/edit-game/{gameTitle}")
    public GameDto editGame(@PathVariable String gameTitle, @RequestBody EditGameDto gameDto){
        log.info("EDIT - game : "+gameTitle);
        return gameRentService.editGame(gameTitle, gameDto);
    }

    @Transactional
    @DeleteMapping("/delete-game/{gameTitle}")
    public GameDto delteGame(@PathVariable String gameTitle){
        log.info("DELTE - Game");
        return gameRentService.delteGame(gameTitle);
    }

}
