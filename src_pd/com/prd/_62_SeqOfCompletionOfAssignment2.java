package com.prd;

import java.util.*;

public class _62_SeqOfCompletionOfAssignment2 {

	    public static void main(String[] args) {

	        Scanner sc = new Scanner(System.in);

	        int m = sc.nextInt();
	        int[] available = new int[m];

	        for (int i = 0; i < m; i++)
	            available[i] = sc.nextInt();

	        int n = sc.nextInt();
	        sc.nextInt(); // ignore (same as m)

	        int[][] required = new int[n][m];
	        for (int i = 0; i < n; i++)
	            for (int j = 0; j < m; j++)
	                required[i][j] = sc.nextInt();

	        sc.nextInt(); // ignore
	        sc.nextInt(); // ignore

	        int[][] issued = new int[n][m];
	        for (int i = 0; i < n; i++)
	            for (int j = 0; j < m; j++)
	                issued[i][j] = sc.nextInt();

	        boolean[] finished = new boolean[n];
	        int[] result = new int[n];
	        int done = 0;

	        int current = 0;

	        while (done < n) {

	            boolean progress = false;

	            for (int k = 0; k < n; k++) {

	                int i = (current + k) % n;

	                if (!finished[i]) {

	                    boolean possible = true;

	                    for (int j = 0; j < m; j++) {
	                        int need = required[i][j] - issued[i][j];
	                        if (need > available[j]) {
	                            possible = false;
	                            break;
	                        }
	                    }

	                    if (possible) {

	                        for (int j = 0; j < m; j++)
	                            available[j] += issued[i][j];

	                        result[done++] = i;
	                        System.out.print(i);
	                        finished[i] = true;
	                        current = (i + 1) % n;
	                        progress = true;
	                        break;
	                    }
	                }
	            }

	            if (!progress) {
	                System.out.println("-1");
	                return;
	            }
	        }

	        for (int i = 0; i < n; i++) {
	            System.out.print(result[i]);
	            if (i < n - 1) System.out.print(" ");
	        }
	    }
	}
/*
3
2 2 3
3 3
2 4 0
0 0  1
0 1 3
3 3
3 5 4
1 3 4
2 3 5

OP: 0 1 2

Correct OP: 2 0 1
*/