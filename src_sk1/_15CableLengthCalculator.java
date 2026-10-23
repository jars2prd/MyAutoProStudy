package com.myprog;

import java.util.*;

//Program 15 : Cable length calculator : Minium length cable used
public class _15CableLengthCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read number of systems
        int n = sc.nextInt();
        int[] state = new int[n];
        int[] dist = new int[n];

        // Read system states (1 = ON, 0 = OFF)
        for (int i = 0; i < n; i++) {
            state[i] = sc.nextInt();
        }

        // Read system distances
        for (int i = 0; i < n; i++) {
            dist[i] = sc.nextInt();
        }

        int totalCable = 0;

        // For each OFF system, find the minimum distance to any ON system
        for (int i = 0; i < n; i++) {
            if (state[i] == 0) {
                int minDist = Integer.MAX_VALUE;

                for (int j = 0; j < n; j++) {
                    if (state[j] == 1) {
                        minDist = Math.min(minDist, Math.abs(dist[i] - dist[j]));
                        state[j]=0;
                    }
                }
                state[i]=1;
                totalCable += minDist;
            }
        }

        System.out.println(totalCable);
    }
}