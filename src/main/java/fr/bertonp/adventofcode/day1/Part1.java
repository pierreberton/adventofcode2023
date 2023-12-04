package fr.bertonp.adventofcode.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Part1 {

    private static final List<Integer> NUMBERS = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    public static void main(String[] args) {
        ClassLoader classLoader = Part1.class.getClassLoader();

        try (InputStream resource = classLoader.getResourceAsStream("day1/part1.txt")) {
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
        int sum = 0;

        for (String line : lines) {
            int firstIntegerIdx = minOfSet(listOfIndexes(line));
            int lastIntegerIdx = maxOfSet(listOfLastIndexes(line));
            String result = "" + line.charAt(firstIntegerIdx) + line.charAt(lastIntegerIdx);
            sum += Integer.parseInt(result);
            System.out.println(result);
        }

        System.out.println("Sum: " + sum);
    }

    private static int minOfSet(Set<Integer> integerSet) {
        return integerSet.stream().mapToInt(e -> e).min().orElseThrow(NoSuchElementException::new);
    }

    private static int maxOfSet(Set<Integer> integerSet) {
        return integerSet.stream().mapToInt(e -> e).max().orElseThrow(NoSuchElementException::new);
    }

    private static Set<Integer> listOfIndexes(String line) {
        Set<Integer> indexes = new HashSet<>();
        for (Integer i : NUMBERS) {
            int idx = line.indexOf(String.valueOf(i));
            if (idx >= 0) {
                indexes.add(idx);
            }
        }
        return indexes;
    }

    private static Set<Integer> listOfLastIndexes(String line) {
        Set<Integer> indexes = new HashSet<>();
        for (Integer i : NUMBERS) {
            int idx = line.lastIndexOf(String.valueOf(i));
            if (idx >= 0) {
                indexes.add(idx);
            }
        }
        return indexes;
    }
}
