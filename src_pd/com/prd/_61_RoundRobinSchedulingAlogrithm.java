package com.prd;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class _61_RoundRobinSchedulingAlogrithm {
	static class Task {
        int id;
        int arrival;
        int remaining;
        int waitingTime;
        int lastFinishTime;

        Task(int id, int arrival, int runTime) {
            this.id = id;
            this.arrival = arrival;
            this.remaining = runTime;
            this.waitingTime = 0;
            this.lastFinishTime = arrival;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numT = sc.nextInt();
        int[] arrival = new int[numT];
        for (int i = 0; i < numT; i++) arrival[i] = sc.nextInt();

        int numR = sc.nextInt();
        int[] run = new int[numR];
        for (int i = 0; i < numR; i++) run[i] = sc.nextInt();

        int timeSlot = sc.nextInt();

        if (numT != numR) {
            System.err.println("Error: number of arrival times and run times must be equal.");
            sc.close();
            return;
        }

        Task[] allTasks = new Task[numT];
        for (int i = 0; i < numT; i++) {
            allTasks[i] = new Task(i, arrival[i], run[i]);
        }

        Queue<Task> queue = new LinkedList<Task>();
        int currentTime = 0;
        int index = 0;              // next task to add by arrival order
        int completedTasks = 0;

        while (completedTasks < numT) {
            // enqueue all tasks that have arrived up to currentTime
            while (index < numT && allTasks[index].arrival <= currentTime) {
                queue.add(allTasks[index]);
                index++;
            }

            if (queue.isEmpty()) {
                // FIX: check index before accessing allTasks[index]
                if (index < numT) {
                    // jump time forward to the next arrival
                    currentTime = Math.max(currentTime, allTasks[index].arrival);
                    continue;
                } else {
                    // no pending tasks and no future arrivals -> should not normally happen
                    break;
                }
            }

            Task cur = queue.poll();

            // waiting since its last finish (or arrival for first time)
            cur.waitingTime += (currentTime - cur.lastFinishTime);

            int exec = Math.min(timeSlot, cur.remaining);
            currentTime += exec;
            cur.remaining -= exec;
            cur.lastFinishTime = currentTime;

            // enqueue tasks that arrived while this task was executing
            while (index < numT && allTasks[index].arrival <= currentTime) {
                queue.add(allTasks[index]);
                index++;
            }

            if (cur.remaining > 0) {
                queue.add(cur);
            } else {
                completedTasks++;
            }
        }

        long totalWait = 0;
        for (Task t : allTasks) totalWait += t.waitingTime;

        double avgWait = (double) totalWait / numT;
        System.out.printf("%.2f\n", avgWait);

        sc.close();
    }
}

/*
4
0 1 3 9
4
2 1 7 5
2

OP: 1.00
*/
