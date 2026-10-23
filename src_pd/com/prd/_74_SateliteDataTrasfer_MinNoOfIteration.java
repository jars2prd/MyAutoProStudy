package com.prd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class _74_SateliteDataTrasfer_MinNoOfIteration {
	static List<List<Integer>> graph;
    static boolean[] visited;
    static int[] subtreeSize;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt(); // not used except reading input

        graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < N - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int maxSatellites = sc.nextInt(); // only constraint in problem, not used in logic

        visited = new boolean[N];
        subtreeSize = new int[N];

        // Build ordered children list
        List<List<Integer>> children = new ArrayList<List<Integer>>();
        for (int i = 0; i < N; i++) {
            children.add(new ArrayList<Integer>());
        }
        buildTree(0, -1, children);

        // Sort children of each node by subtree size (largest first)
        for (int i = 0; i < N; i++) {
            final List<Integer> ch = children.get(i);
            Collections.sort(ch, new Comparator<Integer>() {
                public int compare(Integer a, Integer b) {
                    return subtreeSize[b] - subtreeSize[a];
                }
            });
        }

        // Now simulate
        System.out.println(simulate(children, N));
    }

    // Build BFS tree and compute subtree sizes
    private static void buildTree(int node, int parent, List<List<Integer>> children) {
        visited[node] = true;
        subtreeSize[node] = 1;
        for (int neighbor : graph.get(node)) {
            if (neighbor != parent) {
                children.get(node).add(neighbor);
                buildTree(neighbor, node, children);
                subtreeSize[node] += subtreeSize[neighbor];
            }
        }
    }

    private static int simulate(List<List<Integer>> children, int N) {
        boolean[] hasData = new boolean[N];
        Map<Integer, Queue<Integer>> remaining = new HashMap<Integer, Queue<Integer>>();
        for (int i = 0; i < N; i++) {
            remaining.put(i, new LinkedList<Integer>(children.get(i)));
        }

        hasData[0] = true;
        int totalWithData = 1;
        int iterations = 0;

        while (totalWithData < N) {
            List<Integer> newData = new ArrayList<Integer>();

            for (int i = 0; i < N; i++) {
                if (hasData[i] && !remaining.get(i).isEmpty()) {
                    int child = remaining.get(i).poll();
                    newData.add(child);
                }
            }

            for (int node : newData) {
                if (!hasData[node]) {
                    hasData[node] = true;
                    totalWithData++;
                }
            }

            iterations++;
        }

        return iterations;
    }
}

/*
9 2
0 1
0 2
0 3
1 4
2 5
2 6
2 7
3 8
3

OP: 4
*/