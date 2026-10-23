package com.prd;

import java.util.Scanner;

public class _85_MaxNoOfWins {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String status = sc.next();    // Binary string
        int K = sc.nextInt();         // Special value (number of flips allowed)
        
        int n = status.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = status.charAt(i) - '0';
        }
        
        // Step 1: Find the maximum length of consecutive 1's after flipping at most K zeros
        int maxLen = 0;
        int left = 0, zeros = 0;
        for (int right = 0; right < n; right++) {
            if (arr[right] == 0) zeros++;
            while (zeros > K) {
                if (arr[left] == 0) zeros--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        // Step 2: Count how many windows achieve maxLen with exactly K flips
        int ways = 0;
        left = 0;
        zeros = 0;
        for (int right = 0; right < n; right++) {
            if (arr[right] == 0) zeros++;
            while (zeros > K) {
                if (arr[left] == 0) zeros--;
                left++;
            }
            if (right - left + 1 == maxLen && zeros == K) {
                ways++;
            }
        }
        
        System.out.println(ways);
    }
}

/*
1010101
1

OP: 3
*/