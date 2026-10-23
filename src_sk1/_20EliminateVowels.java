package com.myprog;

import java.util.Scanner;

//Program 20 : Eliminate all vowels
public class _20EliminateVowels {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String inputString = scanner.nextLine();

        String resultString = removeVowels(inputString);
        System.out.println("Output: " + resultString);

        scanner.close();
    }

    public static String removeVowels(String str) {
        StringBuilder sb = new StringBuilder();
        String vowels = "aeiouAEIOU"; // String containing all vowels (both lowercase and uppercase)

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // Check if the character is NOT a vowel
            if (vowels.indexOf(c) == -1) { // indexOf returns -1 if the character is not found
                sb.append(c);
            }
        }
        return sb.toString();
    }
}