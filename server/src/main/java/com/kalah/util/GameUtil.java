package com.kalah.util;

import com.kalah.model.*;

import java.util.Arrays;
import java.util.Map;

public class GameUtil {

    public static final int TOTAL_PITS = 14;
    public static final int PLAYER1 = 1;
    public static final int PLAYER_1_KALAH = 6;
    public static final int PLAYER2 = 2;
    public static final int PLAYER_2_KALAH = 13;
    public static final int INITIAL_SEEDS = 4;

    public static boolean doesGameExist(String gameId, Map<String, Game> games) {
        return games.containsKey(gameId);
    }

    public static Game play(Game game, Player player, int pitNumber ) {
        int lastDroppedPit = pitNumber;
        int[] board = game.getBoard();
        int seeds = board[pitNumber];

        board[pitNumber] = 0;
        // distribute seeds
        while (seeds > 0) {
            int dropPit = getNextPit(lastDroppedPit);

            // if kalah is opponent's then don't put seed in it
            if(isKalah(dropPit) && !isPlayersKalah(dropPit, player)){
                lastDroppedPit = dropPit;
                continue;
            }
            board[dropPit] += 1;
            seeds--;
            lastDroppedPit = dropPit;
        }

        // TODO reformat the code
        if(isPlayersPit(lastDroppedPit, player) &&
                board[lastDroppedPit] == 1 &&
                board[getOpponentPit(lastDroppedPit)] != 0) {
            board[player.getKalahIndex()] += board[lastDroppedPit] + board[getOpponentPit(lastDroppedPit)];


            board[lastDroppedPit] = 0;
            board[getOpponentPit(lastDroppedPit)] = 0;
        }
        game.setBoard(board); // TODO check whether board is getting updated with explicitely setting the value

        // if last dropped seed is not in current player's kalah then give turn to opponent
        if(!isPlayersKalah(lastDroppedPit, player)) {
            game.setCurrentTurn(getNextTurn(game.getCurrentTurn()));
        }

        return verifyGameStatus(game);
    }


    public static int getNextPit(int pitNumber) {
        pitNumber += 1;
        return pitNumber % TOTAL_PITS;
    }

    public static boolean isKalah(int pitNumber){
        return pitNumber == PLAYER_1_KALAH || pitNumber == PLAYER_2_KALAH;
    }

    public static boolean isPlayersKalah(int pitNumber, Player player) {
        return pitNumber == player.getKalahIndex();
    }

    public static boolean isPlayersPit(int pitNumber, Player player) {
        if(player.getKalahIndex() == PLAYER_1_KALAH) {
            return 0 <= pitNumber && pitNumber < PLAYER_1_KALAH;
        }
        else {
            return 7 <= pitNumber && pitNumber < PLAYER_2_KALAH;
        }
    }

    public static int getOpponentPit(int pitNumber) {
        return 12 - pitNumber;
    }

    /**
     * If Player 1 is the current player then change it to Player 2 and vice versa
     */
    public static int getNextTurn(int turn){
        if(turn == PLAYER1) {
            return PLAYER2;
        } else if(turn == PLAYER2){
            return PLAYER1;
        }
        return turn;
    }

    /**
     * Check and update if the game is finished or not after a player makes a step
     * @param game
     * @return
     */
    public static Game verifyGameStatus(Game game) {
        int[] board = game.getBoard();

        int player1PitSum = 0;
        for(int i = 0; i < PLAYER_1_KALAH; i++){
            player1PitSum += board[i];
        }

        int player2PitSum = 0;
        for(int i = 7; i < PLAYER_2_KALAH; i++){
            player2PitSum += board[i];
        }

        // if all the pits of either player is empty, game is finished
        if(player1PitSum == 0 || player2PitSum == 0) {
            game.setGameStatus(GameStatus.FINISHED);

            // reset all the pit values to 0
            for(int i = 0; i < TOTAL_PITS; i++){
                if(i != PLAYER_1_KALAH && i!= PLAYER_2_KALAH) {
                    board[i] = 0;
                }
            }

            // determine the winner and update the player status accordingly
            board[PLAYER_1_KALAH] += player1PitSum;
            board[PLAYER_2_KALAH] += player2PitSum;
            if(board[PLAYER_1_KALAH] > board[PLAYER_2_KALAH]) {
                game.setWinner(1);
            } else if(board[PLAYER_1_KALAH] < board[PLAYER_2_KALAH]) {
                game.setWinner(2);
            } else {
                game.setWinner(3);
            }

        }
        game.setBoard(board);
        return game;
    }

    public static Game resetGame(Game game) {
        int[] pits = new int[GameUtil.TOTAL_PITS];
        Arrays.fill(pits, 0, GameUtil.PLAYER_1_KALAH, GameUtil.INITIAL_SEEDS);
        Arrays.fill(pits, 7, GameUtil.PLAYER_2_KALAH, GameUtil.INITIAL_SEEDS);
        game.setBoard(pits);
        game.setGameStatus(GameStatus.IN_PROGRESS); // TODO check its logic
        game.setCurrentTurn(1);
        game.setWinner(0);
        return game;
    }

}