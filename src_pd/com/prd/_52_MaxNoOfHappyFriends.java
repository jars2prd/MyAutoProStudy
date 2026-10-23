package com.prd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class _52_MaxNoOfHappyFriends {
	static List<Integer>[] adj; // guest -> list of liked fruits
    static boolean[] visited;   // visited fruits during DFS
    static int[] match;         // fruit -> guest assigned
    static int numFriends, numFruits;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Number of guests
        numFriends = sc.nextInt();
        adj = new ArrayList[numFriends + 1];
        for (int i = 0; i <= numFriends; i++) {
            adj[i] = new ArrayList<Integer>();
        }

        // Read preferences for each guest
        List<Set<Integer>> prefs = new ArrayList<Set<Integer>>();
        for (int i = 0; i < numFriends; i++) {
            int cnt = sc.nextInt();
            Set<Integer> set = new HashSet<Integer>();
            for (int j = 0; j < cnt; j++) {
                set.add(sc.nextInt());
            }
            prefs.add(set);
        }

        // Cake size
        int numN = sc.nextInt();
        int numM = sc.nextInt();
        numFruits = numN * numM;

        // Build graph: guest -> liked fruits
        for (int g = 1; g <= numFriends; g++) {
            for (int fruit : prefs.get(g - 1)) {
                if (fruit >= 1 && fruit <= numFruits) {
                    adj[g].add(fruit);
                }
            }
        }

        // Matching array
        match = new int[numFruits + 1]; // match[fruit] = guest
        Arrays.fill(match, 0);

        // Try to match each guest
        int result = 0;
        for (int g = 1; g <= numFriends; g++) {
            visited = new boolean[numFruits + 1];
            if (dfs(g)) {
                result++;
            }
        }

        System.out.println(result);
    }

    // DFS to try assigning a fruit to guest u
    static boolean dfs(int u) {
        for (int fruit : adj[u]) {
            if (!visited[fruit]) {
                visited[fruit] = true;
                if (match[fruit] == 0 || dfs(match[fruit])) {
                    match[fruit] = u;
                    return true;
                }
            }
        }
        return false;
    }
}

/*
3
3 1 2 3
1 2
1 1
2
2
OP: 3
*/
