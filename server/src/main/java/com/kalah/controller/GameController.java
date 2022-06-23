package com.kalah.controller;

import com.kalah.model.dto.ApiResponse;
import com.kalah.model.Game;
import com.kalah.model.dto.PlayRequest;
import com.kalah.model.dto.StartGameRequest;
import com.kalah.model.exception.InvalidParamException;
import com.kalah.service.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    @CrossOrigin(origins = "http://localhost:4200")
    public ApiResponse<Game> start(@RequestBody StartGameRequest request) {
        return gameService.startGame(request.getPlayer1Name(), request.getPlayer2Name());
    }

    @PostMapping("/play")
    @CrossOrigin(origins = "http://localhost:4200")
    public ApiResponse<Game> play(@RequestBody PlayRequest req) throws InvalidParamException {
        return gameService.playGame(req.getGameId(), req.getPitNumber());
    }

    @PostMapping("/reset")
    @CrossOrigin(origins = "http://localhost:4200")
    public ApiResponse<Game> reset(@RequestBody String gameId) throws InvalidParamException {
        return gameService.resetGame(gameId);
    }
}
