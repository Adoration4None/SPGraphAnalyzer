package co.edu.uniquindio.proyectoanalisis.logic;

/*
   Took from GeeksforGeeks
   https://www.geeksforgeeks.org/dials-algorithm-optimized-dijkstra-for-small-range-weights/
 */

import java.util.*;

public class DialAlgorithm {
    private final int INF = Integer.MAX_VALUE;

    public DialAlgorithm() {

    }

    public void shortestPath(int[][] graph, int src, int W) {
        int V = graph.length;
        int[] dist = new int[V];
        Arrays.fill(dist, INF);

        // Create buckets B[].
        ArrayList<Integer>[] B = new ArrayList[W * V + 1];
        for (int i = 0; i < W * V + 1; i++)
            B[i] = new ArrayList<>();

        B[0].add(src);
        dist[src] = 0;

        int idx = 0;
        while (true) {
            while (B[idx].isEmpty() && idx < W * V)
                idx++;

            if (idx == W * V)
                break;

            int u = B[idx].get(0);
            B[idx].remove(0);

            for (int v = 0; v < V; v++) {
                int weight = graph[u][v];

                int du = dist[u];
                int dv = dist[v];

                if (weight != 0 && dv > du + weight) {
                    dist[v] = du + weight;
                    dv = dist[v];
                    B[dv].add(0, v);
                }
            }
        }

        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < V; ++i)
            System.out.println(i + "\t\t" + dist[i]);
    }


}

