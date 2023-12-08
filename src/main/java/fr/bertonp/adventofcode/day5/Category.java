package fr.bertonp.adventofcode.day5;

import fr.bertonp.adventofcode.common.Pair;

import java.util.HashMap;
import java.util.Map;

public enum Category {
    S2S("seed-to-soil"),
    S2F("soil-to-fertilizer"),
    F2W("fertilizer-to-water"),
    W2L("water-to-light"),
    L2T("light-to-temperature"),
    T2H("temperature-to-humidity"),
    H2L("humidity-to-location");

    private final String startStr;

    private final Map<Pair<Long, Long>, Pair<Long, Long>> mapRanges = new HashMap<>();

    Category(String startStr) {
        this.startStr = startStr;
    }

    public void storeBounds(long source, long destination, long range) {
        mapRanges.put(new Pair<>(source, source + range), new Pair<>(destination, destination + range));
    }

    public long getNext(long source) {
        for (Pair<Long, Long> src : mapRanges.keySet()) {
            if (source >= src.getFirst() && source <= src.getSecond()) {
                long offset = source - src.getFirst();
                return mapRanges.get(src).getFirst() + offset;
            }
        }
        return source;
    }

    public static Category fromLineStart(String line) {
        for (Category c : Category.values()) {
            if (line != null && line.startsWith(c.startStr)) {
                return c;
            }
        }
        return null;
    }
}
