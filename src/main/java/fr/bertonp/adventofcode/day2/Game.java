package fr.bertonp.adventofcode.day2;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int id;

    private List<GameSet> gameSets = new ArrayList<>();

    public Game() {
    }

    public Game(int id) {
        this.id = id;
    }

    public Game(int id, List<GameSet> gameSets) {
        this.id = id;
        this.gameSets = gameSets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<GameSet> getGameSets() {
        return gameSets;
    }

    public void setGameSets(List<GameSet> gameSets) {
        this.gameSets = gameSets;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", gameSets=" + gameSets +
                '}';
    }
}
