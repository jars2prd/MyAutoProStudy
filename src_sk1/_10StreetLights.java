package com.myprog;
import java.util.Scanner;
import java.util.Arrays;

//Program 10 : Street lights: based on adjacent cell position , Similar to Cell Simulation program 6

public class _10StreetLights {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read N (number of street lights)
        int N = scanner.nextInt();

        // Read initial state of street lights
        int[] currentState = new int[N];
        for (int i = 0; i < N; i++) {
            currentState[i] = scanner.nextInt();
        }

        // Read M (number of days)
        int M = scanner.nextInt();

        // Simulate the state changes for M days
        for (int day = 0; day < M; day++) {
            int[] nextState = new int[N];
            
            for (int i = 0; i < N; i++) {
                // Determine the state of left and right neighbors
                int leftNeighbor = (i == 0) ? 0 : currentState[i - 1]; // Leftmost light's left neighbor is always OFF (0)
                int rightNeighbor = (i == N - 1) ? 0 : currentState[i + 1]; // Rightmost light's right neighbor is always OFF (0)

                // Apply the rule:
                // Light goes OFF (0) if adjacent lights are both ON (1) or both OFF (0).
                // Light goes ON (1) otherwise.
                if (leftNeighbor == rightNeighbor) {
                    nextState[i] = 0;
                } else {
                    nextState[i] = 1;
                }
            }
            // Update the current state for the next day's calculation
            currentState = Arrays.copyOf(nextState, N);
        }

        // Print the final state of the street lights
        for (int i = 0; i < N; i++) {
            System.out.print(currentState[i] + (i == N - 1 ? "" : " "));
        }
        System.out.println();

        scanner.close();
    }
}
