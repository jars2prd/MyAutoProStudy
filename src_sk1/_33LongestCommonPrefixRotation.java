package com.myprog;

import java.util.Scanner;

//Program 33 : Rotation of string to find the longest common prefix
public class _33LongestCommonPrefixRotation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input strings
        String first = sc.next();
        String second = sc.next();

        int len = first.length();
        int maxPrefix = 0;
        int minRotations = -1;

        for (int i = 0; i < len; i++) {
            // Find longest common prefix length for this rotation
            int prefixLen = 0;
            for (int j = 0; j < len; j++) {
                if (first.charAt(j) == second.charAt(j)) {
                    prefixLen++;
                } else {
                    break;
                }
            }

            // Update if we found a better prefix
            if (prefixLen > maxPrefix) {
                maxPrefix = prefixLen;
                minRotations = i;
            }

            // Rotate the string (first char to end)
            first = first.substring(1) + first.charAt(0);
        }

        System.out.println(minRotations);
        sc.close();
    }
}