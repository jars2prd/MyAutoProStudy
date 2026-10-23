package com.prd;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class _50_JuiceStalls {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // number of stalls
        int[] dist = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = sc.nextInt();
        }

        int N2 = sc.nextInt(); // again number of stalls (same as N)
        int[] juice = new int[N2];
        for (int i = 0; i < N2; i++) {
            juice[i] = sc.nextInt();
        }

        int schoolDist = sc.nextInt();
        int initEnergy = sc.nextInt();

        // Pair stalls as (distance, juice)
        int[][] stalls = new int[N][2];
        for (int i = 0; i < N; i++) {
            stalls[i][0] = dist[i];
            stalls[i][1] = juice[i];
        }

        // Sort by distance from home
        Arrays.sort(stalls, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0], b[0]);
            }
        });

        // Max heap for juices from stalls we've passed
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

        int stops = 0;
        int currEnergy = initEnergy;
        int prevPos = 0;
        int idx = 0;

        while (prevPos < schoolDist) {
            int nextPos;
            if (idx < N) {
                nextPos = stalls[idx][0];
            } else {
                nextPos = schoolDist; // final destination
            }

            int distToNext = nextPos - prevPos;

            // While we can't reach the next position, refuel from best past stall
            while (currEnergy < distToNext) {
                if (maxHeap.isEmpty()) {
                    System.out.println(-1);
                    return;
                }
                currEnergy += maxHeap.poll();
                stops++;
            }

            // Move to the next position
            currEnergy -= distToNext;
            prevPos = nextPos;

            // If this is a stall, add its juice to heap
            if (idx < N && stalls[idx][0] == prevPos) {
                maxHeap.add(stalls[idx][1]);
                idx++;
            }

            // If we've passed all stalls, just loop until we reach school
            if (idx >= N && prevPos < schoolDist && currEnergy >= (schoolDist - prevPos)) {
                prevPos = schoolDist;
            }
        }

        System.out.println(stops);
    }
}


/*
3
5 7 10
3
2 3 5
15
5

O/p: 3
*/