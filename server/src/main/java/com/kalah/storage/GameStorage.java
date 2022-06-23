package com.kalah.storage;

import com.kalah.model.Game;

import java.util.HashMap;
import java.util.Map;

public class GameStorage {
    private static Map<String, Game> games;
    private static GameStorage store;

    private GameStorage() {
        games = new HashMap<>();
    }

    public static synchronized GameStorage getStore() {
        if (store == null) {
           store = new GameStorage();
        }
        return store;
    }

    public Map<String, Game> getGames() {
        return games;
    }

    public void setGame(Game game) {
        games.put(game.getId(), game);
    }
}
