package com.kalah.model;

/**
 * Represents kalah game board with players and game status
 */
public class Game {
    private String id;
    private Player player1;
    private Player player2;
    private GameStatus gameStatus;
    private int currentTurn;
    private int winner;
    private int[] board;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int[] getBoard() {
        return board;
    }

    public void setBoard(int[] board) {
        this.board = board;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int turn) {
        this.currentTurn = turn;
    }

    public int getWinner() {
        return winner;
    }
    public void setWinner(int winner) {
        this.winner = winner;
    }
}
