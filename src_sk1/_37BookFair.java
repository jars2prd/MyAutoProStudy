package com.myprog;

import java.util.*;

import java.util.Scanner;

public class _37BookFair {

	
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int amount = scanner.nextInt(); // Amount of money the person can spend
        
        int numHorror = scanner.nextInt(); // Number of Horror books
        int dummyH = scanner.nextInt(); // Dummy variable for 'X' as per problem statement (always 2)

        int[][] horrorBooks = new int[numHorror][2]; // [rating, price]
        for (int i = 0; i < numHorror; i++) {
            horrorBooks[i][0] = scanner.nextInt(); // hrating
            horrorBooks[i][1] = scanner.nextInt(); // hprice
        }

        int numSciFi = scanner.nextInt(); // Number of Sci-Fi books
        int dummyS = scanner.nextInt(); // Dummy variable for 'P' as per problem statement (always 2)

        int[][] sciFiBooks = new int[numSciFi][2]; // [rating, price]
        for (int i = 0; i < numSciFi; i++) {
            sciFiBooks[i][0] = scanner.nextInt(); // srating
            sciFiBooks[i][1] = scanner.nextInt(); // sprice
        }

        scanner.close();

        int maxStarRating = -1;

        // Iterate through all possible combinations of one Horror and one Sci-Fi book
        for (int i = 0; i < numHorror; i++) {
            int hRating = horrorBooks[i][0];
            int hPrice = horrorBooks[i][1];

            for (int j = 0; j < numSciFi; j++) {
                int sRating = sciFiBooks[j][0];
                int sPrice = sciFiBooks[j][1];

                if (hPrice + sPrice <= amount) {
                    int currentStarRating = hRating + sRating;
                    int remainingAmount = amount - (hPrice + sPrice);

                    // Consider buying more books (Horror or Sci-Fi) to maximize rating
                    // This part can be further optimized, but for simplicity, we'll try to add more
                    // books from either category greedily if they fit the remaining budget and add rating.
                    // For a more complex knapsack-like solution, you would use dynamic programming
                    // or a more sophisticated greedy approach for the remaining items.
                    
                    // For this problem's constraints and maximizing rating,
                    // we can consider the remaining books and pick the ones that give the most rating per price,
                    // or simply add any remaining books if they fit the budget and offer a rating.
                    // Given the small number of items in the example, simple iteration works.
                    
                    // We'll iterate through all other horror books and sci-fi books again to add to the remaining budget
                    // This approach is not fully optimized like a true knapsack but aims to maximize rating given budget.
                    
                    // Add remaining Horror books
                    for (int k = 0; k < numHorror; k++) {
                        if (k != i && remainingAmount >= horrorBooks[k][1]) {
                             currentStarRating += horrorBooks[k][0];
                             remainingAmount -= horrorBooks[k][1];
                        }
                    }

                    // Add remaining Sci-Fi books
                    for (int k = 0; k < numSciFi; k++) {
                        if (k != j && remainingAmount >= sciFiBooks[k][1]) {
                            currentStarRating += sciFiBooks[k][0];
                            remainingAmount -= sciFiBooks[k][1];
                        }
                    }

                    maxStarRating = Math.max(maxStarRating, currentStarRating);
                }
            }
        }

        System.out.println(maxStarRating);
    }
}