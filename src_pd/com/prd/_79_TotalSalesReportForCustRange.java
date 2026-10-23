package com.prd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _79_TotalSalesReportForCustRange {
	static class FenwickTree {
        long[] tree;
        int size;

        FenwickTree(int n) {
            this.size = n;
            tree = new long[n + 1]; // 1-based indexing
        }

        // Add value to a given index (0-based input index)
        void update(int index, long value) {
            index++; // shift to 1-based
            while (index <= size) {
                tree[index] += value;
                index += index & -index;
            }
        }

        // Sum from 0 to index (inclusive)
        long prefixSum(int index) {
            long sum = 0;
            index++; // shift to 1-based
            while (index > 0) {
                sum += tree[index];
                index -= index & -index;
            }
            return sum;
        }

        // Sum from left to right (inclusive)
        long rangeSum(int left, int right) {
            if (left > right) return 0;
            return prefixSum(right) - (left > 0 ? prefixSum(left - 1) : 0);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numOfCustomer = sc.nextInt(); // C
        int numOfQueries = sc.nextInt();  // Q
        int queryDesc = sc.nextInt();     // always 3 (unused)

        FenwickTree bit = new FenwickTree(numOfCustomer+1);

        List<Long> output = new ArrayList<Long>();

        for (int i = 0; i < numOfQueries; i++) {
            int type = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (type == 1) {
                // Type 1: Add sales quantity to customer a
                bit.update(a, b);
            } else if (type == 2) {
                // Type 2: Query total sales in range a to b
                output.add(bit.rangeSum(a, b));
            }
        }

        // Print results for all type 2 queries
        for (int i = 0; i < output.size(); i++) {
            System.out.print(output.get(i));
            if (i < output.size() - 1) {
                System.out.print(" ");
            }
        }
    }
}

/*
4 
5 3
1 3 12
2 0 2
1 1 4
1 3 2
2 2 4

OP: 0 14
*/