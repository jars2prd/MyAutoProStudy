package com.myprog;

import java.util.*;

//Program 23 : Sort K elements of a list.
public class _23SortKElements {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// Read N (inputList_size)
		int N = scan.nextInt();

		// Read the N space-separated integers into an ArrayList
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(scan.nextInt());
		}

		// Read K (num)
		int K = scan.nextInt();

		// Sort the first K elements in ascending order
		// Create a sublist for the first K elements and sort it
		List<Integer> firstK = new ArrayList<>(list.subList(0, K));
		Collections.sort(firstK);

		// Sort the remaining elements in descending order
		// Create a sublist for the remaining elements and sort it in reverse order
		List<Integer> remaining = new ArrayList<>(list.subList(K, N));
		Collections.sort(remaining, Collections.reverseOrder());

		// Combine the sorted lists and print the result
		for (int i = 0; i < K; i++) {
			System.out.print(firstK.get(i) + " ");
		}
		for (int i = 0; i < remaining.size(); i++) {
			System.out.print(remaining.get(i) + (i == remaining.size() - 1 ? "" : " "));
		}
		System.out.println();

		scan.close();
	}
}