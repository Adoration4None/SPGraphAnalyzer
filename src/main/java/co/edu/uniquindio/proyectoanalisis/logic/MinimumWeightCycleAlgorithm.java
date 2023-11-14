package co.edu.uniquindio.proyectoanalisis.logic;

// This code is contributed by NarasingaNikhil
/*
   Took from GeeksforGeeks
   https://www.geeksforgeeks.org/find-minimum-weight-cycle-undirected-graph/
 */

import java.util.ArrayList;
import java.util.List;

public class MinimumWeightCycleAlgorithm {
    private int V;

    public MinimumWeightCycleAlgorithm(int v) {
        V = v;
    }

    class Edge {
        public int u;
        public int v;
        public int weight;
    }

    class Graph {
        int V;
        List<Edge> edge;

        public Graph(int V) {
            this.V = V;
            edge = new ArrayList<>();
        }

        // function add edge to graph
        public void addEdge(int u, int v, int w) {
            Edge e = new Edge();
            e.u = u;
            e.v = v;
            e.weight = w;
            edge.add(e);
        }

        // function remove edge from undirected graph
        public void removeEdge(int u, int v, int w) {
            edge.removeIf(e -> (e.u == u && e.v == v && e.weight == w) || (e.u == v && e.v == u && e.weight == w));
        }

        // find the shortest path from source to sink using
        // Dijkstra’s shortest path algorithm [ Time complexity
        // O(E logV )]
        public int shortestPath(int u, int v, int[][] adjMatrix) {
            int[] dist = new int[V];
            boolean[] sptSet = new boolean[V];

            for (int i = 0; i < V; i++) {
                dist[i] = Integer.MAX_VALUE;
                sptSet[i] = false;
            }

            dist[u] = 0;

            for (int count = 0; count < V - 1; count++) {
                int minDist = Integer.MAX_VALUE, minIndex = -1;

                for (int i = 0; i < V; i++) {
                    if (!sptSet[i] && dist[i] <= minDist) {
                        minDist = dist[i];
                        minIndex = i;
                    }
                }

                int current = minIndex;
                sptSet[current] = true;

                for (int i = 0; i < V; i++) {
                    if (!sptSet[i] && adjMatrix[current][i] != 0 && dist[current] != Integer.MAX_VALUE
                            && dist[current] + adjMatrix[current][i] < dist[i]) {
                        dist[i] = dist[current] + adjMatrix[current][i];
                    }
                }
            }

            return dist[v];
        }

        // function return minimum weighted cycle
        public int findMinimumCycle(int[][] adjMatrix) {
            int minCycle = Integer.MAX_VALUE;
            int E = edge.size();
            for (int i = 0; i < E; i++) {
                Edge e = edge.get(i);
                // get current edge vertices which we currently
                // remove from the graph and then find shortest path
                // between these two vertices using Dijkstra’s
                // shortest path algorithm .
                removeEdge(e.u, e.v, e.weight);
                // the minimum distance between these two vertices
                int distance = shortestPath(e.u, e.v, adjMatrix);

                // to make a cycle we have to add the weight of
                // the currently removed edge if this is the
                // shortest cycle then update min_cycle

                minCycle = Math.min(minCycle, distance + e.weight) + 4;
                addEdge(e.u, e.v, e.weight);
            }
            // return the shortest cycle
            return minCycle;
        }
    }

    public void run(int[][] matrix) {
        Graph g = new Graph(V);

        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (matrix[i][j] != 0) {
                    g.addEdge(i, j, matrix[i][j]);
                }
            }
        }

        System.out.println(g.findMinimumCycle(matrix));
    }

}

