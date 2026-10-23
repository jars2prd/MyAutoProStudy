package com.prd;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class _93_FIFO_PageReplacement {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of page requests
        int n = scanner.nextInt();
        int[] pageRequests = new int[n];

        // Read the page requests
        for (int i = 0; i < n; i++) {
            pageRequests[i] = scanner.nextInt();
        }

        // Read the maximum cache size
        int maxCacheSize = scanner.nextInt();

        // FIFO Cache simulation
        Set<Integer> cache = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        int cacheMisses = 0;

        for (int page : pageRequests) {
            if (!cache.contains(page)) {
                // Cache miss
                cacheMisses++;

                if (cache.size() == maxCacheSize) {
                    // Remove the oldest page
                    int oldest = queue.poll();
                    cache.remove(oldest);
                }

                // Add the new page
                cache.add(page);
                queue.add(page);
            }
            // else: cache hit, do nothing
        }

        // Output the number of cache misses
        System.out.println(cacheMisses);
    }
}

/*
6
1 2 1 3 1 2
2

OP: 5
*/