package com.myprog;

public class _60MazeSolver {

    public static boolean hasPathToCheese(int[][] maze) {
        if (maze == null || maze.length == 0 || maze[0].length == 0) {
            return false; // Handle empty or invalid maze
        }

        int rows = maze.length;
        int cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols];

        // Start DFS from (0,0)
        return dfs(maze, 0, 0, rows, cols, visited);
    }

    private static boolean dfs(int[][] maze, int r, int c, int rows, int cols, boolean[][] visited) {
        // Base Cases
    	
    	System.out.println("Row and Col :"+r+" "+c);
        if (r < 0 || r >= rows || c < 0 || c >= cols || maze[r][c] == 0 || visited[r][c]) {
            return false; // Out of bounds, wall, or already visited
        }
        if (maze[r][c] == 9) {
            return true; // Found the cheese
        }

        visited[r][c] = true; // Mark current cell as visited

        // Explore neighbors
        if (dfs(maze, r + 1, c, rows, cols, visited)) return true; // Down
        if (dfs(maze, r - 1, c, rows, cols, visited)) return true; // Up
        if (dfs(maze, r, c + 1, rows, cols, visited)) return true; // Right
        if (dfs(maze, r, c - 1, rows, cols, visited)) return true; // Left

        return false; // No path found from this cell
    }

    public static void main(String[] args) {
        // Example from the problem description
        int[][] maze1 = {
            {1, 0, 1, 1, 1, 0, 0, 1},
            {1, 0, 0, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 1, 0, 9, 0, 1, 1},
            {1, 1, 1, 0, 1, 0, 0, 1},
            {1, 0, 1, 0, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1}
        };

        if (hasPathToCheese(maze1)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}