package com.myprog;

import java.util.*;

//Program 17 : Frequency Sort : Sorting array as per the frequency (repeating) of numbers
public class _17FrequencySort {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read the number of elements
        int n = sc.nextInt();

        // Read the elements into a list
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(sc.nextInt());
        }

        // Calculate frequencies and store original indices
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        Map<Integer, Integer> originalIndexMap = new HashMap<>(); // To maintain original order for same frequency
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            if (!originalIndexMap.containsKey(num)) {
                originalIndexMap.put(num, i); // Store first occurrence index
            }
        }

        // Sort the list based on frequency and original index
        Collections.sort(nums, (a, b) -> {
            int freqA = frequencyMap.get(a);
            int freqB = frequencyMap.get(b);

            // Sort by frequency in descending order
            if (freqA != freqB) {
                return Integer.compare(freqB, freqA);
            } else {
                // If frequencies are same, sort by original index in ascending order
                return Integer.compare(originalIndexMap.get(a), originalIndexMap.get(b));
            }
        });

        // Print the sorted elements
        for (int i = 0; i < n; i++) {
            System.out.print(nums.get(i) + (i == n - 1 ? "" : " "));
        }
        System.out.println();

        sc.close();
    }
}