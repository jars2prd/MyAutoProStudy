package com.prd;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class _51_LongestConsigativeSubSegment {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String S = sc.next();
        int K = sc.nextInt();
        int n = S.length();
        char[] arr = S.toCharArray();

        // Step 1: Find maximum possible length using sliding window
        int maxLen = 0;
        int left = 0, zeroCount = 0;
        for (int right = 0; right < n; right++) {
            if (arr[right] == '0') zeroCount++;
            while (zeroCount > K) {
                if (arr[left] == '0') zeroCount--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }

        // Step 2: Count distinct ways to get this max length
        Set<String> uniqueWays = new HashSet<String>();
        left = 0;
        zeroCount = 0;
        for (int right = 0; right < n; right++) {
            if (arr[right] == '0') zeroCount++;
            while (zeroCount > K) {
                if (arr[left] == '0') zeroCount--;
                left++;
            }
            if (right - left + 1 == maxLen) {
                char[] temp = Arrays.copyOf(arr, n);
                int changesLeft = K;
                for (int i = left; i <= right && changesLeft > 0; i++) {
                    if (temp[i] == '0') {
                        temp[i] = '1';
                        changesLeft--;
                    }
                }
                uniqueWays.add(new String(temp));
            }
        }

        System.out.println(uniqueWays.size());
    }
}

/*
1010101
1

O/p: 3
*/

