package com.prd;

import java.util.Arrays;
import java.util.Scanner;

public class SalespersonTSP {

	 static class Point {
	 double x, y;

	 Point(double x, double y) {
	 this.x = x;
	 this.y = y;
	 }

	 double distanceTo(Point other) {
	 return Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));
	 }
	 }

	 public static void main(String[] args) {
	 Scanner sc = new Scanner(System.in);

	 // Read input
	 int posK = sc.nextInt(); // Starting position (1-indexed)
	 int num = sc.nextInt(); // Number of retailers on X-axis (N)

	 // Create array to store all retailers
	 Point[] retailers = new Point[num + 2]; // 0-indexed, size num+1 for actual retailers

	 // Read X-axis retailers
	 for (int i = 1; i <= num; i++) {
	 int x = sc.nextInt();
	 retailers[i] = new Point(x, 0);
	 }

	 // Read head retailer
	 int xHead = sc.nextInt();
	 int yHead = sc.nextInt();
	 retailers[num + 1] = new Point(xHead, yHead);

	 // Total number of retailers
	 int totalRetailers = num + 1;

	 // Precompute distances between all pairs
	 double[][] dist = new double[totalRetailers + 1][totalRetailers + 1];
	 for (int i = 1; i <= totalRetailers; i++) {
	 for (int j = 1; j <= totalRetailers; j++) {
	 if (i != j) {
	 dist[i][j] = retailers[i].distanceTo(retailers[j]);
	 } else {
	 dist[i][j] = 0;
	 }
	 }
	 }

	 // DP with bitmask for TSP
	 // dp[mask][i] = minimum distance to visit all retailers in mask, ending at retailer i
	 double[][] dp = new double[1 << totalRetailers][totalRetailers + 1];

	 // Initialize with infinity
	 for (int i = 0; i < (1 << totalRetailers); i++) {
	 Arrays.fill(dp[i], Double.POSITIVE_INFINITY);
	 }

	 // Start from retailer posK
	 dp[1 << (posK - 1)][posK] = 0; // Convert to 0-indexed for bitmask

	 // Fill DP table
	 for (int mask = 0; mask < (1 << totalRetailers); mask++) {
	 for (int u = 1; u <= totalRetailers; u++) {
	 if (dp[mask][u] == Double.POSITIVE_INFINITY) continue;
	 if ((mask & (1 << (u - 1))) == 0) continue; // u should be in current mask

	 for (int v = 1; v <= totalRetailers; v++) {
	 if (u == v) continue;
	 int newMask = mask | (1 << (v - 1));
	 dp[newMask][v] = Math.min(dp[newMask][v], dp[mask][u] + dist[u][v]);
	 }
	 }
	 }

	 // Find minimum distance to visit all retailers
	 double result = Double.POSITIVE_INFINITY;
	 int fullMask = (1 << totalRetailers) - 1;

	 for (int i = 1; i <= totalRetailers; i++) {
	 result = Math.min(result, dp[fullMask][i]);
	 }

	 // Print result rounded to 6 decimal places
	 System.out.printf("%.6f%n", result);
	 
	 System.out.printf("%.6f%n", dp[fullMask][1]);

	 sc.close();
	 }
	}

/*
1
3
0 1 2
1
1
O/p
3.414214
4.828427
*/