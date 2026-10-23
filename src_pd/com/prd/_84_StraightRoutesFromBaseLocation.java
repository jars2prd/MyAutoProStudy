package com.prd;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class _84_StraightRoutesFromBaseLocation {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();      // Number of pickup locations
        int numcord = sc.nextInt();  // Always 2 (x, y) in this problem

        int[] px = new int[num];
        int[] py = new int[num];

        for (int i = 0; i < num; i++) {
            px[i] = sc.nextInt();
            py[i] = sc.nextInt();
        }

        int baseX = sc.nextInt();
        int baseY = sc.nextInt();

        Set<String> directions = new HashSet<String>();

        for (int i = 0; i < num; i++) {
            int dx = px[i] - baseX;
            int dy = py[i] - baseY;

            if (dx == 0 && dy == 0) {
                // Skip if pickup is exactly at the base
                continue;
            }

            int g = gcd(Math.abs(dx), Math.abs(dy));
            dx /= g;
            dy /= g;

            // Normalize direction so that opposite vectors don't duplicate
            if (dx < 0) {
                dx = -dx;
                dy = -dy;
            } else if (dx == 0 && dy < 0) {
                dy = -dy;
            }

            directions.add(dx + "," + dy);
        }

        System.out.println(directions.size());
    }

    // GCD helper
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a == 0 ? 1 : a;
    }
}

/*
3 2
1 1
-1 1
2 3
0
0

OP: 3
*/