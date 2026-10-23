package com.myprog;

import java.util.Scanner;

//Note : Use of scanner.nextLine(); So please check how the inpt need to be given

public class _39MostFrequentCharInRange {

    public static char findMostFrequentChar(String str, int L, int R) {
        // Adjust L and R to be 0-indexed for string access
        L = L - 1;
        R = R - 1;

        // Ensure valid range
        if (L < 0 || R >= str.length() || L > R) {
            return ' '; // Or throw an exception for invalid input
        }

        int[] frequency = new int[26]; // For lowercase English letters 'a' to 'z'

        // Count character frequencies within the specified range
        for (int i = L; i <= R; i++) {
            char ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                frequency[ch - 'a']++;
            }
        }

        int maxFrequency = -1;
        char mostFrequentChar = ' ';

        // Find the character with the maximum frequency
        // In case of ties, select the lexicographically smallest
        for (int i = 0; i < 26; i++) {
            if (frequency[i] > maxFrequency) {
                maxFrequency = frequency[i];
                mostFrequentChar = (char) ('a' + i);
            } else if (frequency[i] == maxFrequency) {
                // If frequencies are same, choose lexicographically smaller
                char currentChar = (char) ('a' + i);
                if (currentChar < mostFrequentChar) {
                    mostFrequentChar = currentChar;
                }
            }
        }
        return mostFrequentChar;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();
        int numPairs = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        StringBuilder result = new StringBuilder();

        for (int p = 0; p < numPairs; p++) {
            String[] rangeVals = scanner.nextLine().split(" ");
            int L = Integer.parseInt(rangeVals[0]);
            int R = Integer.parseInt(rangeVals[1]);
            result.append(findMostFrequentChar(str, L, R));
        }

        System.out.println(result.toString());

        scanner.close();
    }
}
