package com.myprog;

import java.util.Scanner;

public class _38ChocolateDelivery {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfBox = sc.nextInt();
        int prepTime = sc.nextInt();
        int money = sc.nextInt();
        int numMachines = sc.nextInt();
        int numME = sc.nextInt(); // Number of characteristics for machines (always 2)

        int[][] machines = new int[numMachines][2];
        for (int i = 0; i < numMachines; i++) {
            machines[i][0] = sc.nextInt(); // mTime
            machines[i][1] = sc.nextInt(); // mCost
        }

        int numShops = sc.nextInt();
        int shopE = sc.nextInt(); // Number of characteristics for shops (always 2)

        int[][] shops = new int[numShops][2];
        for (int i = 0; i < numShops; i++) {
            shops[i][0] = sc.nextInt(); // sNum (number of boxes)
            shops[i][1] = sc.nextInt(); // sCost
        }

        // Calculate minimum time
        long minTime = Long.MAX_VALUE;

        // Option 1: Use existing machine
        minTime = Math.min(minTime, (long) numOfBox * prepTime);

        // Option 2: Purchase a new machine
        for (int[] machine : machines) {
            int mTime = machine[0];
            int mCost = machine[1];
            if (money >= mCost) {
                minTime = Math.min(minTime, (long) numOfBox * mTime);
            }
        }

        // Option 3: Buy from shops (and potentially use existing machine for remaining)
        for (int[] shop : shops) {
            int sNum = shop[0];
            int sCost = shop[1];
            if (money >= sCost) {
                long remainingBoxes = numOfBox - sNum;
                if (remainingBoxes <= 0) {
                    minTime = Math.min(minTime, 0); // All boxes bought
                } else {
                    minTime = Math.min(minTime, (long) remainingBoxes * prepTime);
                }
            }
        }
        
        // Option 4: Buy from shops and use a new machine for remaining
        for (int[] shop : shops) {
            int sNum = shop[0];
            int sCost = shop[1];
            if (money >= sCost) {
                long remainingBoxes = numOfBox - sNum;
                if (remainingBoxes > 0) {
                    for (int[] machine : machines) {
                        int mTime = machine[0];
                        int mCost = machine[1];
                        if (money - sCost >= mCost) { // Check if enough money for both shop and machine
                            minTime = Math.min(minTime, (long) remainingBoxes * mTime);
                        }
                    }
                }
            }
        }

        System.out.println(minTime);

        sc.close();
    }
}