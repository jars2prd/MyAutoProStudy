package com.myprog;

import java.util.*;

public class _40ShortestPathWithMagic {

    static class State implements Comparable<State> {
        int city;
        int distance;
        int spellsUsed;

        public State(int city, int distance, int spellsUsed) {
            this.city = city;
            this.distance = distance;
            this.spellsUsed = spellsUsed;
        }

        @Override
        public int compareTo(State other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static int findShortestRoute(int numCities, int cityA, int cityB, int numMagic, int[][] roads) {
        // Adjacency list to represent the graph
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < numCities; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int len = road[2];
            adj.get(u).add(new int[]{v, len});
            adj.get(v).add(new int[]{u, len}); // Bidirectional
        }

        // dist[city][spellsUsed]
        int[][] dist = new int[numCities][numMagic + 1];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<State> pq = new PriorityQueue<>();

        dist[cityA][0] = 0;
        pq.add(new State(cityA, 0, 0));

        while (!pq.isEmpty()) {
            State current = pq.poll();
            int u = current.city;
            int d = current.distance;
            int s = current.spellsUsed;

            if (d > dist[u][s]) {
                continue;
            }

            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int len = neighbor[1];

                // Option 1: Don't use magic spell on this edge
                if (dist[u][s] != Integer.MAX_VALUE && dist[u][s] + len < dist[v][s]) {
                    dist[v][s] = dist[u][s] + len;
                    pq.add(new State(v, dist[v][s], s));
                }

                // Option 2: Use magic spell on this edge (if spells available)
                if (s < numMagic && dist[u][s] != Integer.MAX_VALUE && dist[u][s] < dist[v][s + 1]) {
                    dist[v][s + 1] = dist[u][s]; // Distance becomes 0 for this edge
                    pq.add(new State(v, dist[v][s + 1], s + 1));
                }
            }
        }

        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i <= numMagic; i++) {
            minDistance = Math.min(minDistance, dist[cityB][i]);
        }

        return (minDistance == Integer.MAX_VALUE) ? -1 : minDistance;
    }

    public static void main(String[] args) {
        // Example usage based on the provided input:
        int numCities = 5;
        int cityA = 0;
        int cityB = 3;
        
        int numMagic = 1;
        int numRoads = 5; // This is inferred from the number of road lines provided
        int[][] roads = {
            {0, 1, 1},
            {0, 4, 1},
            {1, 2, 2},
            {2, 3, 4},
            {4, 3, 7}
        };

        int result = findShortestRoute(numCities, cityA, cityB, numMagic, roads);
        System.out.println(result); // Expected Output: 1
    }
}