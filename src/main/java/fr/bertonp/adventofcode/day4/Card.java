package fr.bertonp.adventofcode.day4;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Card {
    private int number;

    private Set<Integer> winningNumbers = new HashSet<>();

    private Set<Integer> givenNumbers = new HashSet<>();

    public Card() {
    }

    public Card(int number) {
        this.number = number;
    }

    public Card(int number, Set<Integer> winningNumbers, Set<Integer> givenNumbers) {
        this.number = number;
        this.winningNumbers = winningNumbers;
        this.givenNumbers = givenNumbers;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Set<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(Set<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public Set<Integer> getGivenNumbers() {
        return givenNumbers;
    }

    public void setGivenNumbers(Set<Integer> givenNumbers) {
        this.givenNumbers = givenNumbers;
    }

    public void addWinningNumbersFromString(String listOfWinningNumbers) {
        String[] parts = listOfWinningNumbers.split(" ");
        for (String part : parts) {
            if (!part.isBlank()) {
                winningNumbers.add(Integer.parseInt(part.trim()));
            }
        }
    }

    public void addGivenNumbersFromString(String listOfGivenNumbers) {
        String[] parts = listOfGivenNumbers.split(" ");
        for (String part : parts) {
            if (!part.isBlank()) {
                givenNumbers.add(Integer.parseInt(part.trim()));
            }
        }
    }

    public Set<Integer> intersectGivenAndWinningNumbers() {
        Set<Integer> mutualNumbers = new HashSet<>(givenNumbers);
        mutualNumbers.retainAll(winningNumbers);
        return mutualNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number == card.number && Objects.equals(winningNumbers, card.winningNumbers) && Objects.equals(givenNumbers, card.givenNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, winningNumbers, givenNumbers);
    }

    @Override
    public String toString() {
        return "Card{" +
                "number=" + number +
                ", winningNumbers=" + winningNumbers +
                ", givenNumbers=" + givenNumbers +
                '}';
    }
}
