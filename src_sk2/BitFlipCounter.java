package com.myprog;
import java.util.*;

public class BitFlipCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Read inputs
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();
        
        // XOR to find differing bits
        int xorResult = num1 ^ num2;
        
        // Count the 1s in the XOR result
        int count = 0;
        while (xorResult != 0) {
            count += xorResult & 1;
            xorResult >>= 1;
        }
        
        // Output result
        System.out.println(count);
    }
}