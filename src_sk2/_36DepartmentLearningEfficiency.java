package com.myprog;

import java.util.*;
import java.util.*;

//Program 36 : Find the department Learning Efficiency : 
public class _36DepartmentLearningEfficiency {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: learningEfficiency_size (N)
        int N = scanner.nextInt();

        // Input: learningEfficiency array
        int[] learningEfficiency = new int[N];
        for (int i = 0; i < N; i++) {
            learningEfficiency[i] = scanner.nextInt();
        }

        // Input: ide_size (N)
        int ide_size = scanner.nextInt(); // This is redundant as it's always N

        // Input: teamID array
        int[] teamID = new int[N];
        for (int i = 0; i < N; i++) {
            teamID[i] = scanner.nextInt();
        }

        // Input: Q (total learning projects) and P (elements in each Q line, always 2)
        int Q = scanner.nextInt();
        int P = scanner.nextInt(); // Always 2

        // Store selected employees and their efficiencies
        Set<Integer> selectedEmployeeIDs = new HashSet<>();

        long initialDepartmentEfficiency = 0;
        for (int eff : learningEfficiency) {
            initialDepartmentEfficiency += eff;
        }

        List<Long> departmentEfficiencies = new ArrayList<>();

        for (int q = 0; q < Q; q++) {
            int selectedEmployeeID = scanner.nextInt();
            int K = scanner.nextInt();

            // Mark the selected employee as participating
            selectedEmployeeIDs.add(selectedEmployeeID);

            // Find K employees with lowest learning efficiencies from the same team
            int selectedEmployeeIndex = -1;
            for (int i = 0; i < N; i++) {
                if (i + 1 == selectedEmployeeID) {
                    selectedEmployeeIndex = i;
                    break;
                }
            }

            int selectedEmployeeTeamID = teamID[selectedEmployeeIndex];

            List<Integer> teamMembersEfficiencies = new ArrayList<>();
            List<Integer> teamMembersIDs = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                if (teamID[i] == selectedEmployeeTeamID && !selectedEmployeeIDs.contains(i + 1)) {
                    teamMembersEfficiencies.add(learningEfficiency[i]);
                    teamMembersIDs.add(i + 1);
                }
            }

            // Sort team members by efficiency to find K lowest
            List<Map.Entry<Integer, Integer>> sortedTeamMembers = new ArrayList<>();
            for (int i = 0; i < teamMembersEfficiencies.size(); i++) {
                sortedTeamMembers.add(new AbstractMap.SimpleEntry<>(teamMembersEfficiencies.get(i), teamMembersIDs.get(i)));
            }
            sortedTeamMembers.sort(Comparator.comparingInt(Map.Entry::getKey));

            int count = 0;
            for (Map.Entry<Integer, Integer> entry : sortedTeamMembers) {
                if (count < K) {
                    selectedEmployeeIDs.add(entry.getValue());
                    count++;
                } else {
                    break;
                }
            }

            // Calculate new department efficiency
            long currentDepartmentEfficiency = 0;
            for (int i = 0; i < N; i++) {
                if (!selectedEmployeeIDs.contains(i + 1)) {
                    currentDepartmentEfficiency += learningEfficiency[i];
                }
            }
            departmentEfficiencies.add(currentDepartmentEfficiency);
        }

        // Output the results
        for (long efficiency : departmentEfficiencies) {
            System.out.print(efficiency + " ");
        }
        System.out.println();

        scanner.close();
    }
}