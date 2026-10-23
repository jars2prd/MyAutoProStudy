package com.prd;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class _92_LRU_PageReplacement {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read total number of page requests
        int n = scanner.nextInt();
        int[] pages = new int[n];

        // Read the page requests
        for (int i = 0; i < n; i++) {
            pages[i] = scanner.nextInt();
        }

        // Read the cache size
        int cacheSize = scanner.nextInt();

        System.out.println(countCacheMisses(pages, cacheSize));
    }

    public static int countCacheMisses(int[] pages, int cacheSize) {
        Set<Integer> cache = new LinkedHashSet<>();
        int misses = 0;

        for (int page : pages) {
            if (!cache.contains(page)) {
                // Cache miss
                misses++;
                if (cache.size() == cacheSize) {
                    // Remove the least recently used page
                    Iterator<Integer> it = cache.iterator();
                    it.next();
                    it.remove();
                }
            } else {
                // Cache hit, move page to the end (most recently used)
                cache.remove(page);
            }
            cache.add(page);
        }

        return misses;
    }
}

/*
6
1 2 1 3 1 2
2
OP: 4
*/