package com.prd;

import java.util.Scanner;

public class _59_LargestPalindromicSqSubMatrix {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // rows
        int M = sc.nextInt(); // columns
        int[][] mat = new int[N][M];

        // Read matrix
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                mat[i][j] = sc.nextInt();
            }
        }

        int maxOddSize = Math.min(N, M);
        if (maxOddSize % 2 == 0) maxOddSize--; // ensure odd size

        // Start from largest odd square and go downwards
        for (int size = maxOddSize; size >= 1; size -= 2) {
            if (existsPalindromicSquare(mat, N, M, size)) {
                System.out.println(size * size); // number of elements
                return;
            }
        }
    }

    // Check if any palindromic sub-matrix of given size exists
    private static boolean existsPalindromicSquare(int[][] mat, int N, int M, int size) {
        for (int r = 0; r + size <= N; r++) {
            for (int c = 0; c + size <= M; c++) {
                if (isPalindromic(mat, r, c, size)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check if a specific sub-matrix is palindromic
    private static boolean isPalindromic(int[][] mat, int r, int c, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int mirrorI = size - 1 - i;
                int mirrorJ = size - 1 - j;
                if (mat[r + i][c + j] != mat[r + mirrorI][c + mirrorJ]) {
                    return false;
                }
            }
        }
        return true;
    }
}

/*
5 4
9 8 7 6
2 3 2 5
7 8 9 7
5 4 3 2
6 7 8 9

OP: 9
*/
