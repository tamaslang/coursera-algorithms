package org.talangsoft.algorithms.week3;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Graph {

    private Random rnd = new Random();

    private Map<Integer, int[]> verticesWithAdjacents;

    public static Graph from(Map<Integer, int[]> graphData) {
        return new Graph(graphData);
    }

    public Graph(Map<Integer, int[]> graphData) {
        this.verticesWithAdjacents = new HashMap<>(graphData.size());
        for (Integer key : graphData.keySet()) {
            int[] adjacents = graphData.get(key);
            int[] copyOfAdjacents = new int[graphData.get(key).length];
            System.arraycopy(adjacents, 0, copyOfAdjacents, 0, copyOfAdjacents.length);
            this.verticesWithAdjacents.put(key, copyOfAdjacents);
        }
    }

    public int[] getAdjacents(Integer verticle) {
        return verticesWithAdjacents.get(verticle);
    }

    public Set<Integer> getVerticles() {
        return verticesWithAdjacents.keySet();
    }

    public int[] getVerticlesArr() {
        return getVerticles().stream().mapToInt(s -> s.intValue()).toArray();
    }

    public int[] getRandomEdge() {
        int[] vertices = getVerticlesArr();
        int vertex1 = vertices[rnd.nextInt(vertices.length)];
        int[] adjacents = getAdjacents(vertex1);
        int vertex2 = 0;
        do {
            vertex2 = adjacents[rnd.nextInt(adjacents.length)];
        } while (vertex1 == vertex2);
        return new int[]{vertex1, vertex2};
    }

    private int[] removeElementFrom(int[] source, int elementToRemove) {
        int countOfValidElement = 0;
        int[] arrWithElementRemoved = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            if (source[i] != elementToRemove) arrWithElementRemoved[countOfValidElement++] = source[i];
        }
        int[] retVal = new int[countOfValidElement];
        System.arraycopy(arrWithElementRemoved, 0, retVal, 0, countOfValidElement);
        return retVal;
    }

    private int[] replaceElementFrom(int[] source, int elementToReplace, int newElement) {
        int[] retVal = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            retVal[i] = source[i] == elementToReplace ? newElement : source[i];
        }
        return retVal;
    }

    public void mergeEdge(int a, int b) {
        // System.out.println(String.format("Merge %d with %d", a, b));
        int[] adjecentsOfA = verticesWithAdjacents.remove(a);
        int[] adjacentsOfB = verticesWithAdjacents.remove(b);
        // remove A from adjacents of B
        adjacentsOfB = removeElementFrom(adjacentsOfB, a);
        // remove self loop
        adjacentsOfB = removeElementFrom(adjacentsOfB, b);
        // Connect all node from adjacents of B to A instead of B
        for (int i = 0; i < adjacentsOfB.length; i++) {
            int vertex = adjacentsOfB[i];
            int[] adjecentsOfVertexWithBInsteadOfA = replaceElementFrom(verticesWithAdjacents.remove(vertex), b, a);
            verticesWithAdjacents.put(vertex, adjecentsOfVertexWithBInsteadOfA);
        }
        // remove B from adjacents of A
        adjecentsOfA = removeElementFrom(adjecentsOfA, b);
        adjecentsOfA = removeElementFrom(adjecentsOfA, a);
        // add all adjacents of B to A
        int[] combinedAdjacentsOfA = new int[adjacentsOfB.length + adjecentsOfA.length];
        System.arraycopy(adjecentsOfA, 0, combinedAdjacentsOfA, 0, adjecentsOfA.length);
        System.arraycopy(adjacentsOfB, 0, combinedAdjacentsOfA, adjecentsOfA.length, adjacentsOfB.length);
        verticesWithAdjacents.put(a, combinedAdjacentsOfA);
    }
}
