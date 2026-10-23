package com.prd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _56_ConvertListToPalindromicList {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read number of elements
        int n = sc.nextInt();
        List<Integer> list = new ArrayList<Integer>();

        // Read list elements
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        int left = 0;
        int right = list.size() - 1;

        // Process until palindrome
        while (left < right) {
            int leftVal = list.get(left);
            int rightVal = list.get(right);

            if (leftVal == rightVal) {
                left++;
                right--;
            } else if (leftVal < rightVal) {
                // Merge left with left+1
                list.set(left + 1, list.get(left) + list.get(left + 1));
                list.remove(left);
                right--; // list size reduced
            } else {
                // Merge right with right-1
                list.set(right - 1, list.get(right) + list.get(right - 1));
                list.remove(right);
                right--;
            }
        }

        // Print final palindromic list
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) {
                System.out.print(" ");
            }
        }
    }
}

/*
6
15 10 15 34 25 15

OP: 15 25 34 25 15
*/
