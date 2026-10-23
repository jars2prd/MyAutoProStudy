package com.prd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class _66_TotalDistCoveredByBus {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        if (first == null || first.trim().isEmpty()) {
            System.out.println(0);
            return;
        }
        StringTokenizer st = new StringTokenizer(first);
        int num = Integer.parseInt(st.nextToken());
        // constM is present in input but not used (always 2)
        if (st.hasMoreTokens()) st.nextToken();

        if (num <= 0) {
            System.out.println(0);
            return;
        }

        int[][] intervals = new int[num][2];
        for (int i = 0; i < num; i++) {
            String line = br.readLine();
            while (line != null && line.trim().isEmpty()) line = br.readLine();
            if (line == null) break;
            st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // ensure a <= b (problem states busStop0 < busStop1)
            intervals[i][0] = a;
            intervals[i][1] = b;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] x, int[] y) {
                if (x[0] != y[0]) return x[0] - y[0];
                return x[1] - y[1];
            }
        });

        long total = 0L;
        int curStart = intervals[0][0];
        int curEnd = intervals[0][1];

        for (int i = 1; i < num; i++) {
            int s = intervals[i][0];
            int e = intervals[i][1];
            if (s <= curEnd) { // overlapping or touching
                if (e > curEnd) curEnd = e;
            } else { // disjoint
                total += (long)(curEnd - curStart);
                curStart = s;
                curEnd = e;
            }
        }
        // add last
        total += (long)(curEnd - curStart);

        System.out.println(total);
    }
}

/*
3 2
2 4
3 5
6 7
OP: 4
*/
