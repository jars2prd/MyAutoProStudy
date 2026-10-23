package com.myprog;

import java.util.Scanner;

import java.util.*;

public class _45MaxSalary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of days
        int m = sc.nextInt(); // types of tasks (should be 2: easy & hard)

        int[] easy = new int[n];
        int[] hard = new int[n];

        for (int i = 0; i < n; i++) {
            easy[i] = sc.nextInt();
            hard[i] = sc.nextInt();
        }

        // dp[i][0] = max salary till day i if doing easy task today
        // dp[i][1] = max salary till day i if doing hard task today
        // dp[i][2] = max salary till day i if doing no task today
        int[][] dp = new int[n][3];

        // Day 0 initialization
        dp[0][0] = easy[0];
        dp[0][1] = hard[0];
        dp[0][2] = 0;

        for (int i = 1; i < n; i++) {
            // If doing easy task today, we can come from any task yesterday
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2])) + easy[i];

            // If doing hard task today, yesterday cannot be hard
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][2]) + hard[i];

            // If doing no task today, just take the best from yesterday
            dp[i][2] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
        }

        // Max of last day choices
        int ans = Math.max(dp[n - 1][0], Math.max(dp[n - 1][1], dp[n - 1][2]));
        System.out.println(ans);
    }
}
