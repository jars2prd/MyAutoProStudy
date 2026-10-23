package com.myprog;

import java.util.Arrays;

//Program 30 : Smallest Permutation of the given number 
public class _30CarRacingGame {

    public static int generateTrackNumber(int num) {
        // Handle negative numbers by taking the absolute value for permutation
        String s = String.valueOf(Math.abs(num));
        char[] digits = s.toCharArray();

        // Sort the digits to get the smallest permutation
        Arrays.sort(digits);

        // Reconstruct the number from the sorted digits
        StringBuilder smallestPermutation = new StringBuilder();
        for (char digit : digits) {
            smallestPermutation.append(digit);
        }

        // Convert the string back to an integer
        return Integer.parseInt(smallestPermutation.toString());
    }

    public static void main(String[] args) {
        // Example usage:
        int input1 = 934;
        System.out.println("Input: " + input1 + ", Output: " + generateTrackNumber(input1)); // Expected: 349

        int input2 = -123; // Example with a negative number
        System.out.println("Input: " + input2 + ", Output: " + generateTrackNumber(input2)); // Expected: 123
    }
}