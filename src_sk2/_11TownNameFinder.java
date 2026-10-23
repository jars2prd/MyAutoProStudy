package com.myprog;

import java.util.Scanner;

//Program 11 : Tinselwood State : common prefix from the given words
public class _11TownNameFinder {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read N (number of people)
        int N = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Read the space-separated names
        String[] names = scanner.nextLine().split(" ");

        if (N == 0 || names.length == 0) {
            System.out.println(""); // No names, no common prefix
            return;
        }

        // Convert all names to lowercase as per the problem statement
        for (int i = 0; i < names.length; i++) {
            names[i] = names[i].toLowerCase();
        }

        String longestCommonPrefix = names[0];

        for (int i = 1; i < N; i++) {
            String currentName = names[i];
            int j = 0;
            // Find the common prefix between longestCommonPrefix and currentName
            while (j < longestCommonPrefix.length() && j < currentName.length()
                    && longestCommonPrefix.charAt(j) == currentName.charAt(j)) {
                j++;
            }
            longestCommonPrefix = longestCommonPrefix.substring(0, j);

            // If at any point the common prefix becomes empty, we can stop early
            if (longestCommonPrefix.isEmpty()) {
                break;
            }
        }

        System.out.println(longestCommonPrefix);

        scanner.close();
    }
}
