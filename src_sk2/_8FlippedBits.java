package com.myprog;

import java.util.Scanner;

//Program 8 Flipped Bits in Secret Message

public class _8FlippedBits {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the first integer (P)
        int num1 = scanner.nextInt();

        // Read the second integer (Q)
        int num2 = scanner.nextInt();

        // Calculate the XOR of num1 and num2
        int xorResult = num1 ^ num2;

        // Count the number of set bits in the XOR result
        int flippedBits = 0;
        while (xorResult > 0) {
            xorResult &= (xorResult - 1); // This operation clears the least significant set bit
            flippedBits++;
        }

        // Print the minimum number of flipped bits
        System.out.println(flippedBits);

        scanner.close();
    }
}
