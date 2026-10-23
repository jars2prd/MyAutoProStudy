package com.prd;

import java.util.Scanner;

public class _94_ConnectedCities {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of cities (matrix size)
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] matrix = new int[n][m];

        // Read the adjacency matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        boolean[] visited = new boolean[n];
        int clusterCount = 0;

        // Loop through all cities
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(matrix, visited, i);
                clusterCount++;
            }
        }

        // Output the total number of connected clusters
        System.out.println(clusterCount);
    }

    // Depth-First Search (DFS) to mark connected cities
    private static void dfs(int[][] matrix, boolean[] visited, int city) {
        visited[city] = true;

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[city][i] == 1 && !visited[i]) {
                dfs(matrix, visited, i);
            }
        }
    }
}

/*
5 5
0 0 0 0 0
0 0 1 0 0
0 1 0 1 0
0 0 1 0 0
0 0 0 0 0

OP: 3
*/