package com.myprog;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Program 14 : Vegan and meat based Pizzas : Sliding display in pizza shop
public class _14PizzaOrderProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read N (total orders)
        int n = scanner.nextInt();

        // Read N order numbers
        List<Integer> orders = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            orders.add(scanner.nextInt());
        }

        // Read K (display screen size)
        int k = scanner.nextInt();

        // Process sliding windows
        for (int i = 0; i <= orders.size() - k; i++) {
            int firstMeatOrder = 0; // Default to 0 if no meat order found
            for (int j = 0; j < k; j++) {
                int currentOrder = orders.get(i + j);
                if (currentOrder < 0) {
                    firstMeatOrder = currentOrder;
                    break; // Found the first meat order in this window
                }
            }
            System.out.print(firstMeatOrder + " ");
        }
        System.out.println(); // For a new line at the end
        scanner.close();
    }
}
