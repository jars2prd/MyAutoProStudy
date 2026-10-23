package com.myprog;

import java.util.*;

//Program 34 : Maximum Toll : To find maximum or common road/path between different cities.
public class _34MaxTollRoad {

    static int[] parent;
    static int[] subtreeSize;
    static List<List<Integer>> adj;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // Number of cities
        int R = scanner.nextInt(); // Number of roads (always N-1 for a tree)
        int numCitiesConnectedByRoad = scanner.nextInt(); // Always 2

        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        List<int[]> roads = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
            roads.add(new int[]{u, v});
        }

        parent = new int[N + 1];
        subtreeSize = new int[N + 1];

        // Perform DFS to calculate subtree sizes
        dfs(1, 0); // Start DFS from city 1, with 0 as its parent

        long maxTollRevenue = -1;
        int[] resultRoad = new int[2];

        // Calculate toll for each road
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];

            long toll;
            // Determine which node is the child in the DFS tree
            if (parent[v] == u) { // v is child of u
                toll = (long) subtreeSize[v] * (N - subtreeSize[v]);
            } else { // u is child of v
                toll = (long) subtreeSize[u] * (N - subtreeSize[u]);
            }

            if (toll > maxTollRevenue) {
                maxTollRevenue = toll;
                resultRoad[0] = Math.min(u, v);
                resultRoad[1] = Math.max(u, v);
            } else if (toll == maxTollRevenue) {
                int currentMin = Math.min(u, v);
                int currentMax = Math.max(u, v);
                if (currentMin < resultRoad[0] || (currentMin == resultRoad[0] && currentMax < resultRoad[1])) {
                    resultRoad[0] = currentMin;
                    resultRoad[1] = currentMax;
                }
            }
        }

        System.out.println(resultRoad[0] + " " + resultRoad[1]);

        scanner.close();
    }

    // DFS to calculate subtree sizes
    public static void dfs(int u, int p) {
        parent[u] = p;
        subtreeSize[u] = 1;
        for (int v : adj.get(u)) {
            if (v != p) {
                dfs(v, u);
                subtreeSize[u] += subtreeSize[v];
            }
        }
    }
}