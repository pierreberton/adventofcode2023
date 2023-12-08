package fr.bertonp.adventofcode.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Part1 {

    private final static Map<Long, Long> seeds = new HashMap<>();

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = Part1.class.getClassLoader();

        try (InputStream resource = classLoader.getResourceAsStream("day5/input.txt")) {
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

    private static void mainCode(List<String> lines) throws Exception {
        Category currentCategory = null;
        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }

            if (line.startsWith("seeds:")) {
                String[] seedsNumbers = line.split(Pattern.quote(":"))[1].split(Pattern.quote(" "));
                for (String seedNum : seedsNumbers) {
                    if (!seedNum.isBlank()) {
                        seeds.put(Long.parseLong(seedNum.trim()), -1L);
                    }
                }
            } else {
                Category tmpCat = Category.fromLineStart(line);
                long destination;
                long source;
                long length;
                if (tmpCat != null) {
                    currentCategory = tmpCat;
                    continue;
                } else {
                    String[] lineParts = line.split(Pattern.quote(" "));
                    destination = Long.parseLong(lineParts[0].trim());
                    source = Long.parseLong(lineParts[1].trim());
                    length = Long.parseLong(lineParts[2].trim());
                }

                if (currentCategory == null) {
                    throw new Exception("Category wasn't chosen");
                }

                currentCategory.storeBounds(source, destination, length);
            }
        }

        for (Long seed : seeds.keySet()) {
            long s2s = Category.S2S.getNext(seed);
            long s2f = Category.S2F.getNext(s2s);
            long f2w = Category.F2W.getNext(s2f);
            long w2l = Category.W2L.getNext(f2w);
            long l2t = Category.L2T.getNext(w2l);
            long t2h = Category.T2H.getNext(l2t);
            seeds.put(seed, Category.H2L.getNext(t2h));
        }

        Map.Entry<Long, Long> min = null;
        for (Map.Entry<Long, Long> entry : seeds.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }
        if (min == null) {
            throw new Exception("Min value not found");
        }
        System.out.println(min);
    }
}
