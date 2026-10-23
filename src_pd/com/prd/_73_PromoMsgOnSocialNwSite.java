package com.prd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class _73_PromoMsgOnSocialNwSite {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int users = sc.nextInt();            // Number of users N
        int numg = sc.nextInt();             // Number of pairs of friends (not really used here separately)
        int constx = sc.nextInt();           // Number of edges

        // Build adjacency list for the directed graph
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < users; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < numg; i++) {
            int user1 = sc.nextInt();
            int user2 = sc.nextInt();
            graph.get(user1).add(user2);
        }

        int maxReach = -1;
        int resultUser = -1;

        // For each user, do BFS to find reachable users count
        for (int user = 0; user < users; user++) {
            int reach = bfs(user, graph, users);
            if (reach > maxReach) {
                maxReach = reach;
                resultUser = user;
            }
        }

        System.out.println(resultUser);
        sc.close();
    }

    private static int bfs(int start, List<List<Integer>> graph, int users) {
        boolean[] visited = new boolean[users];
        Queue<Integer> queue = new LinkedList<Integer>();

        visited[start] = true;
        queue.offer(start);
        int count = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int friend : graph.get(current)) {
                if (!visited[friend]) {
                    visited[friend] = true;
                    queue.offer(friend);
                    count++;
                }
            }
        }

        return count;
    }
}

/*
5
4 2
0 1
3 4
1 2
2 1

OP: 0
*/