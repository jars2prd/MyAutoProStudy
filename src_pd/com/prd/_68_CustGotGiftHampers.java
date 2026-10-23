package com.prd;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _68_CustGotGiftHampers {
	 public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        
	        int n = sc.nextInt();
	        int[] prices = new int[n];
	        
	        for (int i = 0; i < n; i++) {
	            prices[i] = sc.nextInt();
	        }
	        
	        int k = sc.nextInt();
	        
	        // Frequency map of prices
	        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
	        for (int price : prices) {
	            freq.put(price, freq.getOrDefault(price, 0) + 1);
	        }
	        
	        int count = 0;
	        
	        if (k == 0) {
	            // For k=0, count pairs of duplicates (nC2 = n*(n-1)/2)
	            for (int val : freq.values()) {
	                if (val > 1) {
	                    count += (val * (val - 1));
	                }
	            }
	        } else {
	            // For k > 0, count pairs (price, price + k)
	            for (int price : prices) {
	                if (freq.containsKey(price + k)) {
	                    count += freq.get(price + k);
	                }
	            }
	        }
	        
	        System.out.println(count);
	    }
}

/*
6
10 15 23 14 2 15
13

OP: 3
*/