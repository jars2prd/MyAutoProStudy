package com.myprog;

//Program 18 : Renovate Houses : Eliminate vowel from string
public class _18HouseRenovation {
    public static void main(String[] args) {
        String houses = "MynameisAnthony"; // Example input
        String result = getNonRenovatedHouses(houses);
        if (!result.isEmpty()) {
            System.out.println(result);
        }
    }

    public static String getNonRenovatedHouses(String houses) {
        StringBuilder nonRenovated = new StringBuilder();
        String vowels = "AEIOUaeiou"; // Define vowels

        for (char houseMark : houses.toCharArray()) {
            // Check if the character is NOT a vowel
            if (vowels.indexOf(houseMark) == -1) {
                nonRenovated.append(houseMark);
            }
        }
        return nonRenovated.toString();
    }
}
