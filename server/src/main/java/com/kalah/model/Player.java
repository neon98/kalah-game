package com.kalah.model;

/**
 * Represents the play of Kalah
 */
public class Player {
    private String name;
    private int kalahIndex;

    public Player(String name, int kalahIndex){
        this.name = name;
        this.kalahIndex = kalahIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKalahIndex() {
        return kalahIndex;
    }

    public void setKalahIndex(int kalahIndex) {
        this.kalahIndex = kalahIndex;
    }

}
