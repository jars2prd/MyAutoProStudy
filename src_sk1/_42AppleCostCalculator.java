package com.myprog;

import java.util.Scanner;

public class _42AppleCostCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input N, appleShop1, priceShop1, appleShop2, priceShop2
        int N = scanner.nextInt();
        int appleShop1 = scanner.nextInt();
        int priceShop1 = scanner.nextInt();
        int appleShop2 = scanner.nextInt();
        int priceShop2 = scanner.nextInt();

        long minCost = Long.MAX_VALUE;

        // Iterate through possible number of boxes from shop 1
        for (int i = 0; i * appleShop1 <= N + appleShop2; i++) { // Optimization: Upper bound can be N/appleShop1 + a few more to account for shop2
            long currentCost = (long) i * priceShop1;
            int remainingApples = N - (i * appleShop1);

            if (remainingApples <= 0) {
                // If we already have N or more apples from shop 1, consider only buying from shop 1
                minCost = Math.min(minCost, currentCost);
            } else {
                // Calculate apples needed from shop 2
                int boxesShop2 = (remainingApples + appleShop2 - 1) / appleShop2; // Ceiling division
                long costShop2 = (long) boxesShop2 * priceShop2;
                minCost = Math.min(minCost, currentCost + costShop2);
            }
        }

        System.out.println(minCost);
        scanner.close();
    }
}