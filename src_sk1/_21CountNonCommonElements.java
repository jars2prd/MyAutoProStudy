package com.myprog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

//Program 21 : Count un-common elements from two Arrays
public class _21CountNonCommonElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read input for the first list
        int n = scanner.nextInt(); // Size of first list
        //HashSet<Integer> set1 = new HashSet<>();
        List<Integer> set1 = new ArrayList();
        
        for (int i = 0; i < n; i++) {
            set1.add(scanner.nextInt());
        }

        // Read input for the second list
        int m = scanner.nextInt(); // Size of second list
       // HashSet<Integer> set2 = new HashSet<>();
        List<Integer> set2 = new ArrayList();
        
        for (int i = 0; i < m; i++) {
            set2.add(scanner.nextInt());
        }

        int nonCommonCount = 0;
        List<Integer> uncommonelements = new ArrayList<>();

        // Count elements in set1 that are not in set2
        for (int element : set1) {
            if (!set2.contains(element)) {
            	uncommonelements.add(element);
                nonCommonCount++;
            }
        }

        // Count elements in set2 that are not in set1
        for (int element : set2) {
            if (!set1.contains(element)) {
            	uncommonelements.add(element);
                nonCommonCount++;
            }
        }

        System.out.println(nonCommonCount);

        scanner.close();
    }
}