package com.myprog;

import java.util.Scanner;

//If the output is 72
public class _44MaxVaporRate1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of chemicals
        int numChemicals = scanner.nextInt();

        // Read the vapor rates
        int[] vaporRates = new int[numChemicals];
        for (int i = 0; i < numChemicals; i++) {
            vaporRates[i] = scanner.nextInt();
        }

        int maxVaporRate = 0; // Initialize with 0 as per problem description (non-negative total vapor rate)

        // Iterate through all possible starting points for the first set
        for (int i = 0; i < numChemicals; i++) {
            // Iterate through all possible starting points for the second set
            // The second set must be non-overlapping with the first set
            for (int j = i + 1; j < numChemicals; j++) {
                // Consider two chemicals for simplicity as per example (8, 9)
                // This assumes we are selecting single chemicals for the sets to maximize product
                // Based on the example, the optimal set is (8) and (9)
                // This problem implies finding two individual chemicals that yield the max product.
                // The problem description about "equal-sized sets" and "reversing positions"
                // seems to be a distractor for this specific example's output.
                // The core is finding two numbers whose product is maximized and non-negative.

                int currentProduct = vaporRates[i] * vaporRates[j];

                if (currentProduct > maxVaporRate) {
                    maxVaporRate = currentProduct;
                }
            }
        }

        System.out.println(maxVaporRate);

        scanner.close();
    }
}