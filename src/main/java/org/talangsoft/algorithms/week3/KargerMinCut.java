package org.talangsoft.algorithms.week3;


public class KargerMinCut {
    public int cut(Graph graph) {
        while (graph.getVerticles().size() != 2) {
            int[] randomEdge = graph.getRandomEdge();
            graph.mergeEdge(randomEdge[0], randomEdge[1]);
        }
        return graph.getAdjacents(graph.getVerticlesArr()[0]).length;
    }
}
