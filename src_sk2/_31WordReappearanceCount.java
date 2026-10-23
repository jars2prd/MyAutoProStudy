package com.myprog;

import java.util.Scanner;

//Program 31 : Word appearance in a given string
public class _31WordReappearanceCount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the sentence
        String sentence = scanner.nextLine();

        // Read the word to search for
        String word = scanner.nextLine();

        // Convert both to lowercase for case-insensitive comparison
        String lowerCaseSentence = sentence.toLowerCase();
        String lowerCaseWord = word.toLowerCase();

        int count = 0;
        int index = 0;

        // Loop to find all occurrences, including overlapping ones
        while ((index = lowerCaseSentence.indexOf(lowerCaseWord, index)) != -1) {
            count++;
            // Move the index forward by one to find overlapping occurrences
            index++; 
        }

        System.out.println(count);

        scanner.close();
    }
}
