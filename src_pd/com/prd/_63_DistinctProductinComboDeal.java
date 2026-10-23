package com.prd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _63_DistinctProductinComboDeal {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read N (customers) and M (products per customer)
        String[] firstLine = br.readLine().trim().split("\\s+");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);

        // Read first customer's products into a set
        String[] firstCustomer = br.readLine().trim().split("\\s+");
        Set<Integer> commonProducts = new HashSet<Integer>();
        for (int i = 0; i < M; i++) {
            commonProducts.add(Integer.parseInt(firstCustomer[i]));
        }

        // Process remaining customers
        for (int c = 1; c < N; c++) {
            String[] customerData = br.readLine().trim().split("\\s+");
            Set<Integer> currentCustomer = new HashSet<Integer>();
            for (int i = 0; i < M; i++) {
                currentCustomer.add(Integer.parseInt(customerData[i]));
            }
            // Retain only products common to all so far
            commonProducts.retainAll(currentCustomer);
        }

        // Convert to list, sort, and print
        List<Integer> result = new ArrayList<Integer>(commonProducts);
        Collections.sort(result);

        for (int i = 0; i < result.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(result.get(i));
        }
    }
}

/*
4 4
8 2 3 2
2 3 4 8
8 3 11 12
2 3 6 8
OP: 3 8
*/