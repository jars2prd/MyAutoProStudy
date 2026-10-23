package com.prd;

import java.util.Scanner;

public class _55_MocheGoldbergTrading {
	 public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        int n = sc.nextInt();
	        int[] counts = new int[n];
	        long sum = 0;
	        long maxCount = 0;

	        for (int i = 0; i < n; i++) {
	            counts[i] = sc.nextInt();
	            sum += counts[i];
	            if (counts[i] > maxCount) {
	                maxCount = counts[i];
	            }
	        }

	        long others = sum - maxCount;
	        long result;

	        if (maxCount <= others + 1) {
	            result = sum; // Can use all days
	        } else {
	            result = others * 2 + 1; // Can't use all from the largest town
	        }

	        System.out.println(result);
	    }
}

/*
3
7 2 3
OP: 11
*/
