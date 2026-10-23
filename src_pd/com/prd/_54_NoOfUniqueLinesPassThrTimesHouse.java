package com.prd;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class _54_NoOfUniqueLinesPassThrTimesHouse {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read number of students and coordinate count (always 2)
        int n = sc.nextInt();
        int m = sc.nextInt(); // Not used since it's always 2

        int[] sx = new int[n];
        int[] sy = new int[n];

        for (int i = 0; i < n; i++) {
            sx[i] = sc.nextInt();
            sy[i] = sc.nextInt();
        }

        int tx = sc.nextInt(); // Teacher's x-coordinate
        int ty = sc.nextInt(); // Teacher's y-coordinate

        Set<String> slopes = new HashSet<String>();

        for (int i = 0; i < n; i++) {
            int dx = sx[i] - tx;
            int dy = sy[i] - ty;

            // Ignore student at the same location as teacher
            if (dx == 0 && dy == 0) {
                continue;
            }

            int g = gcd(dy, dx);
            if (g != 0) {
                dy /= g;
                dx /= g;
            }

            // Normalize direction for uniqueness
            if (dx < 0) {
                dx *= -1;
                dy *= -1;
            } else if (dx == 0) {
                dy = 1; // Vertical line
            } else if (dy == 0) {
                dx = 1; // Horizontal line
            }

            slopes.add(dy + "/" + dx);
        }

        System.out.println(slopes.size());
    }

    // Helper method for greatest common divisor
    private static int gcd(int a, int b) {
        if (b == 0) return Math.abs(a);
        return gcd(b, a % b);
    }
}

/*
3 2
1 1
-1 1
2 3
0
0
OP : 3
*/