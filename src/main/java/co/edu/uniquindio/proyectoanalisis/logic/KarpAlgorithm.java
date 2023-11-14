package co.edu.uniquindio.proyectoanalisis.logic;

// This code is contributed by sanjeev2552
/*
   Took from GeeksforGeeks
   https://www.geeksforgeeks.org/karps-minimum-mean-average-weight-cycle-algorithm/
 */

public class KarpAlgorithm {
    private int V = 4;

    public KarpAlgorithm(int v) {
        V = v;
    }

    // calculates the shortest path
    private void shortestPath(int[][] dp) {
        // initializing all distances as -1
        for (int i = 0; i <= V; i++)
            for (int j = 0; j < V; j++)
                dp[i][j] = -1;

        // shortest distance from first vertex
        // to in itself consisting of 0 edges
        dp[0][0] = 0;

        // filling up the dp table
        for (int i = 1; i <= V; i++) {
            for (int j = 0; j < V; j++) {
                for (int k = 0; k < V; k++) {
                    if (dp[i - 1][k] != -1 && k != j) {
                        int curr_wt = dp[i - 1][k] + dp[0][j];
                        if (dp[i][j] == -1)
                            dp[i][j] = curr_wt;
                        else
                            dp[i][j] = Math.min(dp[i][j], curr_wt);
                    }
                }
            }
        }
    }

    // Returns minimum value of average weight
    // of a cycle in graph.
    public double minAvgWeight(int[][] graph) {
        int[][] dp = new int[V + 1][V];

        // Initialize dp with the given graph
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                dp[0][j] = graph[i][j];

        shortestPath(dp);

        // array to store the avg values
        double[] avg = new double[V];
        for (int i = 0; i < V; i++)
            avg[i] = -1;

        // Compute average values for all vertices using
        // weights of shortest paths store in dp.
        for (int i = 0; i < V; i++) {
            if (dp[V][i] != -1) {
                for (int j = 0; j < V; j++)
                    if (dp[j][i] != -1)
                        avg[i] = Math.max(avg[i], ((double) dp[V][i] - dp[j][i]) / (V - j));
            }
        }

        // Find minimum value in avg[]
        double result = avg[0];
        for (int i = 0; i < V; i++)
            if (avg[i] != -1 && avg[i] < result)
                result = avg[i];

        return result;
    }
}

