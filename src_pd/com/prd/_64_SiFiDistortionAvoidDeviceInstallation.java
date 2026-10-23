package com.prd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class _64_SiFiDistortionAvoidDeviceInstallation {
	static class Point {
        long x, y;
        Point(long x, long y) { this.x = x; this.y = y; }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point p = (Point) o;
            return x == p.x && y == p.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    
    static class Segment {
        Point p1, p2;
        Segment(Point p1, Point p2) {
            this.p1 = p1; this.p2 = p2;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] first = br.readLine().trim().split(" ");
        int n = Integer.parseInt(first[0]); // number of routes
        int coordCount = Integer.parseInt(first[1]); // always 4
        
        List<Segment> segments = new ArrayList<Segment>();
        
        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().trim().split(" ");
            long x1 = Long.parseLong(parts[0]);
            long y1 = Long.parseLong(parts[1]);
            long x2 = Long.parseLong(parts[2]);
            long y2 = Long.parseLong(parts[3]);
            segments.add(new Segment(new Point(x1, y1), new Point(x2, y2)));
        }
        
        Set<Point> intersections = new HashSet<Point>();
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Point inter = getIntersection(segments.get(i), segments.get(j));
                if (inter != null) {
                    intersections.add(inter);
                }
            }
        }
        
        System.out.println(intersections.size());
    }
    
    // Finds intersection point of two line segments
    static Point getIntersection(Segment s1, Segment s2) {
        long x1 = s1.p1.x, y1 = s1.p1.y;
        long x2 = s1.p2.x, y2 = s1.p2.y;
        long x3 = s2.p1.x, y3 = s2.p1.y;
        long x4 = s2.p2.x, y4 = s2.p2.y;
        
        long denom = (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4);
        if (denom == 0) return null; // parallel or overlapping
        
        double px = ((x1*y2 - y1*x2)*(x3 - x4) - (x1-x2)*(x3*y4 - y3*x4)) / (double)denom;
        double py = ((x1*y2 - y1*x2)*(y3 - y4) - (y1-y2)*(x3*y4 - y3*x4)) / (double)denom;
        
        if (onSegment(x1, y1, x2, y2, px, py) && onSegment(x3, y3, x4, y4, px, py)) {
            long rx = Math.round(px * 1000000);
            long ry = Math.round(py * 1000000);
            return new Point(rx, ry);
        }
        return null;
    }
    
    static boolean onSegment(long x1, long y1, long x2, long y2, double px, double py) {
        return px >= Math.min(x1, x2) - 1e-9 && px <= Math.max(x1, x2) + 1e-9 &&
               py >= Math.min(y1, y2) - 1e-9 && py <= Math.max(y1, y2) + 1e-9;
    }
}

/*
3 4
1 1 4 5
0 3 3 2
2 4 5 3
OP: 2
*/
