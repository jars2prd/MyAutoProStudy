package com.myprog;

import java.util.Arrays;
import java.util.Scanner;

//Program 6 : state of the cells : Similar to StreetLights program 10
public class _6CellSimulation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of cells (always 8)
        int numCells = scanner.nextInt();

        // Read the initial state of the 8 cells
        int[] cells = new int[numCells];
        for (int i = 0; i < numCells; i++) {
            cells[i] = scanner.nextInt();
        }

        // Read the number of days
        int days = scanner.nextInt();

        // Simulate the cell states for the given number of days
        for (int d = 0; d < days; d++) {
            int[] nextDayCells = new int[numCells];
            for (int i = 0; i < numCells; i++) {
                int leftNeighbor = (i == 0) ? 0 : cells[i - 1]; // Leftmost cell's left neighbor is always inactive (0)
                int rightNeighbor = (i == numCells - 1) ? 0 : cells[i + 1]; // Rightmost cell's right neighbor is always inactive (0)

                if (leftNeighbor == rightNeighbor) {
                    nextDayCells[i] = 0; // Becomes inactive if both neighbors are same (both active or both inactive)
                } else {
                    nextDayCells[i] = 1; // Becomes active if neighbors are different (one active, one inactive)
                }
            }
            cells = Arrays.copyOf(nextDayCells, numCells); // Update cells for the next day's calculation
        }

        // Print the final state of the cells
        for (int i = 0; i < numCells; i++) {
            System.out.print(cells[i] + (i == numCells - 1 ? "" : " "));
        }
        System.out.println();

        scanner.close();
    }
}