package com.kalah.service;

import com.kalah.model.*;
import com.kalah.model.dto.ApiResponse;
import com.kalah.model.exception.InvalidParamException;
import com.kalah.storage.GameStorage;
import com.kalah.util.GameUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
public class GameService {


    /**
     * Create game board, set players information and store it in game store
     * @param p1
     * @param p1
     * @return ApiResponse<Game>
     */
    public ApiResponse<Game> startGame(String p1, String p2) {

        Game game = new Game();

        game.setId(UUID.randomUUID().toString());

        int[] pits = new int[GameUtil.TOTAL_PITS];
        Arrays.fill(pits, 0, GameUtil.PLAYER_1_KALAH, GameUtil.INITIAL_SEEDS);
        Arrays.fill(pits, 7, GameUtil.PLAYER_2_KALAH, GameUtil.INITIAL_SEEDS);
        game.setBoard(pits);

        game.setPlayer1(new Player(p1, GameUtil.PLAYER_1_KALAH));
        game.setPlayer2(new Player(p2, GameUtil.PLAYER_2_KALAH));
        game.setCurrentTurn(GameUtil.PLAYER1); // set initial turn as player 1's turn

        game.setGameStatus(GameStatus.IN_PROGRESS);
        game.setWinner(0);
        GameStorage.getStore().setGame(game);

        return new ApiResponse<>(game);
    }

    /**
     * Based on the selected pit by player, update the board,
     * decide the player turn and decide the player (if any)
     * @param gameId
     * @param pitNumber
     * @return ApiResponse<Game>
     * @throws InvalidParamException
     */
    public ApiResponse<Game> playGame(String gameId, int pitNumber) throws InvalidParamException {
        if (!GameUtil.doesGameExist(gameId, GameStorage.getStore().getGames())) {
            throw new InvalidParamException("Game with provided id doesn't exist");
        }

        Game game = GameStorage.getStore().getGames().get(gameId);

        // determine who's the player
        Player player = null;

        if (game.getGameStatus() == GameStatus.IN_PROGRESS) {
           if(game.getCurrentTurn() == 1) {
               player = game.getPlayer1();
           } else if (game.getCurrentTurn() == 2) {
               player = game.getPlayer2();
           }
        }

        GameUtil.play(game, player, pitNumber);
        GameStorage.getStore().setGame(game);

        return new ApiResponse<>(game);
    }

    /**
     * Reset the game
     * @param gameId
     * @return ApiResponse<Game>
     * @throws InvalidParamException
     */
    public ApiResponse<Game> resetGame(String gameId) throws InvalidParamException {
        if (!GameUtil.doesGameExist(gameId, GameStorage.getStore().getGames())) {
            throw new InvalidParamException("Game with provided id doesn't exist");
        }

        Game game = GameStorage.getStore().getGames().get(gameId);;
        GameUtil.resetGame(game);
        GameStorage.getStore().setGame(game);

        return new ApiResponse<>(game);
    }

}
