package com.prd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class _53_HanshsakeMechanism {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read number of elements
        int N = Integer.parseInt(br.readLine().trim());

        // Read the list of numbers
        String[] parts = br.readLine().trim().split("\\s+");
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            numbers.add(Integer.parseInt(parts[i]));
        }

        // Read M
        int M = Integer.parseInt(br.readLine().trim());

        // Sort based on:
        // 1. Number of 1s in binary representation (descending)
        // 2. Magnitude (absolute value) (descending)
        // 3. Value itself (descending)
        Collections.sort(numbers, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                int onesA = Integer.bitCount(Math.abs(a));
                int onesB = Integer.bitCount(Math.abs(b));
                if (onesA != onesB) return onesB - onesA; // more ones first

                int magA = Math.abs(a);
                int magB = Math.abs(b);
                if (magA != magB) return magB - magA; // bigger magnitude first

                return b - a; // bigger number first if still tie
            }
        });

        // Output top M numbers
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            if (i > 0) sb.append(" ");
            sb.append(numbers.get(i));
        }
        System.out.println(sb.toString());
    }
}

/*
5
5 9 8 7 16
3
OP: 7 9 5
*/