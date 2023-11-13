package co.edu.uniquindio.proyectoanalisis.logic;

//This code is contributed by Aakash Hasija
/*
   Took from GeeksforGeeks
   https://www.geeksforgeeks.org/shortest-path-for-directed-acyclic-graphs/
 */

import java.util.*;

public class DirectedAcyclicAlgorithm {
    static final int INF = Integer.MAX_VALUE;
    private int V;
    private int[][] graph;

    public DirectedAcyclicAlgorithm(int[][] graph, int v) {
        V = v;
        this.graph = graph;
    }

    private void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (int i = 0; i < V; ++i) {
            if (!visited[i] && graph[v][i] != 0) {
                topologicalSortUtil(i, visited, stack);
            }
        }
        stack.push(v);
    }

    public void shortestPath(int s) {
        Stack<Integer> stack = new Stack<>();
        int dist[] = new int[V];

        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        Arrays.fill(dist, INF);
        dist[s] = 0;

        while (!stack.isEmpty()) {
            int u = stack.pop();

            for (int v = 0; v < V; ++v) {
                if (dist[u] != INF && graph[u][v] != 0 && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        for (int i = 0; i < V; i++) {
            if (dist[i] == INF) {
                System.out.print("INF ");
            } else {
                System.out.print(dist[i] + " ");
            }
        }
    }

}

