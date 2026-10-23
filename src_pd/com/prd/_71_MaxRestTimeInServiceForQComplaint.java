package com.prd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class _71_MaxRestTimeInServiceForQComplaint {
	// Edge class to store connected zone and travel time
    static class Edge {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    // Node class for Dijkstra's priority queue
    static class Node implements Comparable<Node> {
        int vertex;
        int dist;

        Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        public int compareTo(Node other) {
            return this.dist - other.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Number of zones
        int numZones = Integer.parseInt(br.readLine().trim());

        // Number of roads and constX (which we ignore, always 1)
        String[] line = br.readLine().trim().split("\\s+");
        int numRoads = Integer.parseInt(line[0]);
        int constX = Integer.parseInt(line[1]); // Always 1

        // Build graph as adjacency list
        List<Edge>[] graph = new ArrayList[numZones + 1];
        for (int i = 1; i <= numZones; i++) {
            graph[i] = new ArrayList<Edge>();
        }

        // Read roads info
        for (int i = 0; i < numRoads; i++) {
            String[] parts = br.readLine().trim().split("\\s+");
            int zoneA = Integer.parseInt(parts[0]);
            int zoneB = Integer.parseInt(parts[1]);
            int travelTime = Integer.parseInt(parts[2]);

            // Add bidirectional edges
            graph[zoneA].add(new Edge(zoneB, travelTime));
            graph[zoneB].add(new Edge(zoneA, travelTime));
        }

        // Number of complaints and constY (always 2)
        String[] complaintsLine = br.readLine().trim().split("\\s+");
        int numComplaints = Integer.parseInt(complaintsLine[0]);
        int constY = Integer.parseInt(complaintsLine[1]); // Always 2

        // Run Dijkstra from zone 1
        int[] dist = dijkstra(graph, numZones, 1);

        // Process complaints
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numComplaints; i++) {
            String[] compParts = br.readLine().trim().split("\\s+");
            int zoneX = Integer.parseInt(compParts[0]);
            int timeX = Integer.parseInt(compParts[1]);

            int roundTripTime = dist[zoneX] * 2;
            int restTime = timeX - roundTripTime;
            if (restTime < 0) restTime = 0;

            result.append(restTime);
            if (i != numComplaints - 1) result.append(" ");
        }

        System.out.println(result.toString());
    }

    // Dijkstra's shortest path algorithm from start node
    private static int[] dijkstra(List<Edge>[] graph, int n, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;
            int currentDist = current.dist;

            if (currentDist > dist[u]) continue;

            for (Edge edge : graph[u]) {
                int v = edge.to;
                int cost = edge.cost;

                if (dist[u] + cost < dist[v]) {
                    dist[v] = dist[u] + cost;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        return dist;
    }
}

/*
4
4 3
1 2 5
2 3 10
3 4 20
1 4 1
2 2
2 65
3 15

OP 55 0
*/