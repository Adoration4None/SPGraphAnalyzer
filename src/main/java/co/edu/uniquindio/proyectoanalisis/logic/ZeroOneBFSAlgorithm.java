package co.edu.uniquindio.proyectoanalisis.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

public class ZeroOneBFSAlgorithm {
    private int V;
    private ArrayList<Node>[] edges;

    private static class Node {
        int to;
        int weight;

        public Node(int to, int wt) {
            this.to = to;
            this.weight = wt;
        }
    }

    public ZeroOneBFSAlgorithm(int v) {
        this.V = v;
        edges = new ArrayList[V];

        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<>();
        }
    }

    private void addEdge(int u, int v, int wt) {
        edges[u].add(new Node(v, wt));
        edges[v].add(new Node(u, wt));
    }

    private void zeroOneBFS(int src) {
        int[] dist = new int[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        dist[src] = 0;
        queue.addLast(src);

        while (!queue.isEmpty()) {
            int v = queue.removeFirst();
            for (Node edge : edges[v]) {
                if (dist[edge.to] > dist[v] + edge.weight) {
                    dist[edge.to] = dist[v] + edge.weight;
                    if (edge.weight == 0) {
                        queue.addFirst(edge.to);
                    } else {
                        queue.addLast(edge.to);
                    }
                }
            }
        }

        for (int i = 0; i < dist.length; i++) {
            System.out.print(dist[i] + " ");
        }
    }

    public void run(int[][] matrix) {
        ZeroOneBFSAlgorithm graph = new ZeroOneBFSAlgorithm(V);

        // Leer la matriz de adyacencia desde el archivo
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {

                int weight = matrix[i][j];

                if (weight > 0) {
                    graph.addEdge(i, j, weight);
                }
            }
        }

        graph.zeroOneBFS(0);
    }

}

