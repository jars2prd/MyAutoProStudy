package com.myprog;

import java.util.*;

//Program 13 : Number of working weeks 
public class _13ProjectScheduler {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read N (number of projects)
        int N = scanner.nextInt();

        // Read project modules into an array or list
        List<Integer> projModules = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            projModules.add(scanner.nextInt());
        }

        // Sort the project modules in descending order
        Collections.sort(projModules, Collections.reverseOrder());

        int weeks = 0;
        int lastUsedProjectIndex = -1; // To track the index of the last used project

        // Simulate the scheduling
        // This part would involve a loop and logic to select projects based on constraints
        // A more robust solution might involve a priority queue or more complex tracking
        // of available projects and their module counts to efficiently find the next best project.

        // Example logic (simplified for illustration, needs refinement for full problem constraints):
        while (true) {
            boolean moduleSelectedThisWeek = false;
            int currentProjectToUse = -1; // Index of the project to use this week

            // Try to find a project different from the last used one
            for (int i = 0; i < N; i++) {
                if (i != lastUsedProjectIndex && projModules.get(i) > 0) {
                    currentProjectToUse = i;
                    break; // Select the first available different project (after sorting)
                }
            }

            if (currentProjectToUse != -1) {
                projModules.set(currentProjectToUse, projModules.get(currentProjectToUse) - 1);
                weeks++;
                lastUsedProjectIndex = currentProjectToUse;
                moduleSelectedThisWeek = true;
            } else {
                // If no different project can be selected, try the same project if no other option
                if (lastUsedProjectIndex != -1 && projModules.get(lastUsedProjectIndex) > 0) {
                    // This scenario would violate the "different projects in successive weeks" rule.
                    // The greedy approach needs to be smarter to avoid this state or handle it.
                    // A proper solution would involve considering the next best alternative carefully.
                    break; // Cannot select a valid module
                } else {
                    break; // No modules left or no valid selection possible
                }
            }

            if (!moduleSelectedThisWeek) {
                break; // No module could be selected in this iteration
            }
        }


        System.out.println(weeks);
        scanner.close();
    }
}
