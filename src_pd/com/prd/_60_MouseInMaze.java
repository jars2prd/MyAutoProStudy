package com.prd;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _60_MouseInMaze {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        int[][] maze = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = sc.nextInt();
            }
        }
        
        System.out.println(canReachCheese(maze, rows, cols) ? 1 : 0);
    }
    
    static boolean canReachCheese(int[][] maze, int rows, int cols) {
        if (maze[0][0] == 0) return false;
        
        int[] dr = {-1, 1, 0, 0}; // up, down, left, right
        int[] dc = {0, 0, -1, 1};
        
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(0); // store as r * cols + c
        
        while (!queue.isEmpty()) {
            int pos = queue.poll();
            int r = pos / cols;
            int c = pos % cols;
            
            if (maze[r][c] == 9) return true;
            
            maze[r][c] = 0; // mark visited
            
            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && maze[nr][nc] != 0) {
                    queue.add(nr * cols + nc);
                }
            }
        }
        
        return false;
    }
}

/*
8 8
1 0 1 1 1 0 0 1
1 0 0 0 1 1 1 1
1 0 0 0 0 0 0 0
1 0 1 0 9 0 1 1 
1 1 1 0 1 0 0 1
1 0 1 0 1 1 0 1
1 0 0 0 0 1 0 1
1 1 1 1 1 1 1 1
OP: 1
*/
