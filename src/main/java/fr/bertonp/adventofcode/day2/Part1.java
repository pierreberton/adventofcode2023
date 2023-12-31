package fr.bertonp.adventofcode.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Part1 {
    public static void main(String[] args) {
        ClassLoader classLoader = Part1.class.getClassLoader();

        try (InputStream resource = classLoader.getResourceAsStream("day2/input.txt")) {
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
        List<Game> games = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(Pattern.quote(":"));
            String gameId = parts[0].split(Pattern.quote("Game "))[1];
            String[] sets = parts[1].split(Pattern.quote(";"));
            Game g = new Game(Integer.parseInt(gameId));
            for (String set : sets) {
                String[] subSets = set.split(Pattern.quote(","));
                GameSet gs = new GameSet();
                for (String subSet : subSets) {
                    if (subSet.contains("blue")) {
                        gs.setBlue(Integer.parseInt(subSet.replaceAll("blue", "").trim()));
                    }
                    if (subSet.contains("green")) {
                        gs.setGreen(Integer.parseInt(subSet.replaceAll("green", "").trim()));
                    }
                    if (subSet.contains("red")) {
                        gs.setRed(Integer.parseInt(subSet.replaceAll("red", "").trim()));
                    }
                }
                g.getGameSets().add(gs);
            }
            games.add(g);
        }

        int initialRed = 12;
        int initialGreen = 13;
        int initialBlue = 14;
        int sum = 0;
        for (Game g : games) {
            boolean ok = true;
            for (GameSet gs : g.getGameSets()) {
                if (gs.getBlue() > initialBlue
                        || gs.getGreen() > initialGreen
                        || gs.getRed() > initialRed) {
                    ok = false;
                    break;
                }
            }

            if (ok) {
                sum += g.getId();
            }
        }
        System.out.println(sum);
    }
}
