package com.prd;

import java.util.*;

public class ShortestPathSalesperson {
	public static void main(String[] args) {
			Scanner s = new Scanner(System.in);
			int k = s.nextInt() - 1, n = s.nextInt();
			double[][] p = new double[n + 1][2], dp = new double[1 << (n + 1)][n + 1];
		for (int i = 0; i < n; i++)
			p[i][0] = s.nextInt();
		p[n][0] = s.nextInt();
		p[n][1] = s.nextInt();
		for (double[] r : dp)
			Arrays.fill(r, 1e9);
		dp[1 << k][k] = 0;
		for (int m = 0; m < 1 << (n + 1); m++)
			for (int u = 0; u <= n; u++)
				if ((m & (1 << u)) > 0 && dp[m][u] < 1e9)
					for (int v = 0; v <= n; v++)
						if ((m & (1 << v)) == 0)
							dp[m | (1 << v)][v] = Math.min(dp[m | (1 << v)][v],
									dp[m][u] + Math.sqrt((p[u][0] - p[v][0]) * (p[u][0] - p[v][0])
											+ (p[u][1] - p[v][1]) * (p[u][1] - p[v][1])));
		double a = 1e9;
		for (int i = 0; i <= n; i++)
			a = Math.min(a, dp[(1 << (n + 1)) - 1][i]);
		System.out.printf("%.6f", a);
	}
}
