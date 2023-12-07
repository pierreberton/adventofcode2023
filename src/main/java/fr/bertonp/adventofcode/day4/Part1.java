package fr.bertonp.adventofcode.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public static void main(String[] args) {
        ClassLoader classLoader = Part1.class.getClassLoader();

        try (InputStream resource = classLoader.getResourceAsStream("day4/input.txt")) {
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
            Pattern pattern = Pattern.compile("^Card (.*?):(.*?)\\|(.*?)$");
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                Card card = new Card(Integer.parseInt(matcher.group(1).trim()));
                card.addWinningNumbersFromString(matcher.group(2));
                card.addGivenNumbersFromString(matcher.group(3));
                Set<Integer> mutualNumbers = card.intersectGivenAndWinningNumbers();
                sum += Math.pow(2, mutualNumbers.size() - 1);
            }
        }
        System.out.println(sum);
    }
}
