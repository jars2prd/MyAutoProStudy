package com.prd;

import java.util.Scanner;

public class _89_BeautifulHouses {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read rows and columns
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        // Read the grid
        int[][] grid = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        int count = 0;

        // Directions for 8 neighbors (top, top-right, right, bottom-right, bottom, bottom-left, left, top-left)
        int[] dx = {-1, -1,  0, 1, 1,  1,  0, -1};
        int[] dy = { 0,  1,  1, 1, 0, -1, -1, -1};

        // Iterate over all cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    boolean beautiful = true;

                    // Check all 8 neighbors
                    for (int k = 0; k < 8; k++) {
                        int ni = i + dx[k];
                        int nj = j + dy[k];

                        // If within bounds and neighbor is not 0, it's not beautiful
                        if (ni >= 0 && ni < rows && nj >= 0 && nj < cols) {
                            if (grid[ni][nj] != 0) {
                                beautiful = false;
                                break;
                            }
                        }
                    }

                    if (beautiful) {
                        count++;
                    }
                }
            }
        }

        System.out.println(count);
    }
}


/*

4 4
1 0 1 1
0 0 1 0
1 0 1 1
1 0 1 0

OP: 1 ... expected OP: 3
=====

5 7
1 0 0 0 0 1 1
0 1 0 0 0 1 0
0 0 1 0 0 1 1
1 0 0 1 0 1 1
1 0 0 0 0 0 0

OP: 0  ... expected OP: 2

Refer _89_BeautifulHouses.java from office laptop
*/