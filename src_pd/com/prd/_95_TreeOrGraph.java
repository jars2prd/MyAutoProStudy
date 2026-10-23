package com.prd;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class _95_TreeOrGraph {
	// Driver method 
    public static void main(String args[]) 
    { 
    	Scanner scn = new Scanner(System.in);
    	
    	int edges = scn.nextInt();
    	
    	GraphOrTree g1 = new GraphOrTree(edges);
    	
    	int edge1A = 0;
    	int edge1B = 0;
    	for(int i=0;i<edges; i++) {
    		 edge1A = scn.nextInt();
        	 edge1B = scn.nextInt();
        	g1.addEdge(edge1A, edge1B);
    	}
    	
    	if(g1.isTree()) {
    		System.out.println("1");
    	} else {
    		System.out.println("0");
    	}
    	
    } 
}

class GraphOrTree {
	private int V; // No. of vertices 
    private LinkedList<Integer> adj[]; //Adjacency List 

    // Constructor 
  
    @SuppressWarnings("unchecked")
    GraphOrTree(int v) 
    { 
        V = v; 
        adj = new LinkedList[10];//[V]; 
        for (int i=0; i<10; ++i) 
            adj[i] = new LinkedList<Integer>(); 
    } 

    // Function to add an edge into the graph 
    void addEdge(int v,int w) 
    { 
        adj[v].add(w); 
        adj[w].add(v); 
    } 

    
    // A recursive function that uses visited[] and parent 
    // to detect cycle in subgraph reachable from vertex v. 
    boolean isCyclicUtil(int v, boolean visited[], int parent) 
    { 
        // Mark the current node as visited 
        visited[v] = true; 
        Integer i; 

        // Recur for all the vertices adjacent to this vertex 
        Iterator<Integer> it = adj[v].iterator(); 
        while (it.hasNext()) 
        { 
            i = it.next(); 

            // If an adjacent is not visited, then recur for 
            // that adjacent 
            if (!visited[i]) 
            { 
                if (isCyclicUtil(i, visited, v)) 
                    return true; 
            } 

            // If an adjacent is visited and not parent of 
            // current vertex, then there is a cycle. 
            else if (i != parent) 
            return true; 
        } 
        return false; 
    } 

    // Returns true if the graph is a tree, else false. 
    boolean isTree() 
    { 
        // Mark all the vertices as not visited and not part 
        // of recursion stack 
        boolean visited[] = new boolean[10];//[V]; 
        for (int i = 0; i < V; i++) 
            visited[i] = false; 

        // The call to isCyclicUtil serves multiple purposes 
        // It returns true if graph reachable from vertex 0 
        // is cyclic. It also marks all vertices reachable 
        // from 0. 
        if (isCyclicUtil(0, visited, -1)) 
            return false; 

        // If we find a vertex which is not reachable from 0 
        // (not marked by isCyclicUtil(), then we return false 
        for (int u = 0; u < V; u++) 
            if (!visited[u]) 
                return false; 

        return true; 
    } 
}

/*
2 2 
0 1
1 1

OP: 0
*/