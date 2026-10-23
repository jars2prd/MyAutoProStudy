package com.myprog;

import java.util.*;

//Program 28 : Alternate sort : Check what is the output expected --> output is 10 1 9 2 6 3 5 5
public class _28AlternateSort1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt(); // Read the number of elements
        int[] arr = new int[n];

        // Read the elements
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Sort the array
        Arrays.sort(arr);

        // Use two pointers: one from start, one from end
        int start = 0;
        int end = n - 1;

        while (start <= end) {
            if (start != end) {
                System.out.print(arr[end] + " " + arr[start] + " ");
            } else {
                System.out.print(arr[start] + " ");
            }
            start++;
            end--;
        }
    }
}