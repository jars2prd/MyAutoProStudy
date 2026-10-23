package com.prd;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _75_DistanceOfAllBuildingFromServerBuilding {
	 // Directions: up, down, left, right
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read rows and columns
        int M = sc.nextInt();
        int N = sc.nextInt();

        int[][] grid = new int[M][N];
        int[][] dist = new int[M][N];
        Queue<int[]> queue = new LinkedList<int[]>();

        // Read the grid and initialize BFS queue with server locations
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = sc.nextInt();
                dist[i][j] = -1; // -1 means unvisited

                if (grid[i][j] == 1) {
                    // Server building: distance = 0
                    dist[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // BFS from all server buildings at once
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                // Check bounds and unvisited
                if (nr >= 0 && nr < M && nc >= 0 && nc < N && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        // Print result
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(dist[i][j]);
                if (j < N - 1) System.out.print(" ");
            }
            System.out.println();
        }
    }
}

/*
4 4
0 0 0 0
0 1 0 0
0 0 1 0
0 0 1 0

OP:
2 1 2 3
1 0 1 2
2 1 0 1
2 1 0 1
*/