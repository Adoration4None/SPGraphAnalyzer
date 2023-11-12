package co.edu.uniquindio.proyectoanalisis.logic;

/*
   Took from GeeksforGeeks (no contributor mentioned)
   https://www.geeksforgeeks.org/johnsons-algorithm/
 */

public class JohnsonAlgorithm {
    private static final int INF = 99999;

    // Number of vertices in the graph
    private int V;

    // A utility function to find the vertex with minimum
    // distance value, from the set of vertices not yet
    // included in shortest path tree
    private int minDistance(int[] dist, boolean[] sptSet)
    {
        // Initialize min value
        int min = INF, min_index = -1;

        for (int v = 0; v < V; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }

    // A utility function to print the constructed distance
    // array
    private void printSolution(int[][] dist)
    {
        System.out.println(
                "Following matrix shows the shortest distances "
                        + "between every pair of vertices:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF)
                    System.out.printf("%7s", "INF");
                else
                    System.out.printf("%7d", dist[i][j]);
            }
            System.out.println();
        }
    }

    // Solves the all-pairs shortest path problem using
    // Floyd Warshall algorithm
    public void johnson(int[][] graph)
    {
        FloydWarshallAlgorithm algorithm = new FloydWarshallAlgorithm(V);
        int[][] dist = new int[V][V];
        int i, j, k;

		/* Initialize the solution matrix same as input
		graph matrix. Or we can say the initial values of
		shortest distances are based on shortest paths
		considering no intermediate vertex. */
        for (i = 0; i < V; i++)
            for (j = 0; j < V; j++)
                dist[i][j] = graph[i][j];

		algorithm.floydWarshall(dist);

        // Print the shortest distance matrix
        //printSolution(dist);
    }

    public JohnsonAlgorithm(int V) {
        this.V = V;
    }
}

