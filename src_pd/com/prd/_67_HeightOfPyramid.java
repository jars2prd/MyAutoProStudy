package com.prd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _67_HeightOfPyramid {
	public static int maxPyramidHeight(int[] arr) {
       // Arrays.sort(arr);

        int height = 0;
        long prevSum = 0;   // use long to avoid integer overflow
        long currentSum = 0;

        for (int size : arr) {
            currentSum += size;

            if (currentSum >= prevSum) {
                height++;
                prevSum = currentSum;
                currentSum = 0;
            }
        }

        return height;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        int result = maxPyramidHeight(arr);
        System.out.println(result);
    }
}

/*
4
40 100 20 30

OP: 2
*/
