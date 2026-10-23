package com.myprog;

import java.util.*;

//Program 32: Use of whitener : X and Y axis problem
public class _32MaxLineEraser {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read numS (number of starting X-coordinates)
        int numS = scanner.nextInt();

        // Read startX array
        int[] startX = new int[numS];
        for (int i = 0; i < numS; i++) {
            startX[i] = scanner.nextInt();
        }

        // Read numE (number of ending X-coordinates) - though the problem states M-N always, we'll read it as numE for clarity
        int numE = scanner.nextInt();

        // Read endX array
        int[] endX = new int[numE];
        for (int i = 0; i < numE; i++) {
            endX[i] = scanner.nextInt();
        }

        // Create a list of line segments (start, end)
        List<int[]> segments = new ArrayList<>();
        for (int i = 0; i < numS; i++) {
            segments.add(new int[]{startX[i], endX[i]});
        }

        // Sort segments by startX, then by endX
        segments.sort((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // Use a greedy approach to find the minimum number of markers
        int markerCount = 0;
        int i = 0;
        while (i < segments.size()) {
            markerCount++;
            int currentEnd = segments.get(i)[1]; // The end of the current segment

            // Find the maximum reach of the current marker placement
            int maxReach = currentEnd;
            int j = i + 1;
            while (j < segments.size()) {
                // If the current segment can be covered by a marker placed within its range
                // and this marker can also cover the next segment
                if (segments.get(j)[0] <= maxReach) {
                    maxReach = Math.max(maxReach, segments.get(j)[1]);
                    j++;
                } else {
                    break;
                }
            }

            // Move 'i' to the first segment not covered by the current marker
            i = j;
        }

        System.out.println(markerCount);

        scanner.close();
    }
}