package com.myprog;

import java.util.*;

public class _41BinaryStringConversion {

    public static int minSteps(String str1, String str2) {
        if (str1.equals(str2)) {
            return 0; // Already converted
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> visited = new HashMap<>(); // Stores string and min steps to reach it

        queue.offer(str1);
        visited.put(str1, 0);

        int stepLength = 2; // Start reversing substrings of length 2

        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                String currentStr = queue.poll();
                int currentSteps = visited.get(currentStr);

                // Generate next states by reversing substrings of 'stepLength'
                for (int j = 0; j <= currentStr.length() - stepLength; j++) {
                    StringBuilder sb = new StringBuilder(currentStr);
                    String reversedSub = new StringBuilder(currentStr.substring(j, j + stepLength)).reverse().toString();
                    sb.replace(j, j + stepLength, reversedSub);
                    String nextStr = sb.toString();

                    if (nextStr.equals(str2)) {
                        return currentSteps + 1; // Found the target string
                    }

                    if (!visited.containsKey(nextStr)) {
                        visited.put(nextStr, currentSteps + 1);
                        queue.offer(nextStr);
                    }
                }
            }
            stepLength++; // Increment step length for next level of transformations
            if (stepLength > str1.length()) { // No more valid substring lengths to reverse
                break;
            }
        }

        return -1; // str2 not reachable
    }

    public static void main(String[] args) {
        // Example from the problem description
        String str1 = "1010";
        String str2 = "0011";
        System.out.println("Input str1: " + str1 + ", str2: " + str2);
        System.out.println("Output: " + minSteps(str1, str2)); // Expected: 2

        // Another example
        String str3 = "0000";
        String str4 = "1111";
        System.out.println("Input str1: " + str3 + ", str2: " + str4);
        System.out.println("Output: " + minSteps(str3, str4)); // Expected: -1 (or a higher number if reachable)
    }
}
