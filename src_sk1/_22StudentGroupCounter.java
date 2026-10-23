package com.myprog;

import java.util.Scanner;

//Program 22 : Count a perticular digit in a perticular number
public class _22StudentGroupCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read particularGroup
        int particularGroup = scanner.nextInt();

        // Read studentGroup as a string to iterate through its digits
        scanner.nextLine(); // Consume the newline character after reading the integer
        String studentGroupStr = scanner.nextLine();

        int count = 0;
        // Iterate through each character (digit) of the studentGroup string
        for (int i = 0; i < studentGroupStr.length(); i++) {
            // Convert the character to an integer
            int digit = Character.getNumericValue(studentGroupStr.charAt(i));

            // Check if the digit matches the particularGroup
            if (digit == particularGroup) {
                count++;
            }
        }

        // Print the final count
        System.out.println(count);

        scanner.close();
    }
}