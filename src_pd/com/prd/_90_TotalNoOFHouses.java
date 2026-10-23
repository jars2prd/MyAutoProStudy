package com.prd;

import java.util.Scanner;

public class _90_TotalNoOFHouses {
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

        boolean[][] visited = new boolean[rows][cols];
        int houseCount = 0;

        // DFS to find connected components (houses)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    dfs(grid, visited, i, j, rows, cols);
                    houseCount++;
                }
            }
        }

        System.out.println(houseCount);
    }

    private static void dfs(int[][] grid, boolean[][] visited, int x, int y, int rows, int cols) {
        if (x < 0 || x >= rows || y < 0 || y >= cols ||
            grid[x][y] == 0 || visited[x][y]) {
            return;
        }

        visited[x][y] = true;

        // Visit neighbors (up, down, left, right)
        dfs(grid, visited, x + 1, y, rows, cols);
        dfs(grid, visited, x - 1, y, rows, cols);
        dfs(grid, visited, x, y + 1, rows, cols);
        dfs(grid, visited, x, y - 1, rows, cols);
    }
}

/*
5 5
0 0 0 0 0
0 1 1 0 0
0 0 1 1 0
0 0 0 0 0
0 0 0 0 0

OP: 1
*/