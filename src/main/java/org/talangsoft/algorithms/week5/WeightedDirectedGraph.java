package org.talangsoft.algorithms.week5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WeightedDirectedGraph {

    private Map<Integer, int[][]> graphData;

    public WeightedDirectedGraph(Map<Integer, int[][]> source) {
        this.graphData = new HashMap<>(source.size());
        for (Integer key : source.keySet()) {
            int[][] adjacentsWithWeights = source.get(key);
            int[][] copyOfAdjacentsWithWeights = new int[source.get(key).length][];
            System.arraycopy(adjacentsWithWeights, 0, copyOfAdjacentsWithWeights, 0, copyOfAdjacentsWithWeights.length);
            this.graphData.put(key, copyOfAdjacentsWithWeights);
            // create empty nodes too
            for (int[] adjacentWithWeight : copyOfAdjacentsWithWeights) {
                if (!this.graphData.containsKey(adjacentWithWeight[0])) {
                    this.graphData.put(adjacentWithWeight[0], new int[][]{});
                }
            }
        }
    }

    public int[][] getAdjacents(Integer vertex) {
        return graphData.get(vertex);
    }

    public Integer[] getVertices() {
        return graphData.keySet().stream().toArray(Integer[]::new);
    }

    private Map.Entry<Integer, Integer> removeAndReturnMinimumVertex(Map<Integer, Integer> vertexToValue) {
        Map.Entry<Integer, Integer> vertexWithMin = null;
        for (Map.Entry<Integer, Integer> currentVertex : vertexToValue.entrySet()) {
            if (vertexWithMin == null || currentVertex.getValue() < vertexWithMin.getValue()) {
                vertexWithMin = currentVertex;
            }
        }
        vertexToValue.remove(vertexWithMin.getKey());
        return vertexWithMin;
    }

    public Map<Integer, Integer> shortestPathsWithDijkstra(Integer s) {
        /**
         *
         *          ---@2 ---
         *     (1)/     |     \ (6)
         *      /       |      @
         *   1(s)       |(2)    4
         *      \       |      @
         *    (4)\      @     / (3)
         *        \____@3___/
         *
         */

        // create Q -> unvisited nodes with endless distances except starting point
        Map<Integer, Integer> unvisitedQ = Arrays.stream(getVertices()).collect(Collectors.toMap(vertex -> vertex, vertex -> Integer.MAX_VALUE));
        // mark starting vertex s to 0 distance
        unvisitedQ.put(s, 0);
        // create V -> storage for visited nodes with distances: the solution, eventually
        Map<Integer, Integer> visitedV = new HashMap<>(getVertices().length);
        while (!unvisitedQ.isEmpty()) {
            // find min in unvisited Q
            Map.Entry<Integer, Integer> minimumVertex = removeAndReturnMinimumVertex(unvisitedQ);
            visitedV.put(minimumVertex.getKey(), minimumVertex.getValue());

            // find all neighbours that are in  Q
            int[][] neighboursWithDistances = getAdjacents(minimumVertex.getKey());
            // calculate distances to neighbours
            for (int[] neighbourAndDistance : neighboursWithDistances) {
                if (vertexIsUnvisitedWithSmallerValue(unvisitedQ, neighbourAndDistance, minimumVertex.getValue())) {
                    unvisitedQ.put(neighbourAndDistance[0], minimumVertex.getValue() + neighbourAndDistance[1]);
                }
            }
        }

        return visitedV;
    }

    private boolean vertexIsUnvisitedWithSmallerValue(Map<Integer, Integer> unvisitedQ, int[] vertexWithDistance, int currentMinVertexValue) {
        return unvisitedQ.containsKey(vertexWithDistance[0]) &&
                unvisitedQ.get(vertexWithDistance[0]) > currentMinVertexValue + vertexWithDistance[1];
    }

}
