package com.myprog;

import java.util.*;

//Program 16 : Arrangement of Flowers : First ascending and then descending sorting array.
public class _16FlowerArrangement {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read N (flowerStem_size)
        int N = scanner.nextInt();

        // Read N space-separated flower stem lengths
        int[] flowerStems = new int[N];
        for (int i = 0; i < N; i++) {
            flowerStems[i] = scanner.nextInt();
        }

        // Read K (random)
        int K = scanner.nextInt();

        // Separate the array into two parts
        int[] firstK = Arrays.copyOfRange(flowerStems, 0, K);
        int[] remaining = Arrays.copyOfRange(flowerStems, K, N);

        // Sort the first K flowers in increasing order
        Arrays.sort(firstK);

        // Sort the remaining flowers in decreasing order
        // Sort in ascending order first, then reverse
        Arrays.sort(remaining);
        for (int i = 0; i < remaining.length / 2; i++) {
            int temp = remaining[i];
            remaining[i] = remaining[remaining.length - 1 - i];
            remaining[remaining.length - 1 - i] = temp;
        }

        // Print the final arrangement
        for (int i = 0; i < firstK.length; i++) {
            System.out.print(firstK[i] + " ");
        }
        for (int i = 0; i < remaining.length; i++) {
            System.out.print(remaining[i] + " ");
        }
        System.out.println();

        scanner.close();
    }
}
