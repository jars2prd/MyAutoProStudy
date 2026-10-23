package com.myprog;

import java.util.Arrays;
import java.util.Scanner;

import java.util.Scanner;

//If the output is 63
public class _44MaxVaporRate {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt(); // Number of chemicals
        int[] vaporRates = new int[num];

        for (int i = 0; i < num; i++) {
            vaporRates[i] = scanner.nextInt();
        }

        long maxVaporRate = 0; // Initialize with 0, as negative results are not picked

        // Iterate through all possible lengths (k) of the sets
        // Each set has size k. Total length of selected chemicals is 2*k
        for (int k = 1; k * 2 <= num; k++) {
            // Iterate through all possible starting positions (i) for the first set
            // The first set goes from index i to i + k - 1
            // The second set goes from index i + k to i + 2*k - 1
            for (int i = 0; i <= num - (2 * k); i++) {
                long currentVaporRate = 0;

                // Calculate the sum of products
                for (int j = 0; j < k; j++) {
                    // Chemical from first set: vaporRates[i + j]
                    // Chemical from second set (reversed): vaporRates[i + k + (k - 1 - j)]
                    currentVaporRate += (long) vaporRates[i + j] * vaporRates[i + 2 * k - 1 - j];
                }

                // Update maxVaporRate if currentVaporRate is greater and positive
                if (currentVaporRate > maxVaporRate) {
                    maxVaporRate = currentVaporRate;
                }
            }
        }

        System.out.println(maxVaporRate);

        scanner.close();
    }
}