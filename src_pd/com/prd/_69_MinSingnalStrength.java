package com.prd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class _69_MinSingnalStrength {
	static class Node {
        int index;
        List<Integer> neighbors = new ArrayList<Integer>();

        Node(int index) {
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        int n = input.length();

        // Identify nodes (engineers) positions
        // and build adjacency (since it's a binary tree, each node can have up to 2 children)
        List<Node> nodes = new ArrayList<Node>();

        for (int i = 0; i < n; i++) {
            if (input.charAt(i) == '1') {
                nodes.add(new Node(i));
            }
        }

        // Map from index in input string to Node object for quick access
        Map<Integer, Node> indexToNode = new HashMap<Integer, Node>();
        for (Node node : nodes) {
            indexToNode.put(node.index, node);
        }

        // Build edges between parent and children (undirected because of full duplex connection)
        for (Node node : nodes) {
            int leftIndex = 2 * node.index + 1;
            int rightIndex = 2 * node.index + 2;

            if (leftIndex < n && input.charAt(leftIndex) == '1') {
                Node leftChild = indexToNode.get(leftIndex);
                node.neighbors.add(leftChild.index);
                leftChild.neighbors.add(node.index);
            }

            if (rightIndex < n && input.charAt(rightIndex) == '1') {
                Node rightChild = indexToNode.get(rightIndex);
                node.neighbors.add(rightChild.index);
                rightChild.neighbors.add(node.index);
            }
        }

        // Now compute min signal strength for each node
        // signal strength = max distance to any other node in the graph
        // Use BFS from each node

        // We'll print results in order of nodes by their input indices in ascending order
        // but only for nodes that exist (engineers)

        Collections.sort(nodes, new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                return n1.index - n2.index;
            }
        });

        for (Node node : nodes) {
            int maxDist = bfsMaxDistance(node.index, indexToNode);
            System.out.print(maxDist + " ");
        }
    }

    // BFS from nodeIndex to find max distance to any other node
    private static int bfsMaxDistance(int startIndex, Map<Integer, Node> indexToNode) {
        Queue<Integer> queue = new LinkedList<Integer>();
        Set<Integer> visited = new HashSet<Integer>();

        queue.add(startIndex);
        visited.add(startIndex);

        int distance = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;

            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                Node currentNode = indexToNode.get(current);

                for (int neighbor : currentNode.neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }

        return distance;
    }
}

/*
111110000010000
OP: 3 2 4 3 3 4 
*/