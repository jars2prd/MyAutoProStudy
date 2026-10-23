package com.prd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

//Refer _91_ShortedJobFirstScheduling from office laptop

public class _91_ShortedJobFirstScheduling {
	static class Task {
        int requestTime;
        int duration;

        Task(int requestTime, int duration) {
            this.requestTime = requestTime;
            this.duration = duration;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of tasks
        int n = Integer.parseInt(scanner.nextLine());

        // Read request times
        int[] requestTimes = Arrays.stream(scanner.nextLine().split(" "))
                                   .mapToInt(Integer::parseInt)
                                   .toArray();

        // Read durations
        int[] durations = Arrays.stream(scanner.nextLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();

        // Build list of tasks
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tasks.add(new Task(requestTimes[i], durations[i]));
        }

        double avgWaitingTime = calculateAverageWaitingTime(tasks);
        System.out.printf("%.2f\n", avgWaitingTime);
    }

    private static double calculateAverageWaitingTime(List<Task> tasks) {
        tasks.sort(Comparator.comparingInt(t -> t.requestTime)); // sort by arrival

        PriorityQueue<Task> pq = new PriorityQueue<>(Comparator.comparingInt(t -> t.duration));
        int currentTime = 0;
        int totalWaitingTime = 0;
        int index = 0;
        int n = tasks.size();

        while (index < n || !pq.isEmpty()) {
            // Add all tasks that have arrived by current time
            while (index < n && tasks.get(index).requestTime <= currentTime) {
                pq.offer(tasks.get(index));
                index++;
            }

            if (!pq.isEmpty()) {
                Task currentTask = pq.poll();
                totalWaitingTime += currentTime - currentTask.requestTime;
                currentTime += currentTask.duration;
            } else {
                // If no tasks are ready, jump to the next task's request time
                currentTime = tasks.get(index).requestTime;
            }
        }

        return (double) totalWaitingTime / n;
    }
}


