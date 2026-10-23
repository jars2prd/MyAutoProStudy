package com.myprog;

import java.util.Scanner;

//Program 24 : Find numbers less than K
public class _24CountLessThanK {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read N, the number of elements in the list
        int N = scanner.nextInt();

        // Read the N space-separated integers into an array
        int[] elements = new int[N];
        for (int i = 0; i < N; i++) {
            elements[i] = scanner.nextInt();
        }

        // Read K, the integer to be compared (referred to as 'mum' in the image)
        int K = scanner.nextInt();

        // Count elements strictly less than K
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (elements[i] < K) {
                count++;
            }
        }

        // Print the result
        System.out.println(count);

        scanner.close();
    }
}