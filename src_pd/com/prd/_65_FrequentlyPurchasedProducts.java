package com.prd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class _65_FrequentlyPurchasedProducts {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int tag_row = sc.nextInt(); // Number of customers (N)
        int tag_col = sc.nextInt(); // Number of products per customer (M)

        // Map to store ProductID -> Set of unique customer IDs
        Map<Integer, Set<Integer>> productCustomers = new HashMap<Integer, Set<Integer>>();

        for (int customer = 0; customer < tag_row; customer++) {
            for (int j = 0; j < tag_col; j++) {
                int productId = sc.nextInt();
                if (!productCustomers.containsKey(productId)) {
                    productCustomers.put(productId, new HashSet<Integer>());
                }
                productCustomers.get(productId).add(customer);
            }
        }

        int K_input = sc.nextInt(); // Minimum number of customers

        List<Integer> result = new ArrayList<Integer>();

        for (Map.Entry<Integer, Set<Integer>> entry : productCustomers.entrySet()) {
            if (entry.getValue().size() >= K_input) {
                result.add(entry.getKey());
            }
        }

        Collections.sort(result);

        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i < result.size() - 1) {
                System.out.print(" ");
            }
        }
    }
}

/*
4 4
1 2 3 2
2 3 4 8
8 3 11 12
2 3 6 8
3

OP: 2 3 8
*/