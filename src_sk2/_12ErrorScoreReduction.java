package com.myprog;
import java.util.Arrays;
import java.util.Scanner;

//Program 12 : Error score based on bugs : Find number of succ projects
public class _12ErrorScoreReduction {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read errorScore_size (N)
        int n = sc.nextInt();

        // Read errorScore array
        int[] errorScores = new int[n];
        for (int i = 0; i < n; i++) {
            errorScores[i] = sc.nextInt();
        }

        // Read compP (P)
        int p = sc.nextInt();

        // Read othQ (Q)
        int q = sc.nextInt();

        sc.close();

        // Sort the error scores in ascending order to easily identify the highest score
        Arrays.sort(errorScores);

        int projectsCompleted = 0;

        // Iterate until all error scores are zero
        while (true) {
            boolean allZero = true;
            for (int score : errorScores) {
                if (score > 0) {
                    allZero = false;
                    break;
                }
            }
            if (allZero) {
                break; // All scores are zero, exit loop
            }

            // Find the team member with the highest error score
            // Since the array is sorted, the last element is the highest
            int maxErrorIndex = -1;
            for (int i = n - 1; i >= 0; i--) {
                if (errorScores[i] > 0) {
                    maxErrorIndex = i;
                    break;
                }
            }

            if (maxErrorIndex == -1) {
                break; // Should not happen if allZero is false, but as a safeguard
            }

            // Reduce the score of the team member who completes the project
            errorScores[maxErrorIndex] = Math.max(0, errorScores[maxErrorIndex] - p);

            // Reduce the scores of other team members with higher scores
            for (int i = 0; i < n; i++) {
                if (i != maxErrorIndex && errorScores[i] > errorScores[maxErrorIndex]) { // Check for higher score relative to the new maxErrorIndex score
                    errorScores[i] = Math.max(0, errorScores[i] - q);
                }
            }
            projectsCompleted++;

            // Re-sort to maintain the order for finding the next highest score
            Arrays.sort(errorScores);
        }

        System.out.println(projectsCompleted);
    }
}