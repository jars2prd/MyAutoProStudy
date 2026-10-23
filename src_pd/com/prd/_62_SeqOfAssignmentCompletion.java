package com.prd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class _62_SeqOfAssignmentCompletion {
	 public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        // Read number of subjects (M)
	        int booksNum = sc.nextInt();
	        int[] avail = new int[booksNum];
	        for (int i = 0; i < booksNum; i++) {
	            avail[i] = sc.nextInt();
	        }

	        // Read N and reqBooks (M again)
	        int studentNum = sc.nextInt();
	        int reqBooks = sc.nextInt();

	        int[][] max = new int[studentNum][reqBooks];
	        for (int i = 0; i < studentNum; i++) {
	            for (int j = 0; j < reqBooks; j++) {
	                max[i][j] = sc.nextInt();
	            }
	        }

	        // IssuedBooks count already known == studentNum, reqBooks
	        int studentIssuedBooks = sc.nextInt();
	        int issuedBooks = sc.nextInt();

	        int[][] alloc = new int[studentIssuedBooks][issuedBooks];
	        for (int i = 0; i < studentIssuedBooks; i++) {
	            for (int j = 0; j < issuedBooks; j++) {
	                alloc[i][j] = sc.nextInt();
	            }
	        }

	        // Calculate need[][] = max[][] - alloc[][]
	        int[][] need = new int[studentNum][reqBooks];
	        for (int i = 0; i < studentNum; i++) {
	            for (int j = 0; j < reqBooks; j++) {
	                need[i][j] = max[i][j] - alloc[i][j];
	            }
	        }

	        boolean[] finished = new boolean[studentNum];
	        List<Integer> sequence = new ArrayList<>();

	        int completedCount = 0;
	        int index = 0; // start with smallest student ID

	        while (completedCount < studentNum) {
	            boolean found = false;
	            int checkedCount = 0;

	            while (checkedCount < studentNum) {
	                int sid = (index + checkedCount) % studentNum;

	                if (!finished[sid] && canComplete(need[sid], avail)) {
	                    // Assign books to this student
	                    for (int j = 0; j < booksNum; j++) {
	                        avail[j] += alloc[sid][j]; // release after completion
	                    }
	                    finished[sid] = true;
	                    sequence.add(sid);
	                    completedCount++;
	                    index = (sid + 1) % studentNum; // next start point
	                    found = true;
	                    break;
	                }
	                checkedCount++;
	            }

	            if (!found) {
	                // No student could be completed in this round
	                System.out.println("-1");
	                return;
	            }
	        }

	        // Print sequence
	        for (int i = 0; i < sequence.size(); i++) {
	            System.out.print(sequence.get(i));
	            if (i != sequence.size() - 1) System.out.print(" ");
	        }
	    }

	    private static boolean canComplete(int[] need, int[] avail) {
	        for (int i = 0; i < need.length; i++) {
	            if (need[i] > avail[i]) {
	                return false;
	            }
	        }
	        return true;
	    }
}

/*
 
3
2 2 3
3 3
2 4 0
0 0  1
0 1 3
3 3
3 5 4
1 3 4
2 3 5
Output: 0 1 2

expected Output: 2 0 1
 
 
 
 
 */
