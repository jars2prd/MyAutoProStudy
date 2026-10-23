package com.myprog;

import java.util.Scanner;

public class _47SubmatrixProductCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read N and M
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        // Read the matrix A
        long[][] A = new long[N][M]; // Use long to prevent overflow for product
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                A[i][j] = scanner.nextLong();
            }
        }

        // Read maxK
        long maxK = scanner.nextLong();

        int count = 0;

        // Iterate through all possible submatrices starting from (0,0)
        // A submatrix B[1..X][1..Y] corresponds to A[0..X-1][0..Y-1] in 0-indexed array
        for (int x = 1; x <= N; x++) { // x represents the number of rows in submatrix
            for (int y = 1; y <= M; y++) { // y represents the number of columns in submatrix
                long product = 1;
                boolean productExceedsK = false;

                // Calculate product of elements in the current submatrix A[0..x-1][0..y-1]
                for (int i = 0; i < x; i++) {
                    for (int j = 0; j < y; j++) {
                        // Handle zero elements: if any element is 0, product becomes 0
                        if (A[i][j] == 0) {
                            product = 0;
                            break; // Product is 0, no need to multiply further
                        }
                        // Check for potential overflow before multiplication
                        if (maxK != 0 && Math.abs(product) > maxK / Math.abs(A[i][j]) && A[i][j] != 0) {
                            productExceedsK = true;
                            break; // Product will definitely exceed K, no need to multiply further
                        }
                        product *= A[i][j];
                    }
                    if (productExceedsK || product == 0) {
                        break;
                    }
                }

                // Check if the product is less than maxK
                if (!productExceedsK && product < maxK) {
                    count++;
                }
            }
        }

        System.out.println(count);

        scanner.close();
    }
}
