package org.talangsoft.algorithms.week3;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

class Graph {

    private Random rnd = new Random();

    private Map<Integer, Integer[]> verticesWithAdjacents;

    public Map<Integer, Integer[]> getVerticesWithAdjacents() {
        return verticesWithAdjacents;
    }

    public static Graph from(Map<Integer, Integer[]> graphData) {
        return new Graph(graphData);
    }

    public Graph(Map<Integer, Integer[]> graphData) {
        this.verticesWithAdjacents = new HashMap<>();
        for (Integer key : graphData.keySet()) {
            Integer[] adjacents = graphData.get(key);
            Integer[] copyOfAdjacents = new Integer[graphData.get(key).length];
            System.arraycopy(adjacents, 0, copyOfAdjacents, 0, copyOfAdjacents.length);
            this.verticesWithAdjacents.put(key, copyOfAdjacents);
        }
    }

    public Integer[] getAdjacents(Integer verticle) {
        return verticesWithAdjacents.get(verticle);
    }

    public Set<Integer> getVerticles() {
        return verticesWithAdjacents.keySet();
    }

    public Integer[] getVerticlesArr() {
        return getVerticles().toArray(new Integer[getVerticles().size()]);
    }

    public Integer[] getRandomEdge() {
        Integer[] vertices = getVerticlesArr();
        Integer vertex1 = vertices[rnd.nextInt(vertices.length)];
        Integer[] adjacents = getAdjacents(vertex1);
        Integer vertex2 = adjacents[rnd.nextInt(adjacents.length)];
        return new Integer[]{vertex1, vertex2};
    }

    private Integer[] removeElementFrom(Integer[] source, Integer elementToRemove) {
        int countOfElement = 0;
        for (int i = 0; i < source.length; i++) {
            if (source[i].equals(elementToRemove)) countOfElement++;
        }
        Integer[] retVal = new Integer[source.length - countOfElement];
        int newIndex = 0;
        for (int i = 0; i < source.length; i++) {
            if (!source[i].equals(elementToRemove)) retVal[newIndex++] = source[i];
        }
        return retVal;
    }

    private Integer[] replaceElementFrom(Integer[] source, Integer elementToReplace, Integer newElement) {
        Integer[] retVal = new Integer[source.length];
        for (int i = 0; i < source.length; i++) {
            retVal[i] = source[i].equals(elementToReplace) ? newElement : source[i];
        }
        return retVal;
    }

    public void mergeEdge(Integer a, Integer b) {
        Integer[] adjecentsOfA = verticesWithAdjacents.remove(a);
        Integer[] adjacentsOfB = verticesWithAdjacents.remove(b);
        // remove A from adjacents of B
        // adjacentsOfB.removeIf(vertex -> vertex.equals(edge.a));
        Integer[] adjacentsOfBWithoutA = removeElementFrom(adjacentsOfB, a);
        // Connect all node from adjacents of B to A instead of B
        for (int i = 0; i < adjacentsOfBWithoutA.length; i++) {
            Integer vertex = adjacentsOfBWithoutA[i];
            Integer[] adjecentsOfVertexWithBInsteadOfA = replaceElementFrom(verticesWithAdjacents.remove(vertex), b, a);
            verticesWithAdjacents.put(vertex, adjecentsOfVertexWithBInsteadOfA);
        }
        // remove B from adjacents of A
        Integer[] adjacentsOfAWithoutB = removeElementFrom(adjecentsOfA, b);
        // add all adjacents of B to A
        Integer[] combinedAdjacentsOfA = new Integer[adjacentsOfBWithoutA.length + adjacentsOfAWithoutB.length];
        System.arraycopy(adjacentsOfAWithoutB, 0, combinedAdjacentsOfA, 0, adjacentsOfAWithoutB.length);
        System.arraycopy(adjacentsOfBWithoutA, 0, combinedAdjacentsOfA, adjacentsOfAWithoutB.length, adjacentsOfBWithoutA.length);
        verticesWithAdjacents.put(a, combinedAdjacentsOfA);
    }
}

public class KargerMinCut {
    public int cut(Graph graph) {
        while (graph.getVerticles().size() != 2) {
            Integer[] randomEdge = graph.getRandomEdge();
            graph.mergeEdge(randomEdge[0], randomEdge[1]);
        }
        return graph.getAdjacents(graph.getVerticlesArr()[0]).length;
    }
}
