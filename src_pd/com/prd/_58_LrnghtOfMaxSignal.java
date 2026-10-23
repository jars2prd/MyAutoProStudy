package com.prd;

import java.util.Scanner;

public class _58_LrnghtOfMaxSignal {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read size of the string
        int n = sc.nextInt();
        int[] arr = new int[n];

        // Read the binary string as integers
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int maxLen = 0;
        int i = 0;

        while (i < n) {
            int j = i;

            // Count how many same consecutive elements
            while (j < n && arr[j] == arr[i]) {
                j++;
            }

            int length = j - i;
            boolean notAtStart = (i > 0);
            boolean notAtEnd = (j < n);

            // Consider only if not at start or end
            if (notAtStart && notAtEnd) {
                if (length > maxLen) {
                    maxLen = length;
                }
            }

            i = j; // Move to next sequence
        }

        System.out.println(maxLen);
    }
}
/*
6
1 0 1 0 0 0
OP: 1
*/