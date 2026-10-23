package com.prd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _49_MinDistToVisitAllRetailers {
	static int n;
    static double[] x;
    static double headX, headY;
    static double INF = 1e100;
    static double[][][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int startPos = Integer.parseInt(br.readLine().trim()) - 1;
        n = Integer.parseInt(br.readLine().trim());

        x = new double[n];
        String[] coords = br.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) {
            x[i] = Double.parseDouble(coords[i]);
        }

        headX = Double.parseDouble(br.readLine().trim());
        headY = Double.parseDouble(br.readLine().trim());

        dp = new double[n][n][2][3];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int hv = 0; hv < 2; hv++)
                    Arrays.fill(dp[i][j][hv], -1);

        double ans = solve(startPos, startPos, 0, 0);
        System.out.printf("%.6f\n", ans);
    }

    static double solve(int L, int R, int hv, int pos) {
        // Finish only if all x-axis visited and head visited
        if (L == 0 && R == n - 1 && hv == 1) return 0.0;

        if (dp[L][R][hv][pos] >= 0) return dp[L][R][hv][pos];

        double best = INF;
        double curX, curY;

        if (pos == 0) { curX = x[L]; curY = 0; }
        else if (pos == 1) { curX = x[R]; curY = 0; }
        else { curX = headX; curY = headY; }

        // Extend to the left
        if (L > 0) {
            best = Math.min(best,
                dist(curX, curY, x[L - 1], 0) + solve(L - 1, R, hv, 0));
        }
        // Extend to the right
        if (R < n - 1) {
            best = Math.min(best,
                dist(curX, curY, x[R + 1], 0) + solve(L, R + 1, hv, 1));
        }
        // Visit head if not visited yet
        if (hv == 0) {
            best = Math.min(best,
                dist(curX, curY, headX, headY) + solve(L, R, 1, 2));
        }

        return dp[L][R][hv][pos] = best;
    }

    static double dist(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2, dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }
}

/* Answer is not matching exactly
ip:
1
3
0 1 2
1
1
op: 3.414214

correctoutput: 3.828427*/
