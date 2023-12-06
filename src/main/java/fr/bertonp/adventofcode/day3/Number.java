package fr.bertonp.adventofcode.day3;

import fr.bertonp.adventofcode.common.Pair;

import java.util.ArrayList;
import java.util.List;

public class Number {

    private Pair<Integer, Integer> beginCoord;

    private Pair<Integer, Integer> endCoord;

    private String number;

    public Number() {
    }

    public Number(Pair<Integer, Integer> beginCoord, String number) {
        this.beginCoord = beginCoord;
        this.number = number;
    }

    public Number(Pair<Integer, Integer> beginCoord, Pair<Integer, Integer> endCoord, String number) {
        this.beginCoord = beginCoord;
        this.endCoord = endCoord;
        this.number = number;
    }

    public Pair<Integer, Integer> getBeginCoord() {
        return beginCoord;
    }

    public void setBeginCoord(Pair<Integer, Integer> beginCoord) {
        this.beginCoord = beginCoord;
    }

    public Pair<Integer, Integer> getEndCoord() {
        return endCoord;
    }

    public void setEndCoord(Pair<Integer, Integer> endCoord) {
        this.endCoord = endCoord;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void appendToNumber(String character) {
        number += character;
    }

    public int getNumberAsInt() {
        return Integer.parseInt(number);
    }

    @Override
    public String toString() {
        return "Number{" +
                "beginCoord=" + beginCoord +
                ", endCoord=" + endCoord +
                ", number='" + number + '\'' +
                '}';
    }

    public boolean isTouchedBy(Pair<Integer, Integer> coord) {
        List<Pair<Integer, Integer>> fullCoord = new ArrayList<>();
        fullCoord.add(beginCoord);
        fullCoord.add(endCoord);

        int delta = endCoord.getSecond() - beginCoord.getSecond();

        for (int i = 1; i < delta - 1; i++) {
            fullCoord.add(new Pair<>(endCoord.getFirst(), endCoord.getSecond() - i));
        }

        return fullCoord.contains(coord);
    }
}
