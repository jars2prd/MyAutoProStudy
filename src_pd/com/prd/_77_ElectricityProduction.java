package com.prd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class _77_ElectricityProduction {
	static int exact2DDP(int V, int M, int N, int[] vols, int[] masses, int[] values) {
        int[][] dp = new int[M + 1][V + 1];
        for (int m = 0; m <= M; m++) {
            Arrays.fill(dp[m], -1);
        }
        dp[0][0] = 0;

        for (int i = 0; i < N; i++) {
            int vi = vols[i];
            int mi = masses[i];
            int ei = values[i];
            for (int m = M; m >= mi; m--) {
                for (int v = V; v >= vi; v--) {
                    if (dp[m - mi][v - vi] != -1) {
                        int cand = dp[m - mi][v - vi] + ei;
                        if (cand > dp[m][v]) {
                            dp[m][v] = cand;
                        }
                    }
                }
            }
        }

        int best = 0;
        for (int m = 0; m <= M; m++) {
            for (int v = 0; v <= V; v++) {
                if (dp[m][v] > best) {
                    best = dp[m][v];
                }
            }
        }
        return best;
    }

    static int sparsePrunedDP(int V, int M, int N, int[] vols, int[] masses, int[] values) {
        List<int[]> states = new ArrayList<int[]>();
        states.add(new int[]{0, 0, 0}); // mass, volume, electricity

        for (int i = 0; i < N; i++) {
            int vi = vols[i];
            int mi = masses[i];
            int ei = values[i];
            List<int[]> newStates = new ArrayList<int[]>();
            for (int[] st : states) {
                int nm = st[0] + mi;
                int nv = st[1] + vi;
                if (nm <= M && nv <= V) {
                    newStates.add(new int[]{nm, nv, st[2] + ei});
                }
            }
            List<int[]> allStates = new ArrayList<int[]>(states);
            allStates.addAll(newStates);

            // sort by mass, then volume, then -electricity
            Collections.sort(allStates, new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    if (a[0] != b[0]) return a[0] - b[0];
                    if (a[1] != b[1]) return a[1] - b[1];
                    return b[2] - a[2];
                }
            });

            List<int[]> pruned = new ArrayList<int[]>();
            for (int[] st : allStates) {
                if (!pruned.isEmpty()) {
                    int[] last = pruned.get(pruned.size() - 1);
                    if (last[0] == st[0] && last[1] == st[1]) {
                        if (st[2] > last[2]) {
                            pruned.set(pruned.size() - 1, st);
                        }
                        continue;
                    }
                }
                boolean dominated = false;
                for (int[] p : pruned) {
                    if (p[0] <= st[0] && p[1] <= st[1] && p[2] >= st[2]) {
                        dominated = true;
                        break;
                    }
                }
                if (!dominated) {
                    // remove states dominated by this one
                    Iterator<int[]> it = pruned.iterator();
                    while (it.hasNext()) {
                        int[] p = it.next();
                        if (p[0] >= st[0] && p[1] >= st[1] && p[2] <= st[2]) {
                            it.remove();
                        }
                    }
                    pruned.add(st);
                }
            }
            states = pruned;
        }

        int best = 0;
        for (int[] st : states) {
            if (st[2] > best) best = st[2];
        }
        return best;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt(); // reactorCapacity
        int M = sc.nextInt(); // criticalMass
        int N = sc.nextInt(); // totalVial

        int volumeSize = sc.nextInt();
        int[] vols = new int[volumeSize];
        for (int i = 0; i < volumeSize; i++) vols[i] = sc.nextInt();

        int massSize = sc.nextInt();
        int[] masses = new int[massSize];
        for (int i = 0; i < massSize; i++) masses[i] = sc.nextInt();

        int elecSize = sc.nextInt();
        int[] values = new int[elecSize];
        for (int i = 0; i < elecSize; i++) values[i] = sc.nextInt();

        // Safety: ensure arrays match N
        if (!(vols.length == masses.length && masses.length == values.length && values.length == N)) {
            N = Math.min(Math.min(vols.length, masses.length), Math.min(values.length, N));
            vols = Arrays.copyOf(vols, N);
            masses = Arrays.copyOf(masses, N);
            values = Arrays.copyOf(values, N);
        }

        // Choose algorithm
        long threshold = 8000000L; // safe limit for exact DP
        int ans;
        if ((long) (M + 1) * (V + 1) <= threshold) {
            ans = exact2DDP(V, M, N, vols, masses, values);
        } else {
            ans = sparsePrunedDP(V, M, N, vols, masses, values);
        }

        System.out.println(ans);
        sc.close();
    }
}

/*
100
15
5
5
50 40 30 20 10
5
1 2 3 9 5
5
300 480 270 200 180

OP: 960
*/
