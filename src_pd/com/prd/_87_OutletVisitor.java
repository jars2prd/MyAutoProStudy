package com.prd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Refer _87_MaxOutletWithoutTravelling.java from office laptop

public class _87_OutletVisitor {
	static class Graph {
        private final Map<Integer, List<Integer>> adjacencyList;

        public Graph(int vertices) {
            adjacencyList = new HashMap<>();
            for (int i = 1; i <= vertices; i++) {
                adjacencyList.put(i, new ArrayList<>());
            }
        }

        public void addEdge(int u, int v) {
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
        }

        public int getMaxOutlets(int startVertex, int k) {
            boolean[] visited = new boolean[adjacencyList.size() + 1];
            return dfs(startVertex, -1, visited, k, 0);
        }

        private int dfs(int vertex, int parent, boolean[] visited, int k, int count) {
            visited[vertex] = true;
            int outlets = 0;
            if (vertex != 1 && adjacencyList.get(vertex).size() == 1) {
                outlets = 1;
            }
            for (int neighbor : adjacencyList.get(vertex)) {
                if (!visited[neighbor] && neighbor != parent) {
                    outlets += dfs(neighbor, vertex, visited, k, count + 1);
                }
            }
            return outlets;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(3, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);

        int maxOutlets = graph.getMaxOutlets(1, 3);
        System.out.println("Maximum outlets that can be visited: " + maxOutlets);
    }
}
