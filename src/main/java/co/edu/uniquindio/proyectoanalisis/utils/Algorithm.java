package co.edu.uniquindio.proyectoanalisis.utils;

import co.edu.uniquindio.proyectoanalisis.logic.BellmanFordAlgorithm;
import co.edu.uniquindio.proyectoanalisis.logic.DijkstraAlgorithm;

public class Algorithm {
    private int id;
    private String name;
    private String fileName;

    public Algorithm() {
    }

    public Algorithm(int id, String name, String fileName) {
        this.id = id;
        this.name = name;
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Runs a specific algorithm according to the id
     * @param graph
     * @return execution time
     */
    public double run(int[][] graph) {
        double initTime, endTime;

        if(id == 1) {
            DijkstraAlgorithm algorithm = new DijkstraAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.dijkstra(graph, 0);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 2) {
            BellmanFordAlgorithm algorithm = new BellmanFordAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.bellmanFord(graph, 0);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 3) {
            DijkstraAlgorithm algorithm = new DijkstraAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.dijkstra(graph, 0);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 4) {
            DijkstraAlgorithm algorithm = new DijkstraAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.dijkstra(graph, 0);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 5) {
            DijkstraAlgorithm algorithm = new DijkstraAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.dijkstra(graph, 0);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 6) {
            DijkstraAlgorithm algorithm = new DijkstraAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.dijkstra(graph, 0);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 7) {
            DijkstraAlgorithm algorithm = new DijkstraAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.dijkstra(graph, 0);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 8) {
            DijkstraAlgorithm algorithm = new DijkstraAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.dijkstra(graph, 0);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 9) {
            DijkstraAlgorithm algorithm = new DijkstraAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.dijkstra(graph, 0);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 10) {
            DijkstraAlgorithm algorithm = new DijkstraAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.dijkstra(graph, 0);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 11) {
            DijkstraAlgorithm algorithm = new DijkstraAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.dijkstra(graph, 0);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 12) {
            DijkstraAlgorithm algorithm = new DijkstraAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.dijkstra(graph, 0);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }

        return 0;
    }
}
