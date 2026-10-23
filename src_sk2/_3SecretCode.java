package com.myprog;

import java.util.Scanner;

public class _3SecretCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("My first program");
		
		Scanner scanner = new Scanner(System.in);

        // Read input values
        long secretCode = scanner.nextLong();
        long firstKey = scanner.nextLong();
        long secondKey = scanner.nextLong();

        long MOD = 1000000007; // The large prime modulus

        // Step 1: Calculate (secretCode ^ firstKey) % 10
        // We need to calculate (secretCode ^ firstKey) first, then take modulo 10
        // Since secretCode^firstKey can be very large, we need modular exponentiation
        // but the modulo here is 10, so it's simpler to just calculate (secretCode % 10) ^ firstKey % 10
        // However, the problem states (S^N)%10, which implies S^N can be large before %10
        // A direct calculation of S^N can lead to overflow for large S and N.
        // The power function can be used with a modulus of 10 for the inner part.
        long innerResult = power(secretCode, firstKey, 10); // Calculate (S^N) % 10

        // Step 2: Calculate (innerResult ^ secondKey) % MOD
        long finalResult = power(innerResult, secondKey, MOD); // Calculate ((((S^N)%10)^M)%1000000007)

        // Print the encrypted code
        System.out.println(finalResult);

        scanner.close();
		

	}
	
	public static long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if (exp % 2 == 1) { // If exp is odd, multiply base with result
                res = (res * base) % mod;
            }
            base = (base * base) % mod; // Square the base
            exp /= 2; // Halve the exponent
        }
        return res;
    }

}
