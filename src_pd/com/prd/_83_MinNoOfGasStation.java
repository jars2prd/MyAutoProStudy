package com.prd;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class _83_MinNoOfGasStation {
	 public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        int N = sc.nextInt(); // number of gas stations
	        int[] dist = new int[N];
	        for (int i = 0; i < N; i++) {
	            dist[i] = sc.nextInt();
	        }

	        int nums = sc.nextInt(); // always equal to N (not actually needed separately)
	        int[] gas = new int[N];
	        for (int i = 0; i < N; i++) {
	            gas[i] = sc.nextInt();
	        }

	        int D = sc.nextInt(); // distance to office
	        int K = sc.nextInt(); // initial fuel

	        // Combine distances and fuel amounts into station list
	        int[][] stations = new int[N + 1][2];
	        for (int i = 0; i < N; i++) {
	            stations[i][0] = dist[i];
	            stations[i][1] = gas[i];
	        }
	        // Add the office as a station with 0 fuel
	        stations[N][0] = D;
	        stations[N][1] = 0;

	        // Sort stations by distance from home
	        Arrays.sort(stations, new Comparator<int[]>() {
	            public int compare(int[] a, int[] b) {
	                return a[0] - b[0];
	            }
	        });

	        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
	        int stops = 0;
	        int prevDist = 0;
	        int fuel = K;

	        for (int i = 0; i <= N; i++) {
	            int travel = stations[i][0] - prevDist;
	            fuel -= travel;

	            // Refuel if we can't reach this station
	            while (fuel < 0 && !maxHeap.isEmpty()) {
	                fuel += maxHeap.poll();
	                stops++;
	            }

	            if (fuel < 0) { // Even after refueling, can't reach
	                System.out.println(-1);
	                return;
	            }

	            // Add this station's fuel to heap for potential future use
	            maxHeap.offer(stations[i][1]);
	            prevDist = stations[i][0];
	        }

	        System.out.println(stops);
	    }
}

/*
4
5 7 8 10
4
2 3 1 5
15
5

OP: 3
*/