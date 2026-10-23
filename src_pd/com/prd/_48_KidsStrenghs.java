package com.prd;

import java.util.Scanner;

public class _48_KidsStrenghs {
	static int[] parent;
    static int[] rank;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Number of players
        int n = sc.nextInt();
        long[] strength = new long[n + 1]; // 1-based indexing

        for (int i = 1; i <= n; i++) {
            strength[i] = sc.nextLong();
        }

        // numCards and pairCount (pairCount = 2 * numCards, but not used directly)
        int numCards = sc.nextInt();
        int pairCount = sc.nextInt(); // not really needed in logic

        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        // Read each card's pair and union them
        for (int i = 0; i < numCards; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            union(a, b);
        }

        // Store total strength per root
        long[] teamStrength = new long[n + 1];
        long maxPower = 0;

        for (int i = 1; i <= n; i++) {
            int root = find(i);
            teamStrength[root] += strength[i];
            if (teamStrength[root] > maxPower) {
                maxPower = teamStrength[root];
            }
        }

        System.out.println(maxPower);
        sc.close();
    }

    // DSU find with path compression
    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // DSU union by rank
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return;

        if (rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB;
        } else if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
        } else {
            parent[rootB] = rootA;
            rank[rootA]++;
        }
    }
}

/*
Input
6
11 2 3 15 8 22
3 2
1 2
2 3
4 5

Output: 23
*/