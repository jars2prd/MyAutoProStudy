package com.myprog;

//Program 19 : Count occurences of a word (string) in the parent long string 
public class _19CountOccurrences {
    public static void main(String[] args) {
        String parent = "TimisplayinginthehouseofTimwiththetoysofTim";
        String sub = "Tim";
        int count = 0;
        int lastIndex = 0;

        // Loop to find all occurrences of the substring
        while ((lastIndex = parent.indexOf(sub, lastIndex)) != -1) {
            count++;
            lastIndex += sub.length(); // Move the search starting point past the found occurrence
        }
        System.out.println(count);
    }
}