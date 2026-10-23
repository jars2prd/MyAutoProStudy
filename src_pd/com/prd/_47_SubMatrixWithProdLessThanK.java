package com.prd;

import java.util.Scanner;

public class _47_SubMatrixWithProdLessThanK {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] A = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                A[i][j] = sc.nextInt();
            }
        }

        long K = sc.nextLong();
        int count = 0;

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                long product = 1;
                boolean ok = true;
                for (int i = 0; i <= x && ok; i++) {
                    for (int j = 0; j <= y; j++) {
                        product *= A[i][j];
                        if (product > K) {
                            ok = false;
                            break;
                        }
                    }
                }
                if (ok) count++;
            }
        }

        System.out.println(count);
    }
}

/*
input
2 3
1 2 3
1 2 3
3

Output:: 3
 * 
 * 
 */
 
