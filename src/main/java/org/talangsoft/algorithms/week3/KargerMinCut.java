package org.talangsoft.algorithms.week3;

import java.util.*;

class Graph<T> {

    private Random rnd = new Random();

    class Edge<T> {
        private T a, b;

        public Edge(T a, T b) {
            this.a = a;
            this.b = b;
        }

    }

    private Map<T, List<T>> verticesWithAdjacents;

    public Map<T, List<T>> getVerticesWithAdjacents() {
        return verticesWithAdjacents;
    }

    public static <T> Graph from(Map<T, List<T>> graphData) {
        return new Graph(graphData);
    }

    public Graph(Map<T, List<T>> graphData) {
        this.verticesWithAdjacents = new HashMap<>();
        for (T key : graphData.keySet()) {
            List<T> connectedEdges = new LinkedList<T>();
            connectedEdges.addAll(graphData.get(key));
            this.verticesWithAdjacents.put(key, connectedEdges);
        }
    }

    public List<T> getAdjacents(T verticle) {
        return verticesWithAdjacents.get(verticle);
    }

    public void setAdjacents(T verticle, List<T> newAdjacents) {
        verticesWithAdjacents.put(verticle, newAdjacents);
    }

    public void removeVertex(T verticle) {
        verticesWithAdjacents.remove(verticle);
    }

    public Set<T> getVerticles() {
        return verticesWithAdjacents.keySet();
    }

    public T[] getVerticlesArr() {
        return (T[]) getVerticles().toArray();
    }

    public Edge<T> getRandomEdge() {
        T[] vertices = getVerticlesArr();
        T vertex1 = vertices[rnd.nextInt(vertices.length)];
        List<T> adjacents = getAdjacents(vertex1);
        T vertex2 = adjacents.get(rnd.nextInt(adjacents.size()));
        return new Edge<T>(vertex1, vertex2);
    }


    public void mergeEdge(Edge<T> edge) {
        List<T> adjecentsOfA = verticesWithAdjacents.remove(edge.a);
        List<T> adjacentsOfB = verticesWithAdjacents.remove(edge.b);
        // remove A from adjacents of B
        adjacentsOfB.removeIf(vertex -> vertex.equals(edge.a));
        // Connect all node from adjacents of B to A instead of B
        adjacentsOfB.stream().forEach(vertex -> {
            replaceAdjacentWithAwithB(vertex, edge.a, edge.b);
        });
        // remove B from adjacents of A
        adjecentsOfA.removeIf(vertex -> vertex.equals(edge.b));
        // add all adjacents of B to A
        adjecentsOfA.addAll(adjacentsOfB);
        verticesWithAdjacents.put(edge.a, adjecentsOfA);
    }

    private void replaceAdjacentWithAwithB(T vertex, T a, T b) {
        List<T> adjacencyList = verticesWithAdjacents.get(vertex);
        int initialSize = adjacencyList.size();
        adjacencyList.removeIf(value -> value.equals(b));
        int toAdd = initialSize - adjacencyList.size();
        for (int i = 0; i < toAdd; i++) {
            adjacencyList.add(a);
        }
    }
}

public class KargerMinCut<T> {
    public int cut(Graph<T> graph) {
        while (graph.getVerticles().size() != 2) {
            Graph.Edge randomEdge = graph.getRandomEdge();
            graph.mergeEdge(randomEdge);
        }
        return graph.getAdjacents(graph.getVerticlesArr()[0]).size();
    }
}
