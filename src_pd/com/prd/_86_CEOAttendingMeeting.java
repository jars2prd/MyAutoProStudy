package com.prd;

import java.util.Scanner;

public class _86_CEOAttendingMeeting {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int num = sc.nextInt();
        int[] likes = new int[num];
        
        for (int i = 0; i < num; i++) {
            likes[i] = sc.nextInt() - 1; // 0-indexed
        }
        
        boolean[] visited = new boolean[num];  // marks completely processed nodes
        int[] depth = new int[num];            // depth in current DFS path (0 if not in current path)
        int maxCycle = 0;
        
        for (int i = 0; i < num; i++) {
            if (!visited[i]) {
                int current = i;
                int step = 1;
                
                while (true) {
                    if (depth[current] != 0) {
                        // Found a cycle: step - depth[current]
                        maxCycle = Math.max(maxCycle, step - depth[current]);
                        break;
                    }
                    if (visited[current]) {
                        // Already processed in some earlier traversal
                        break;
                    }
                    depth[current] = step;
                    step++;
                    current = likes[current];
                }
                
                // Mark all nodes in this traversal as visited and reset depth[]
                current = i;
                while (!visited[current] && depth[current] != 0) {
                    visited[current] = true;
                    int next = likes[current];
                    depth[current] = 0; // reset for reuse
                    current = next;
                }
            }
        }
        
        System.out.println(maxCycle);
    }
}

/*
4 
2 3 4 1

OP: 4
*/
