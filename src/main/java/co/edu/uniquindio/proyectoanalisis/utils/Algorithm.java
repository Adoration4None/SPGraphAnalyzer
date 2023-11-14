package co.edu.uniquindio.proyectoanalisis.utils;

import co.edu.uniquindio.proyectoanalisis.logic.*;

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
            FloydWarshallAlgorithm algorithm = new FloydWarshallAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.floydWarshall(graph);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 4) {
            JohnsonAlgorithm algorithm = new JohnsonAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.johnson(graph);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 5) {
            DirectedAcyclicAlgorithm algorithm = new DirectedAcyclicAlgorithm(graph, graph.length);

            initTime = System.nanoTime();
            algorithm.shortestPath(0);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 6) {
            DialAlgorithm algorithm = new DialAlgorithm();

            initTime = System.nanoTime();
            algorithm.shortestPath(graph, 0, 999);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 7) {
            MultistageGraphAlgorithm algorithm = new MultistageGraphAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.shortestDist(graph);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 8) {
            UnweightedGraphSPAlgorithm algorithm = new UnweightedGraphSPAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.run(graph, 0);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 9) {
            KarpAlgorithm algorithm = new KarpAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.minAvgWeight(graph);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 10) {
            ZeroOneBFSAlgorithm algorithm = new ZeroOneBFSAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.run(graph);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 11) {
            MinimumWeightCycleAlgorithm algorithm = new MinimumWeightCycleAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.run(graph);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }
        if(id == 12) {
            DijkstraPriorityQueueAlgorithm algorithm = new DijkstraPriorityQueueAlgorithm(graph.length);

            initTime = System.nanoTime();
            algorithm.dijkstra(graph, 0);
            endTime = System.nanoTime();

            return (endTime - initTime) / 1000000;
        }

        return 0;
    }
}
