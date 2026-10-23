package com.prd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Refer _87_MaxOutletWithoutTravelling.java from office laptop

public class _87_MaxOutletWithoutTravelling {
	static int maxCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Number of outlets
        int num = scanner.nextInt();

        // Number of roads
        int roads = scanner.nextInt();

        // Build adjacency list
        List<Integer>[] graph = new ArrayList[num + 1];
        for (int i = 0; i <= num; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        // Read all roads (undirected edges)
        for (int i = 0; i < roads; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }

        // Try DFS from each node
        for (int i = 1; i <= num; i++) {
            boolean[] visitedEdges = new boolean[num + 1];
            dfs(i, graph, visitedEdges, 1); // start with current node
        }

        System.out.println(maxCount);
    }

    private static void dfs(int node, List<Integer>[] graph, boolean[] visited, int count) {
        maxCount = Math.max(maxCount, count);

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                dfs(neighbor, graph, visited, count + 1);
                visited[neighbor] = false;
            }
        }
    }
	
	
}
