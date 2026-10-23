package com.prd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class _76_AlumniDinner {
	static int N;
    static int[] likes;
    static int[] visited; // 0 = unvisited, 1 = visiting, 2 = visited
    static List<Integer> bestCycle = new ArrayList<Integer>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        likes = new int[N + 1]; // 1-indexed
        visited = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            likes[i] = sc.nextInt();
        }

        // Check each alumnus for cycles
        for (int i = 1; i <= N; i++) {
            if (visited[i] == 0) {
                findCycle(i);
            }
        }

        // Sort the result in ascending order
        Collections.sort(bestCycle);

        // Print output
        for (int i = 0; i < bestCycle.size(); i++) {
            System.out.print(bestCycle.get(i));
            if (i < bestCycle.size() - 1) System.out.print(" ");
        }
    }

    private static void findCycle(int start) {
        Map<Integer, Integer> orderMap = new HashMap<Integer, Integer>(); // node -> step index
        int current = start;
        int step = 0;

        while (true) {
            if (visited[current] == 1) {
                // Cycle detected
                int cycleStartStep = orderMap.get(current);
                List<Integer> cycle = new ArrayList<Integer>();
                for (Map.Entry<Integer, Integer> e : orderMap.entrySet()) {
                    if (e.getValue() >= cycleStartStep) {
                        cycle.add(e.getKey());
                    }
                }
                updateBestCycle(cycle);
                break;
            } else if (visited[current] == 2) {
                // Already processed node
                break;
            }

            visited[current] = 1; // mark as visiting
            orderMap.put(current, step++);
            current = likes[current];
        }

        // Mark all as fully visited
        for (int node : orderMap.keySet()) {
            visited[node] = 2;
        }
    }

    private static void updateBestCycle(List<Integer> cycle) {
        if (cycle.size() > bestCycle.size()) {
            bestCycle = new ArrayList<Integer>(cycle);
        } else if (cycle.size() == bestCycle.size()) {
            List<Integer> tempCycle = new ArrayList<Integer>(cycle);
            Collections.sort(tempCycle);
            List<Integer> sortedBest = new ArrayList<Integer>(bestCycle);
            Collections.sort(sortedBest);
            if (tempCycle.toString().compareTo(sortedBest.toString()) < 0) {
                bestCycle = new ArrayList<Integer>(cycle);
            }
        }
    }
}

/*
4
2 3 4 1

OP: 1 2 3 4
*/
