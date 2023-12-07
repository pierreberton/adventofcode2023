package fr.bertonp.adventofcode.day3;

import fr.bertonp.adventofcode.common.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part1 {
    public static void main(String[] args) {
        ClassLoader classLoader = Part1.class.getClassLoader();

        try (InputStream resource = classLoader.getResourceAsStream("day3/input.txt")) {
            if (resource != null) {
                List<String> doc =
                        new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines().toList();
                mainCode(doc);
            } else {
                System.err.println("File not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void mainCode(List<String> lines) {

        List<Pair<Integer, Integer>> symbolsCoord = new ArrayList<>();
        List<Number> numbers = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Number currentNumber = null;
            char[] lineChars = line.toCharArray();
            for (int j = 0; j < lineChars.length; j++) {
                char c = lineChars[j];
                if (c >= '0' && c <= '9') {
                    if (currentNumber == null) {
                        currentNumber = new Number(new Pair<>(i, j), String.valueOf(c));
                    } else {
                        currentNumber.appendToNumber(String.valueOf(c));
                    }
                } else {
                    if (c != '.') {
                        symbolsCoord.add(new Pair<>(i, j));
                    }
                    if (currentNumber != null) {
                        currentNumber.setEndCoord(new Pair<>(i, j - 1));
                        numbers.add(currentNumber);
                        currentNumber = null;
                    }
                }
                if (j == lineChars.length - 1 && currentNumber != null) {
                    currentNumber.setEndCoord(new Pair<>(i, j));
                    numbers.add(currentNumber);
                    currentNumber = null;
                }
            }
        }

        List<Pair<Integer, Integer>> closedToList = new ArrayList<>();
        for (Pair<Integer, Integer> coord : symbolsCoord) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    closedToList.add(new Pair<>(coord.getFirst() + i, coord.getSecond() + j));
                }
            }
        }

        Set<Number> numbersClosedToSymbol = new HashSet<>();
        for (Pair<Integer, Integer> coord : closedToList) {
            for (Number number : numbers) {
                if (number.isTouchedBy(coord)) {
                    numbersClosedToSymbol.add(number);
                }
            }
        }

        System.out.println(numbersClosedToSymbol.stream().mapToInt(Number::getNumberAsInt).sum());
    }
}
