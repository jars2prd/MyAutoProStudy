package com.prd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class _80_MinRouteInCity {
	static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // GCD helper for slope normalization
    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static int minRoutes(List<Point> points) {
        Set<String> remaining = new HashSet<String>();
        for (Point p : points) {
            remaining.add(p.x + "," + p.y);
        }

        int routes = 0;

        while (!remaining.isEmpty()) {
            // Pick any base point
            String first = remaining.iterator().next();
            String[] parts = first.split(",");
            Point base = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));

            // Map from slope → points on that line
            Map<String, List<String>> slopeMap = new HashMap<String, List<String>>();

            for (String s : remaining) {
                if (s.equals(first)) continue;
                String[] arr = s.split(",");
                Point p = new Point(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));

                int dx = p.x - base.x;
                int dy = p.y - base.y;

                // Normalize slope
                if (dx == 0) {  // vertical
                    dy = 1; dx = 0;
                } else if (dy == 0) { // horizontal
                    dy = 0; dx = 1;
                } else {
                    int g = gcd(Math.abs(dy), Math.abs(dx));
                    dy /= g;
                    dx /= g;
                    if (dx < 0) { // keep dx positive for consistency
                        dx = -dx;
                        dy = -dy;
                    }
                }

                String slopeKey = dy + "/" + dx;
                if (!slopeMap.containsKey(slopeKey)) {
                    slopeMap.put(slopeKey, new ArrayList<String>());
                }
                slopeMap.get(slopeKey).add(s);
            }

            // Find slope with most points
            int maxPoints = 0;
            List<String> bestLine = new ArrayList<String>();
            for (List<String> linePoints : slopeMap.values()) {
                if (linePoints.size() > maxPoints) {
                    maxPoints = linePoints.size();
                    bestLine = linePoints;
                }
            }

            // Always include base point
            bestLine.add(first);

            // Remove all points on this best line
            for (String s : bestLine) {
                remaining.remove(s);
            }

            routes++;
        }

        return routes;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numLoc = sc.nextInt();
        int numCoord = sc.nextInt(); // always 2

        List<Point> points = new ArrayList<Point>();
        for (int i = 0; i < numLoc; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            points.add(new Point(x, y));
        }
        sc.close();

        if (numLoc == 0) {
            System.out.println(0);
        } else {
            System.out.println(minRoutes(points));
        }
    }
}

/*
8 2 
1 4
2 3
2 1
3 2
4 1
5 0
4 3
5 4

OP: 2
*/