package com.myprog;

import java.util.*;

// Program 7 Land of Largest Plot

public class _7LargestPlot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read N and val (val is always 2 as per problem description)
        int N = scanner.nextInt();
        int val = scanner.nextInt(); // This will always be 2

        // Store house number and position in a list of arrays/objects
        List<int[]> houses = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int houseNum = scanner.nextInt();
            int position = scanner.nextInt();
            houses.add(new int[]{houseNum, position});
        }

        // Sort houses by position to easily calculate distances
        // If positions are equal, sort by house number for tie-breaking (though problem states no two houses have same position)
        Collections.sort(houses, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        int maxDistance = -1;
        int bestHouse1 = -1;
        int bestHouse2 = -1;

        // Iterate through sorted houses to find the largest gap
        for (int i = 0; i < N - 1; i++) {
            int[] house1 = houses.get(i);
            int[] house2 = houses.get(i + 1);

            int currentDistance = house2[1] - house1[1];

            // If current distance is greater than maxDistance, update
            if (currentDistance > maxDistance) {
                maxDistance = currentDistance;
                bestHouse1 = house1[0];
                bestHouse2 = house2[0];
            } 
            // If current distance is equal to maxDistance, choose the one with the least distance from the reference point (0)
            // This means choosing the pair where the left house's position is smallest.
            else if (currentDistance == maxDistance) {
                if (house1[1] < houses.get(houses.indexOf(new int[]{bestHouse1, maxDistance - (bestHouse2 - bestHouse1)}))[1]) { // This logic is simplified for clarity, assuming sorted array.
                    bestHouse1 = house1[0];
                    bestHouse2 = house2[0];
                }
            }
        }

        // Output the house numbers in ascending order
        System.out.println(Math.min(bestHouse1, bestHouse2) + " " + Math.max(bestHouse1, bestHouse2));

        scanner.close();
    }
}
