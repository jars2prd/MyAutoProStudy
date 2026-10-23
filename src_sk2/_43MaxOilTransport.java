package com.myprog;
import java.util.*;

import java.util.*;
//Not working, output is 90 instead of 60
public class _43MaxOilTransport {

    // Represents an edge (pipe) in the network
    static class Edge {
        int to;
        int rate; // capacity of the pipe

        public Edge(int to, int rate) {
            this.to = to;
            this.rate = rate;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numStations = scanner.nextInt(); // N: number of stations
        int baseRefinery = scanner.nextInt(); // S: index of base refinery (root)

        int numConnections = scanner.nextInt(); // M: number of connections
        int charCon = scanner.nextInt(); // P: characteristics of each connection (always 3)

        // Adjacency list to represent the tree/graph
        // We use ArrayList of ArrayLists to store edges (pipes)
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i <= numStations; i++) { // Stations are 1-indexed in problem, adjust for 0-indexed ArrayList
            adj.add(new ArrayList<>());
        }

        // Read pipe connections
        for (int i = 0; i < numConnections; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int rate = scanner.nextInt();
            // Since it's a tree (undirected pipes for flow, though flow is from root to leaves), add edges in both directions
            adj.get(start).add(new Edge(end, rate));
            adj.get(end).add(new Edge(start, rate));
        }

        long totalMaxFlow = 0;
        // Start DFS from the base refinery to calculate flow to each leaf
        // We initialize the current path's minimum rate to a very large value
        totalMaxFlow = dfs(baseRefinery, -1, Integer.MAX_VALUE, adj);

        System.out.println(totalMaxFlow);

        scanner.close();
    }

    /**
     * Performs a Depth-First Search (DFS) to find the maximum flow to leaves.
     *
     * @param u The current node.
     * @param parent The parent of the current node (to avoid going back).
     * @param currentMinRate The minimum rate found on the path from the root to the current node.
     * @param adj The adjacency list representing the network.
     * @return The sum of maximum flows to all leaves reachable from 'u'.
     */
    private static long dfs(int u, int parent, int currentMinRate, List<List<Edge>> adj) {
        long sumOfLeafFlows = 0;
        boolean isLeaf = true; // Assume current node is a leaf until a child is found

        for (Edge edge : adj.get(u)) {
            int v = edge.to;
            int rate = edge.rate;

            if (v != parent) { // Avoid going back to the parent
                isLeaf = false; // Current node is not a leaf if it has a child
                // Update the minimum rate for the path to the child
                int newMinRate = Math.min(currentMinRate, rate);
                sumOfLeafFlows += dfs(v, u, newMinRate, adj);
            }
        }

        // If 'u' is a leaf node (no children or all children are parents), add its currentMinRate to the total
        if (isLeaf) {
            return currentMinRate;
        }

        return sumOfLeafFlows;
    }
}