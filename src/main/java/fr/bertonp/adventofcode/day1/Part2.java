package fr.bertonp.adventofcode.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Part2 {

    private static final List<Integer> NUMBERS = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    private static final List<String> NUMBERS_AS_STR = Arrays.asList(
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

    public static void main(String[] args) {
        ClassLoader classLoader = Part2.class.getClassLoader();

        try (InputStream resource = classLoader.getResourceAsStream("day1/part2.txt")) {
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
            String result = "" + getNumberAtIndex(line, firstIntegerIdx) + getNumberAtIndex(line, lastIntegerIdx);
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
        for (String s : NUMBERS_AS_STR) {
            int idx = line.indexOf(s);
            if (idx >= 0) {
                indexes.add(idx);
            }
        }
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
        for (String s : NUMBERS_AS_STR) {
            int idx = line.lastIndexOf(s);
            if (idx >= 0) {
                indexes.add(idx);
            }
        }
        for (Integer i : NUMBERS) {
            int idx = line.lastIndexOf(String.valueOf(i));
            if (idx >= 0) {
                indexes.add(idx);
            }
        }
        return indexes;
    }

    private static int getNumberAtIndex(String line, int index) {
        try {
            return Integer.parseInt("" + line.charAt(index));
        } catch (NumberFormatException e) {
            String number = line.substring(index, index + 2);

            return switch (number) {
                case "ze" -> 0;
                case "on" -> 1;
                case "tw" -> 2;
                case "th" -> 3;
                case "fo" -> 4;
                case "fi" -> 5;
                case "si" -> 6;
                case "se" -> 7;
                case "ei" -> 8;
                case "ni" -> 9;
                default -> throw new IllegalStateException("Unexpected value: " + number);
            };
        }
    }
}
