package com.myprog;

import java.util.Arrays;
import java.util.Scanner;

//Program 28 : Alternate Sort --> output is 1 3 5 9
public class _28AlternateSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the size of the list
        int N = scanner.nextInt();

        // Read the elements of the list into an array
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        // Sort the array in ascending order
        Arrays.sort(arr);

        // Print the alternately sorted elements
        // Start from the first element (index 0) and print every other element
        for (int i = 0; i < N; i += 2) {
            System.out.print(arr[i] + " ");
        }
        System.out.println(); // For a new line at the end

        scanner.close();
    }
}