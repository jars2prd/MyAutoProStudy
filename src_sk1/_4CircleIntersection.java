package com.myprog;

import java.util.Scanner;
import java.lang.Math;

public class _4CircleIntersection {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input for the first circle
        int centerX1 = scanner.nextInt();
        int centerY1 = scanner.nextInt();
        int radius1 = scanner.nextInt();

        // Input for the second circle
        int centerX2 = scanner.nextInt();
        int centerY2 = scanner.nextInt();
        int radius2 = scanner.nextInt();

        scanner.close();

        double area = calculateIntersectionArea(centerX1, centerY1, radius1, centerX2, centerY2, radius2);

        System.out.printf("%.6f%n", area);
    }

    public static double calculateIntersectionArea(int x1, int y1, int r1, int x2, int y2, int r2) {
        double d = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)); // Distance between centers

        // Case 1: Circles do not overlap
        if (d >= r1 + r2) {
            return 0.0;
        }

        // Case 2: One circle is completely inside the other
        if (d <= Math.abs(r1 - r2)) {
            // The smaller circle's area is the intersection area
            if (r1 < r2) {
                return Math.PI * r1 * r1;
            } else {
                return Math.PI * r2 * r2;
            }
        }

        // Case 3: Circles partially overlap
        double a1 = 2 * Math.acos((d * d + r1 * r1 - r2 * r2) / (2 * d * r1));
        double a2 = 2 * Math.acos((d * d + r2 * r2 - r1 * r1) / (2 * d * r2));

        double area1 = 0.5 * r1 * r1 * (a1 - Math.sin(a1));
        double area2 = 0.5 * r2 * r2 * (a2 - Math.sin(a2));

        return area1 + area2;
    }
}