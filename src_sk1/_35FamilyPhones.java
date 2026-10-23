package com.myprog;

import java.util.*;

//Program 35 : Maximum number of phones owned by generation : Tree Structure 
public class _35FamilyPhones {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // Total number of family members
        int[] phones = new int[N]; // Number of phones for each member
        for (int i = 0; i < N; i++) {
            phones[i] = scanner.nextInt();
        }

        int numRel = scanner.nextInt(); // Number of relationships
        int memConnect = scanner.nextInt(); // This value seems to indicate 2 members per relationship

        // Build adjacency list for the tree
        // Assuming member 0 is the root for simplicity based on typical tree problems,
        // but the problem states "family assigns themselves to be the root".
        // In a real scenario, you might need to determine the root if not explicitly given.
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < numRel; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            adj.get(u).add(v);
            // If the tree is undirected, also add: adj.get(v).add(u);
            // For a directed family tree (parent to child), only one direction is needed.
            // Assuming parent -> child direction as implied by "root".
        }

        scanner.close();

        // Perform BFS to find max phones per generation
        int maxPhonesInGeneration = 0;
        Queue<Integer> queue = new LinkedList<>();
        
        // Assuming member 0 is the root. If not, find the actual root.
        queue.offer(0); 
        
        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            int currentLevelPhones = 0;

            for (int i = 0; i < currentLevelSize; i++) {
                int member = queue.poll();
                currentLevelPhones += phones[member];

                for (int neighbor : adj.get(member)) {
                    queue.offer(neighbor);
                }
            }
            maxPhonesInGeneration = Math.max(maxPhonesInGeneration, currentLevelPhones);
        }

        System.out.println(maxPhonesInGeneration);
    }
}
