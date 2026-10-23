package com.prd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _72_MaxAppExecute {
	static class Application {
        int requestTime;
        int endTime;

        public Application(int requestTime, int endTime) {
            this.requestTime = requestTime;
            this.endTime = endTime;
        }
    }

    public static void main(String[] args) throws IOException {
        // Read input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().trim().split("\\s+");
        int num = Integer.parseInt(firstLine[0]);    // number of applications
        int constx = Integer.parseInt(firstLine[1]); // number of parameters per app (should be 3)

        // Map resourceID -> List of Applications
        Map<Integer, List<Application>> resourceApps = new HashMap<Integer, List<Application>>();

        for (int i = 0; i < num; i++) {
            String[] parts = br.readLine().trim().split("\\s+");
            int requestTime = Integer.parseInt(parts[0]);
            int endTime = Integer.parseInt(parts[1]);
            int resourceID = Integer.parseInt(parts[2]);

            if (!resourceApps.containsKey(resourceID)) {
                resourceApps.put(resourceID, new ArrayList<Application>());
            }
            resourceApps.get(resourceID).add(new Application(requestTime, endTime));
        }

        int totalMax = 0;

        // Process each resource independently
        for (Map.Entry<Integer, List<Application>> entry : resourceApps.entrySet()) {
            List<Application> appsList = entry.getValue();

            // Map to store one application per request time with earliest end time
            Map<Integer, Integer> requestTimeMap = new HashMap<Integer, Integer>();

            for (Application app : appsList) {
                if (!requestTimeMap.containsKey(app.requestTime) ||
                    app.endTime < requestTimeMap.get(app.requestTime)) {
                    requestTimeMap.put(app.requestTime, app.endTime);
                }
            }

            // Convert map entries back to list of Application objects
            List<Application> filteredApps = new ArrayList<Application>();
            for (Map.Entry<Integer, Integer> e : requestTimeMap.entrySet()) {
                filteredApps.add(new Application(e.getKey(), e.getValue()));
            }

            // Sort by end time
            Collections.sort(filteredApps, new Comparator<Application>() {
                @Override
                public int compare(Application a1, Application a2) {
                    return a1.endTime - a2.endTime;
                }
            });

            // Greedy interval scheduling
            int count = 0;
            int lastEnd = -1;
            for (Application app : filteredApps) {
                if (app.requestTime >= lastEnd) {
                    count++;
                    lastEnd = app.endTime;
                }
            }

            totalMax += count;
        }

        System.out.println(totalMax);
    }
}

/*
6 3
1200 1250 1
1210 1220 1
1225 1230 1
1330 1345 2
1330 1340 2
1340 1345 2

OP: 4
*/