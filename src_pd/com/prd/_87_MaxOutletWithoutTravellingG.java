package com.prd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//Refer _87_MaxOutletWithoutTravelling.java from office laptop

public class _87_MaxOutletWithoutTravellingG {
	
	static int V;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read num (total number of outlets, including warehouse)
        int num = scanner.nextInt();
        V = num;

        // Read kourtlets (not used in this simplified solution)
        int kourtlets = scanner.nextInt();

        // The third line of input seems to be for legacy or a different problem context.
        // We'll ignore it based on the core problem of finding the longest path.
        // int num1 = scanner.nextInt();
        // int kourtlets1 = scanner.nextInt();

        // Initialize adjacency list
        adj = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adj[i] = new ArrayList<>();
        }

        // Read the roads (edges)
        // The number of roads 'M' is not explicitly given, but we can assume
        // the input ends after reading all roads based on the num_run example.
        // The problem states 'The next M lines', but doesn't say what M is.
        // The example input has 4 outlets and 3 edges, which is a tree.
        // Let's read edges until there's no more input.
        while (scanner.hasNextInt()) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        // --- Algorithm to find the diameter of a tree ---
        // 1. Start a BFS/DFS from an arbitrary node (e.g., node 1) to find the farthest node.
        // 2. Start a second BFS/DFS from that farthest node to find the new farthest node.
        // 3. The distance between the two farthest nodes is the diameter.
        
        // Step 1: Find the farthest node from an arbitrary starting point (let's use 1)
        int startNode = 1;
        int[] dists1 = bfs(startNode);

        int farthestNode = -1;
        int maxDist = -1;
        for (int i = 1; i <= V; i++) {
            if (dists1[i] > maxDist) {
                maxDist = dists1[i];
                farthestNode = i;
            }
        }
        
        // Step 2: Find the farthest node from the one found in step 1
        int[] dists2 = bfs(farthestNode);
        
        int diameter = -1;
        for (int i = 1; i <= V; i++) {
            if (dists2[i] > diameter) {
                diameter = dists2[i];
            }
        }

        // The number of outlets visited is the number of nodes in the longest path, which is diameter + 1.
        System.out.println(diameter + 1);

        scanner.close();
    }

    // Breadth-First Search to find distances from a source node
    private static int[] bfs(int start) {
        int[] dists = new int[V + 1];
        boolean[] visited = new boolean[V + 1];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= V; i++) {
            dists[i] = -1; // Initialize distances to -1
        }
        
        dists[start] = 0;
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : adj[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    dists[v] = dists[u] + 1;
                    queue.add(v);
                }
            }
        }
        return dists;
    }
}
