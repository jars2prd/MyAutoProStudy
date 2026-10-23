package com.myprog;
import java.util.Scanner;

//Output is 33
public class _45MaxInternSalary {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read N (number of days) and M (type, always 2)
        int N = scanner.nextInt();
        int M = scanner.nextInt(); // M is always 2 for easy and difficult tasks

        // Arrays to store easy and difficult task earnings for each day
        int[] easyEarnings = new int[N];
        int[] difficultEarnings = new int[N];

        // Read earnings for each day
        for (int i = 0; i < N; i++) {
            easyEarnings[i] = scanner.nextInt();
            difficultEarnings[i] = scanner.nextInt();
        }

        // Dynamic programming approach to find maximum salary
        // dp[i][0] represents max salary up to day i, ending with no work on day i
        // dp[i][1] represents max salary up to day i, ending with easy task on day i
        // dp[i][2] represents max salary up to day i, ending with difficult task on day i
        int[][] dp = new int[N][3];

        // Base case for day 0
        dp[0][0] = 0; // No work on day 0
        dp[0][1] = easyEarnings[0]; // Easy task on day 0
        dp[0][2] = difficultEarnings[0]; // Difficult task on day 0

        // Fill DP table for subsequent days
        for (int i = 1; i < N; i++) {
            // No work on current day i
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));

            // Easy task on current day i
            // Can come from any state on previous day (no work, easy, or difficult)
            dp[i][1] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2])) + easyEarnings[i];

            // Difficult task on current day i
            // Can only come from no work on previous day
            dp[i][2] = dp[i - 1][0] + difficultEarnings[i];
        }

        // Find the maximum salary from the last day's states
        int maxSalary = Math.max(dp[N - 1][0], Math.max(dp[N - 1][1], dp[N - 1][2]));

        System.out.println(maxSalary);

        scanner.close();
    }
}