package com.prd;

import java.util.Scanner;

public class _78_TotalScoreOfGroupOfStudent {
	 public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        int n = sc.nextInt();
	        int[] pfr = new int[n];

	        for (int i = 0; i < n; i++) {
	            pfr[i] = sc.nextInt();
	        }

	        int totalScore = 0;

	        for (int i = 0; i < n; i++) {
	            // If no student behind has higher PFR -> score 15
	            boolean hasHigher = false;
	            for (int j = i + 1; j < n; j++) {
	                if (pfr[j] > pfr[i]) {
	                    hasHigher = true;
	                    break;
	                }
	            }

	            if (!hasHigher) {
	                totalScore += 15;
	                continue;
	            }

	            // We have a higher PFR behind; now check for smaller PFR behind
	            boolean hasSmaller = false;
	            for (int j = i + 1; j < n; j++) {
	                if (pfr[j] < pfr[i]) {
	                    hasSmaller = true;
	                    break;
	                }
	            }

	            if (hasSmaller) {
	                totalScore += 5;
	            } else {
	                totalScore += 10;
	            }
	        }

	        System.out.println(totalScore);
	        sc.close();
	    }
}

/*
6
1 4 5 2 7 8

OP: 55
*/