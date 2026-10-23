package com.myprog;

import java.util.Scanner;

//when output is 9
public class _15CableLength1{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read systemState_size (N)
        int N = scanner.nextInt();

        // Read systemState array
        int[] systemState = new int[N];
        for (int i = 0; i < N; i++) {
            systemState[i] = scanner.nextInt();
        }

        // Read dist_size (M) - though not explicitly used in this solution, it's part of the input description
        int M = scanner.nextInt(); 

        // Read dist array (distances from the first system)
        int[] dist = new int[M];
        for (int i = 0; i < M; i++) {
            dist[i] = scanner.nextInt();
        }

        long totalCableLength = 0;

        for (int i = 0; i < N; i++) {
            if (systemState[i] == 0) { // If the system is OFF
                long minLength = Long.MAX_VALUE;
                int offSystemDistance = dist[i];

                // Find the nearest ON system
                for (int j = 0; j < N; j++) {
                    if (systemState[j] == 1) { // If the system is ON
                        int onSystemDistance = dist[j];
                        long currentLength = Math.abs(offSystemDistance - onSystemDistance);
                        if (currentLength < minLength) {
                            minLength = currentLength;
                        }
                    }
                }
                totalCableLength += minLength;
            }
        }

        System.out.println(totalCableLength);
        scanner.close();
    }
}