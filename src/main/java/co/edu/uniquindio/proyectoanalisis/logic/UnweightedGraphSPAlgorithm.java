package co.edu.uniquindio.proyectoanalisis.logic;

// This code is contributed by Sahil Vaid
/*
   Took from GeeksforGeeks
   https://www.geeksforgeeks.org/shortest-path-unweighted-graph/
 */

import java.util.*;

public class UnweightedGraphSPAlgorithm {
    private int V;

    public UnweightedGraphSPAlgorithm(int v) {
        this.V = v;
    }

    public void run(int[][] graph, int src) {
        for (int i = 0; i < V; i++) {
            System.out.println("Shortest path from node 0 to node " + i + ":");
            printShortestDistance(graph, src, i, V);
            System.out.println();
        }
    }

    private void printShortestDistance(int[][] adjacencyMatrix, int src, int dest, int v) {
        int pred[] = new int[v];
        int dist[] = new int[v];

        if (!BFS(adjacencyMatrix, src, dest, v, pred, dist)) {
            System.out.println("No path from node " + src + " to node " + dest);
            return;
        }

        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }

        System.out.println("Shortest path length from node " + src + " to node " + dest + " is: " + dist[dest]);
        System.out.print("Path is :: ");
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
    }

    private static boolean BFS(int[][] adjacencyMatrix, int src, int dest, int v, int pred[], int dist[]) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean visited[] = new boolean[v];

        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }

        visited[src] = true;
        dist[src] = 0;
        queue.add(src);

        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < v; i++) {
                if (adjacencyMatrix[u][i] == 1 && visited[i] == false) {
                    visited[i] = true;
                    dist[i] = dist[u] + 1;
                    pred[i] = u;
                    queue.add(i);
                }
            }
        }
        return true;
    }
}


