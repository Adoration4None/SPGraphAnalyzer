package co.edu.uniquindio.proyectoanalisis.logic;

import java.util.*;

// Main class DPQ
public class DijkstraPriorityQueueAlgorithm {

    // Member variables of this class
    private int dist[];
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    // Number of vertices
    private int V;
    int[][] graph;

    // Constructor of this class
    public DijkstraPriorityQueueAlgorithm(int V) {

        // This keyword refers to the current object itself
        this.V = V;
        dist = new int[V];
        settled = new HashSet<Integer>();
        pq = new PriorityQueue<Node>(V, new Node());
    }

    // Method 1
    // Dijkstra's Algorithm
    public void dijkstra(int[][] graph, int src) {
        this.graph = graph;

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // Add the source node to the priority queue
        pq.add(new Node(src, 0));

        // Distance to the source is 0
        dist[src] = 0;

        while (settled.size() != V) {

            // Terminating condition check when
            // the priority queue is empty, return
            if (pq.isEmpty())
                return;

            // Removing the minimum distance node
            // from the priority queue
            int u = pq.remove().node;

            // Adding the node whose distance is
            // finalized
            if (settled.contains(u))

                // Continue keyword skips execution for
                // following check
                continue;

            // We don't have to call e_Neighbors(u)
            // if u is already present in the settled set.
            settled.add(u);

            e_Neighbors(u);
        }
    }

    // Method 2
    // To process all the neighbors
    // of the passed node
    private void e_Neighbors(int u) {

        int edgeDistance = -1;
        int newDistance = -1;

        // All the neighbors of v
        for (int v = 0; v < V; v++) {
            if (graph[u][v] > 0) {
                // If the current node hasn't already been processed
                if (!settled.contains(v)) {
                    edgeDistance = graph[u][v];
                    newDistance = dist[u] + edgeDistance;

                    // If the new distance is cheaper in cost
                    if (newDistance < dist[v])
                        dist[v] = newDistance;

                    // Add the current node to the queue
                    pq.add(new Node(v, dist[v]));
                }
            }
        }
    }
}

// Class 2
// Helper class implementing the Comparator interface
// Representing a node in the graph
class Node implements Comparator<Node> {

    // Member variables of this class
    public int node;
    public int cost;

    // Constructors of this class

    // Constructor 1
    public Node() {}

    // Constructor 2
    public Node(int node, int cost) {

        // This keyword refers to the current instance itself
        this.node = node;
        this.cost = cost;
    }

    // Method 1
    @Override
    public int compare(Node node1, Node node2) {

        if (node1.cost < node2.cost)
            return -1;

        if (node1.cost > node2.cost)
            return 1;

        return 0;
    }
}

