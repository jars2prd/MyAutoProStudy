package com.prd;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents a retailer with its coordinates and original position index.
 */
public class SalespersonPath {
    static int N; // total number of retailers (excluding head retailer? No, including all)
    static double[][] dist;
    static double[][] dp;
    static int startIndex;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int posK = sc.nextInt(); // starting retailer index (1-based)
        int num = sc.nextInt();  // number of retailers on X-axis (N)
        double[] xs = new double[num + 1]; // X coordinates for axis retailers
        double[] ys = new double[num + 1]; // Y coordinates for axis retailers

        for (int i = 0; i < num; i++) {
            xs[i] = sc.nextDouble();
            ys[i] = 0.0;
        }

        double xHead = sc.nextDouble();
        double yHead = sc.nextDouble();

        // Add head retailer at the end
        xs[num] = xHead;
        ys[num] = yHead;

        N = num + 1; // total retailers
        startIndex = posK - 1; // convert to 0-based index

        // Build distance matrix
        dist = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = Math.hypot(xs[i] - xs[j], ys[i] - ys[j]);
            }
        }

        // DP[mask][i] = min distance to cover 'mask' set ending at node i
        int totalMask = 1 << N;
        dp = new double[totalMask][N];
        for (double[] row : dp) Arrays.fill(row, Double.POSITIVE_INFINITY);

        dp[1 << startIndex][startIndex] = 0.0;

        for (int mask = 0; mask < totalMask; mask++) {
            for (int u = 0; u < N; u++) {
                if ((mask & (1 << u)) != 0) {
                    for (int v = 0; v < N; v++) {
                        if ((mask & (1 << v)) == 0) {
                            int nextMask = mask | (1 << v);
                            dp[nextMask][v] = Math.min(dp[nextMask][v],
                                    dp[mask][u] + dist[u][v]);
                        }
                    }
                }
            }
        }

        double ans = Double.POSITIVE_INFINITY;
        
        System.out.printf("Expected answer %.6f\n", dp[totalMask - 1][1]);
        for (int i = 0; i < N; i++) {
        	System.out.println(dp[totalMask - 1][i]);
            ans = Math.min(ans, dp[totalMask - 1][i]);
        }

        System.out.printf("%.6f\n", ans);
    }
}

/*
1
3
0 1 2
1
1
Expected answer 3.828427
Infinity
3.8284271247461903
3.414213562373095
3.414213562373095
3.414214

*/