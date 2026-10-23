package com.myprog;

import java.util.Scanner;

//Program 25 : Interchange Index and Values
public class _25ReplaceWithIndex {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the size of the list (N)
        int N = scanner.nextInt();

        // Create an array to store the original list
        int[] arr = new int[N];
        // Create an array to store the new positions (index mapping)
        int[] newPositions = new int[N];

        // Read the elements of the list and populate the newPositions array
        // The value arr[i] will be at index i in the original array.
        // We want to find out where each number (0 to N-1) is located.
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
            // Store the current index 'i' at the position indicated by the value arr[i]
            // For example, if arr[0] = 3, then newPositions[3] = 0
            newPositions[arr[i]] = i;
        }

        // Print the elements of the newPositions array, which represent the transformed list
        for (int i = 0; i < N; i++) {
            System.out.print(newPositions[i] + (i == N - 1 ? "" : " "));
        }
        System.out.println(); // For a new line at the end

        scanner.close();
    }
}
