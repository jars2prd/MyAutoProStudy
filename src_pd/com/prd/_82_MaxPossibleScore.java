package com.prd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class _82_MaxPossibleScore {
	static int N;
    static long[] val;
    static List<Integer>[] adj;
    static long maxScore = Long.MIN_VALUE;

    static class Pair {
        long maxDown;
        long minDown;
        Pair(long a, long b) { maxDown = a; minDown = b; }
    }

    static Pair dfs(int u, int parent) {
        boolean isLeaf = true;
        long maxDown = val[u];
        long minDown = val[u];

        // Store best two maxDowns and best two minDowns from children for combining leaf-to-leaf
        long bestMax1 = Long.MIN_VALUE, bestMax2 = Long.MIN_VALUE;
        long bestMin1 = Long.MAX_VALUE, bestMin2 = Long.MAX_VALUE;

        for (int v : adj[u]) {
            if (v == parent) continue;
            isLeaf = false;
            Pair child = dfs(v, u);

            // Extend child's path down to current node
            long cand1 = child.maxDown * val[u];
            long cand2 = child.minDown * val[u];

            // Update maxDown/minDown for return
            maxDown = Math.max(maxDown, Math.max(cand1, cand2));
            minDown = Math.min(minDown, Math.min(cand1, cand2));

            // Track top two maxDowns for combination
            if (cand1 > bestMax1) { bestMax2 = bestMax1; bestMax1 = cand1; }
            else if (cand1 > bestMax2) { bestMax2 = cand1; }
            if (cand2 > bestMax1) { bestMax2 = bestMax1; bestMax1 = cand2; }
            else if (cand2 > bestMax2) { bestMax2 = cand2; }

            // Track two smallest for negative*negative possibility
            if (cand1 < bestMin1) { bestMin2 = bestMin1; bestMin1 = cand1; }
            else if (cand1 < bestMin2) { bestMin2 = cand1; }
            if (cand2 < bestMin1) { bestMin2 = bestMin1; bestMin1 = cand2; }
            else if (cand2 < bestMin2) { bestMin2 = cand2; }
        }

        // Combine two different child paths through this node
        if (!isLeaf) {
            if (bestMax2 != Long.MIN_VALUE) {
                maxScore = Math.max(maxScore, bestMax1 * bestMax2 / val[u]);
            }
            if (bestMin2 != Long.MAX_VALUE) {
                maxScore = Math.max(maxScore, bestMin1 * bestMin2 / val[u]);
            }
        }

        return new Pair(maxDown, minDown);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        val = new long[N + 1];
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<Integer>();

        String[] parts = br.readLine().trim().split("\\s+");
        for (int i = 1; i <= N; i++) {
            val[i] = Long.parseLong(parts[i - 1]);
        }

        String[] line3 = br.readLine().trim().split("\\s+");
        int E = Integer.parseInt(line3[0]);
        for (int i = 0; i < E; i++) {
            String[] edge = br.readLine().trim().split("\\s+");
            int a = Integer.parseInt(edge[0]);
            int b = Integer.parseInt(edge[1]);
            adj[a].add(b);
            adj[b].add(a);
        }

        dfs(1, -1);
        System.out.println(maxScore);
    }
}

/*

4
-1 2 3 2
3 2
1 2
1 3
3 4

OP: 12

Expected OP: -12 ... refer _82_MaxPossibleScore.java from office laptop
*/