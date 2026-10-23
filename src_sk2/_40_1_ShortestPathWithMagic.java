package com.myprog;

import java.util.*;

public class _40_1_ShortestPathWithMagic {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numCities = scanner.nextInt(); // N
        int cityA = scanner.nextInt(); // start city
        int cityB = scanner.nextInt(); // end city
        int numMagic = scanner.nextInt(); // K
        int numRoads = scanner.nextInt();
        int roadProp = scanner.nextInt(); // R, always 3 based on problem

        // Adjacency list to represent the graph: List<int[]> where int[] is {neighbor, weight}
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < numCities; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < numRoads; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int len = scanner.nextInt();
            adj.get(u).add(new int[]{v, len});
            adj.get(v).add(new int[]{u, len}); // Bidirectional road
        }

        // Implement the shortest path algorithm (e.g., Dijkstra's with spell states)
        // A common approach for Dijkstra's with spells uses a custom Node class or array:
        // class State { int city; int cost; int spellsUsed; }
        // PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
        // int[][] minCost = new int[numCities][numMagic + 1]; // minCost[city][spellsUsed]

        // Initialize minCost with infinity and minCost[cityA][0] = 0;
        // Add initial state to pq: new State(cityA, 0, 0)

        // Loop while pq is not empty:
        //  Extract min state
        //  If current cost > minCost[currentCity][currentSpellsUsed], continue
        //  For each neighbor:
        //      Calculate new cost with and without spell use
        //      Update minCost and add to pq if a shorter path is found

        // After the algorithm, find the minimum distance to cityB across all spell uses.
        int shortestDistance = -1; // Or find from minCost[cityB][...]

        System.out.println(shortestDistance);

        scanner.close();
    }
}