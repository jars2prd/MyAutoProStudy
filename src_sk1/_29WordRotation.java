package com.myprog;

import java.util.Scanner;

//Program 29 : Check rotation of given words
public class _29WordRotation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the first word
        String word1 = scanner.nextLine();

        // Read the second word
        String word2 = scanner.nextLine();

        // Check if word1 is a right rotation of word2
        if (isRightRotation(word1, word2)) {
            System.out.println(1);
        } else {
            System.out.println(-1);
        }

        scanner.close();
    }

    // Function to check if word1 is a right rotation of word2
    public static boolean isRightRotation(String word1, String word2) {
        // If lengths are different, they cannot be rotations of each other
        if (word1.length() != word2.length()) {
            return false;
        }

        // Concatenate word1 with itself
        String concatenatedWord1 = word1 + word1;

        // Check if word2 is a substring of the concatenated word1
        return concatenatedWord1.contains(word2);
    }
}
