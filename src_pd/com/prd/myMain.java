package com.prd;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

class myMain {
	
	int id;
    double x;
    double y;

    Retailer(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the Euclidean distance to another retailer.
     * @param other The other retailer.
     * @return The distance between this retailer and the other.
     */
    double distanceTo(Retailer other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
}

/**
 * Main class to find the shortest path for the salesperson.
 */
public class myMain {

    public static void main(String[] args) {
        // Use Locale.US to ensure dot is used as a decimal separator
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        // 1. Read Inputs
        int posK = scanner.nextInt();
        int num = scanner.nextInt();

        Retailer[] retailers = new Retailer[num + 1];
        Retailer[] xAxisRetailers = new Retailer[num];

        for (int i = 0; i < num; i++) {
            int xCoord = scanner.nextInt();
            // Retailer IDs are 1-based
            retailers[i] = new Retailer(i + 1, xCoord, 0);
            xAxisRetailers[i] = retailers[i];
        }

        int xN1 = scanner.nextInt();
        int yN1 = scanner.nextInt();
        // The head retailer has ID num + 1
        retailers[num] = new Retailer(num + 1, xN1, yN1);

        scanner.close();

        // 2. Identify Key Retailers
        Retailer startRetailer = retailers[posK - 1];
        Retailer headRetailer = retailers[num];

        // Sort X-axis retailers by their X-coordinate to find the extremes
        Arrays.sort(xAxisRetailers, (a, b) -> Double.compare(a.x, b.x));

        Retailer minXRetailer = xAxisRetailers[0];
        Retailer maxXRetailer = xAxisRetailers[num - 1];

        // 3. Calculate Distances for the Four Main Strategies

        // The distance of the "sweep" across all X-axis retailers
        double xAxisSweepDistance = minXRetailer.distanceTo(maxXRetailer);

        // Strategy 1: Start -> MinX -> MaxX -> Head
        double path1 = startRetailer.distanceTo(minXRetailer) + xAxisSweepDistance + maxXRetailer.distanceTo(headRetailer);

        // Strategy 2: Start -> MaxX -> MinX -> Head
        double path2 = startRetailer.distanceTo(maxXRetailer) + xAxisSweepDistance + minXRetailer.distanceTo(headRetailer);

        // Strategy 3: Start -> Head -> MinX -> MaxX
        double path3 = startRetailer.distanceTo(headRetailer) + headRetailer.distanceTo(minXRetailer) + xAxisSweepDistance;
       
        // Strategy 4: Start -> Head -> MaxX -> MinX
        double path4 = startRetailer.distanceTo(headRetailer) + headRetailer.distanceTo(maxXRetailer) + xAxisSweepDistance;

        // 4. Find the minimum of the four path lengths
        double minPath = Math.min(Math.min(path1, path2), Math.min(path3, path4));

        // 5. Print the result formatted to 6 decimal places
        System.out.printf("%.6f%n", minPath);
    }
	
}
