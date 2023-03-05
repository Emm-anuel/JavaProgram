package com.epam.javaprogram.util;

import java.util.ArrayList;
import java.util.List;

public class Results {

    private final List<Long> timeTaken = new ArrayList<>();
    private long startTime;

    public long endTime() {
        final long taken = System.nanoTime() - startTime;
        timeTaken.add(taken);
        return taken;
    }

    public long getAverageTime() {
        long total = 0;
        for (final long time : timeTaken) {
            total += time;
        }

        return total / timeTaken.size();
    }

    public void startTime() {
        startTime = System.nanoTime();
    }
}