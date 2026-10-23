package com.myprog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// Program 9 : Replacing buses on overlapping routes
public class _9BusRouteOptimizer {

    public static int countRemainingBuses(int[][] busStations) {
        if (busStations == null || busStations.length == 0) {
            return 0;
        }

        // 1. Sort intervals
        Arrays.sort(busStations, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                }
                return Integer.compare(a[1], b[1]);
            }
        });

        List<int[]> mergedIntervals = new ArrayList<>();
        mergedIntervals.add(busStations[0]); // Add the first interval

        for (int i = 1; i < busStations.length; i++) {
            int[] currentInterval = busStations[i];
            int[] lastMergedInterval = mergedIntervals.get(mergedIntervals.size() - 1);

            // 2. Check for overlap and merge
            if (currentInterval[0] <= lastMergedInterval[1]) {
                // Overlap: merge by updating the end of the last merged interval
                lastMergedInterval[1] = Math.max(lastMergedInterval[1], currentInterval[1]);
            } else {
                // No overlap: add the current interval as a new merged interval
                mergedIntervals.add(currentInterval);
            }
        }

        // 3. Return the count of merged intervals
        return mergedIntervals.size();
    }

    public static void main(String[] args) {
        // Example Input from the problem statement:
        // 4 2 (N=4 buses, M=2 stations per bus)
        // 2 8
        // 6 10
        // 12 14
        // 13 30
        int[][] inputBuses = {{2, 8}, {6, 10}, {12, 14}, {12, 20}};
        int remainingBuses = countRemainingBuses(inputBuses);
        System.out.println("Number of remaining buses: " + remainingBuses); // Expected Output: 2 (after merging [2,10] and [12,30])
    }
}