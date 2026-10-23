package com.prd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _70_LongestPalindromicList {
	  public static List<Integer> longestPalindromicList(List<Integer> salesData) {
	        int i = 0;
	        int j = salesData.size() - 1;

	        while (i < j) {
	            int left = salesData.get(i);
	            int right = salesData.get(j);

	            if (left == right) {
	                i++;
	                j--;
	            } else if (left < right) {
	                // Merge left two elements
	                int merged = salesData.get(i) + salesData.get(i + 1);
	                salesData.set(i + 1, merged);
	                salesData.remove(i);
	                j--; // list size reduced, so adjust right pointer
	            } else {
	                // Merge right two elements
	                int merged = salesData.get(j - 1) + salesData.get(j);
	                salesData.set(j - 1, merged);
	                salesData.remove(j);
	                j--;
	            }
	        }

	        return salesData;
	    }

	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        int num = sc.nextInt();
	        List<Integer> salesData = new ArrayList<Integer>();

	        for (int k = 0; k < num; k++) {
	            salesData.add(sc.nextInt());
	        }

	        List<Integer> result = longestPalindromicList(salesData);

	        for (int val : result) {
	            System.out.print(val + " ");
	        }
	    }
}


/*
6
15 10 15 34 25 15
OP: 15 25 34 25 15 
*/
