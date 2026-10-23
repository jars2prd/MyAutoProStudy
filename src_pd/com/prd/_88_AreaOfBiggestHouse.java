package com.prd;

import java.util.Scanner;

public class _88_AreaOfBiggestHouse {
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
        int maxArea = 0;

        // Traverse the grid to find connected components
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area = dfs(grid, visited, i, j, rows, cols);
                    if (area > maxArea) {
                        maxArea = area;
                    }
                }
            }
        }

        System.out.println(maxArea);
    }

    private static int dfs(int[][] grid, boolean[][] visited, int x, int y, int rows, int cols) {
        if (x < 0 || x >= rows || y < 0 || y >= cols ||
            grid[x][y] == 0 || visited[x][y]) {
            return 0;
        }

        visited[x][y] = true;

        // Count this cell and all connected cells
        int area = 1;
        area += dfs(grid, visited, x + 1, y, rows, cols);
        area += dfs(grid, visited, x - 1, y, rows, cols);
        area += dfs(grid, visited, x, y + 1, rows, cols);
        area += dfs(grid, visited, x, y - 1, rows, cols);

        return area;
    }
}

/*
5 5
0 0 0 0 0
0 1 1 0 0
0 0 0 0 0
0 0 1 1 0
0 0 1 0 0

OP: 3
*/