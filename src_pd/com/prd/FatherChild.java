package com.prd;

import java.util.Scanner;

public class FatherChild {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int parentPos = sc.nextInt(); // X1
        int childPos = sc.nextInt();  // X2
        int velParent = sc.nextInt(); // V1
        int steps = sc.nextInt();     // N

        int maxF = 0;
        int bestV2 = 0;

        for (int v2 = 1; v2 <= 10000; v2++) {
            int count = 1;
            boolean started = false;

            for (int i = 0; i < steps; i++) {
                int posParent = parentPos + i * velParent;

                if ((posParent - childPos) % v2 == 0 && (posParent - childPos) >= 0) {
                    if (!started) {
                        started = true; // First match found
                    }
                    if (started) {
                        count++;
                    }
                }
            }

            if (count > maxF || (count == maxF && v2 > bestV2)) {
                maxF = count;
                bestV2 = v2;
            }
        }

        System.out.println(maxF + " " + bestV2);
    }
}
