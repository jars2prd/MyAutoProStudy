package com.myprog;
import java.util.*;

class Node {
    int value;
    List<Node> neighbors;
    boolean isLeaf;

    public Node(int value) {
        this.value = value;
        this.neighbors = new ArrayList<>();
        this.isLeaf = true; // Assume leaf initially, update later
    }
}

public class _82MaxProductPathInTree {

    private static long maxProduct = Long.MIN_VALUE;

    public static void main(String[] args) {
        // Input from the image:
        // N = 4 (number of nodes)
        // Node values: -1, 2, 3, 2 (adjusted to 0-indexed for array)
        // Edges: (1,2), (1,3), (3,4)

        int N = 4;
        int[] nodeValues = {-1, 2, 3, 2}; // Node values for nodes 1, 2, 3, 4 respectively

        // Create nodes
        Node[] nodes = new Node[N + 1]; // Using 1-based indexing for nodes
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(nodeValues[i - 1]);
        }

        // Build the tree (add edges)
        // Edge 1-2
        nodes[1].neighbors.add(nodes[2]);
        nodes[2].neighbors.add(nodes[1]);
        nodes[1].isLeaf = false; // Node 1 is not a leaf
        nodes[2].isLeaf = false; // Node 2 is not a leaf initially, but becomes a leaf after connecting to 1 only

        // Edge 1-3
        nodes[1].neighbors.add(nodes[3]);
        nodes[3].neighbors.add(nodes[1]);
        nodes[1].isLeaf = false; // Node 1 is not a leaf
        nodes[3].isLeaf = false; // Node 3 is not a leaf

        // Edge 3-4
        nodes[3].neighbors.add(nodes[4]);
        nodes[4].neighbors.add(nodes[3]);
        nodes[3].isLeaf = false; // Node 3 is not a leaf

        // Determine actual leaves
        for (int i = 1; i <= N; i++) {
            if (nodes[i].neighbors.size() == 1 && i != 1) { // A node is a leaf if it has only one neighbor and is not the root
                nodes[i].isLeaf = true;
            } else if (nodes[i].neighbors.isEmpty()) { // Isolated node (shouldn't happen in a connected tree)
                nodes[i].isLeaf = true;
            } else {
                nodes[i].isLeaf = false;
            }
        }
        // Special handling for the root if it's also a leaf in a single-node tree (not this case)
        if (N == 1) nodes[1].isLeaf = true;


        // Find all leaves
        List<Node> leaves = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (nodes[i].isLeaf) {
                leaves.add(nodes[i]);
            }
        }

        // Iterate through all pairs of leaves and find the path with maximum product
        for (int i = 0; i < leaves.size(); i++) {
            for (int j = i + 1; j < leaves.size(); j++) {
                Node leaf1 = leaves.get(i);
                Node leaf2 = leaves.get(j);
                findPathAndProduct(leaf1, leaf2, new ArrayList<>(), new HashSet<>());
            }
        }

        System.out.println(maxProduct); // Output: -12
    }

    private static void findPathAndProduct(Node current, Node target, List<Node> currentPath, Set<Node> visited) {
        currentPath.add(current);
        visited.add(current);

        if (current == target) {
            long product = 1;
            for (Node node : currentPath) {
                product *= node.value;
            }
            maxProduct = Math.max(maxProduct, product);
        } else {
            for (Node neighbor : current.neighbors) {
                if (!visited.contains(neighbor)) {
                    findPathAndProduct(neighbor, target, currentPath, visited);
                }
            }
        }

        currentPath.remove(currentPath.size() - 1); // Backtrack
        visited.remove(current);
    }
}