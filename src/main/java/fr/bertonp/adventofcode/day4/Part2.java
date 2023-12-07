package fr.bertonp.adventofcode.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public static void main(String[] args) {
        ClassLoader classLoader = Part2.class.getClassLoader();

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
        Integer[] copies = new Integer[lines.size()];
        Arrays.fill(copies, 1);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Pattern pattern = Pattern.compile("^Card (.*?):(.*?)\\|(.*?)$");
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                Card card = new Card(Integer.parseInt(matcher.group(1).trim()));
                card.addWinningNumbersFromString(matcher.group(2));
                card.addGivenNumbersFromString(matcher.group(3));
                Set<Integer> mutualNumbers = card.intersectGivenAndWinningNumbers();
                for (int k = 0; k < copies[i]; k++) {
                    for (int j = i + 1; j < i + 1 + mutualNumbers.size(); j++) {
                        copies[j] = copies[j] + 1;
                    }
                }
            }
        }
        System.out.println(Arrays.stream(copies).mapToInt(e -> e).sum());
    }
}
