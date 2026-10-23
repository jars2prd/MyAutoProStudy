package com.myprog;

import java.util.ArrayList;
import java.util.*;

public class _2Solution
{
    public static int soldierAtK(int num, int[][] soldierPosition, int posSoldier)
    {
        // Initialize the soldiers in an ArrayList from 1 to num
        List<Integer> soldiers = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            soldiers.add(i);
        }

        // Simulate each action
        for (int[] action : soldierPosition) {
            int row = action[0]; // 1-based index for row
            int col = action[1]; // 1-based index for col

            // Adjust to 0-based indexing for ArrayList
            int leftIdx = row - 1;
            int rightIdx = col - 1;

            while (leftIdx < rightIdx) {
                // Perform the swap
                int temp = soldiers.get(leftIdx);
                soldiers.set(leftIdx, soldiers.get(rightIdx));
                soldiers.set(rightIdx, temp);

                // Move to the next pair for swapping
                leftIdx++;
                rightIdx--;
            }
        }

        // Return the ID of the soldier at the Kth (posSoldier) position
        // Adjust posSoldier to 0-based index
        return soldiers.get(posSoldier - 1);
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        // input for num
        int num = in.nextInt();
        // input for soldierPosition
        int soldierPosition_row = in.nextInt();
        int soldierPosition_col = in.nextInt(); // This seems to be the number of columns (always 2 for row and col)
        int soldierPosition[][] = new int[soldierPosition_row][soldierPosition_col];
        for(int idx = 0; idx < soldierPosition_row; idx++)
        {
            for(int jdx = 0; jdx < soldierPosition_col; jdx++) {
                soldierPosition[idx][jdx] = in.nextInt();
            }
        }
        int posSoldier = in.nextInt();
        int result = soldierAtK(num, soldierPosition, posSoldier);
        System.out.println(result); // Print the result
        in.close(); // Close the scanner
    }
}
