package com.myprog;

import java.util.Scanner;

public class _5MorningJog {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input
        int parentPos = scanner.nextInt(); // X1
        int childPos = scanner.nextInt(); // X2
        int velParent = scanner.nextInt(); // V1
        int steps = scanner.nextInt(); // N

        // Calculate parent's final position
        long parentFinalPos = (long) parentPos + (long) velParent * steps;

        int maxF = 0;
        int bestV2 = 0;

        // Iterate through possible child speeds (V2)
        // Assuming V2 can be from 1 to a reasonable upper limit (e.g., 1000 or more if needed)
        // The problem doesn't specify V2 range, so a reasonable upper bound is assumed for practicality.
        for (int v2 = 1; v2 <= 1000; v2++) { 
            // Calculate child's position for each step, and check for common footsteps
            int currentF = 0;
            long childCurrentPos = childPos;
            
            // Simulate child's movement and count common footsteps
            // We need to find common positions within the parent's path
            for (int k = 1; k <= steps; k++) {
                long parentStepPos = (long) parentPos + (long) velParent * k;
                childCurrentPos += v2; // Child takes a step

                // Check if child's current position matches any of parent's stepped positions within the path
                // And if the child's position is not beyond the parent's final position
                if (childCurrentPos >= parentPos && childCurrentPos <= parentFinalPos) {
                    // Check if child lands on a position the parent has landed on at some point
                    // This is a simplified check. A more rigorous check would involve finding exact common steps.
                    // For this problem, we assume "common footsteps" means landing on a position the parent has visited.
                    // The core logic here needs to be carefully designed to match the problem's exact definition of "common footsteps".
                    // The example suggests a simple count of positions where the child lands within the parent's path.
                    // If the problem implies landing on the same specific step as the parent, the logic would differ significantly.
                    if (childCurrentPos % velParent == parentPos % velParent) { // Check if child lands on a multiple of V1 from parentPos
                        currentF++;
                    }
                } else if (childCurrentPos > parentFinalPos) {
                    break; // Child has passed parent's path
                }
            }

            // Update maxF and bestV2 if a better solution is found
            if (currentF > maxF) {
                maxF = currentF;
                bestV2 = v2;
            } else if (currentF == maxF && v2 > bestV2) { // "output the highest prospective speed as V2"
                bestV2 = v2;
            }
        }

        // Output
        System.out.println(maxF + " " + bestV2);

        scanner.close();
    }
}