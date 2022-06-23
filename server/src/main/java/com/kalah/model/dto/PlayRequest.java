package com.kalah.model.dto;

public class PlayRequest {
    private String gameId;
    private int pitNumber;

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getPitNumber() {
        return pitNumber;
    }

    public void setPitNumber(int pitNumber) {
        this.pitNumber = pitNumber;
    }
}
