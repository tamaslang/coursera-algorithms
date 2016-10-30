package org.talangsoft.algorithms.week4;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

class DirectedGraph {

    private Map<Integer, int[]> graphData;

    public DirectedGraph(Map<Integer, int[]> source) {
        this.graphData = new HashMap<>(source.size());
        for (Integer key : source.keySet()) {
            int[] adjacents = source.get(key);
            int[] copyOfAdjacents = new int[source.get(key).length];
            System.arraycopy(adjacents, 0, copyOfAdjacents, 0, copyOfAdjacents.length);
            this.graphData.put(key, copyOfAdjacents);
            // create empty nodes too
            for(Integer adjacent : copyOfAdjacents){
                if(!this.graphData.containsKey(adjacent)){
                    this.graphData.put(adjacent,new int[]{});
                }
            }
        }
    }

    public DirectedGraph reverseGraph() {
        Map<Integer, List<Integer>> reversedGraphWithList = new HashMap<>(graphData.size());
        for (Integer v1 : graphData.keySet()) {
            int[] v1Adjacents = graphData.get(v1);
            for (int v1Adjacent : v1Adjacents) {
                if (reversedGraphWithList.get(v1Adjacent) == null)
                    reversedGraphWithList.put(v1Adjacent, new LinkedList<>());
                reversedGraphWithList.get(v1Adjacent).add(v1);
            }
        }
        Iterator<Map.Entry<Integer, List<Integer>>> entryIterator = reversedGraphWithList.entrySet().iterator();
        Map<Integer, int[]> reversedGraphArr = new HashMap<>(reversedGraphWithList.size());
        while (entryIterator.hasNext()) {
            Map.Entry<Integer, List<Integer>> entry = entryIterator.next();
            reversedGraphArr.put(entry.getKey(), entry.getValue().stream().mapToInt(Integer::intValue).toArray());
        }
        return new DirectedGraph(reversedGraphArr);
    }

    private void dfsForFinishingTimes(Integer s, Set<Integer> visited, List<Integer> finishingTimes) {
        Stack<Integer> nodesToVisit = new Stack<>();
        nodesToVisit.add(s);
        while (!nodesToVisit.isEmpty()) {
            Integer currentNode = nodesToVisit.peek();
            int[] neighbours = graphData.get(currentNode);
            if (neighbours == null) continue;
            boolean hadUnvisitedNeighbours = false;
            for (Integer n : neighbours) {
                if (!visited.contains(n)) {
                    nodesToVisit.push(n);
                    visited.add(n);
                    hadUnvisitedNeighbours = true;
                }
            }
            if(!hadUnvisitedNeighbours) {
                nodesToVisit.pop();
                finishingTimes.add(currentNode);
            }

        }
    }

    private void dfs(Integer s, Set<Integer> visited) {
        Queue<Integer> nodesToVisit = new LinkedBlockingDeque<>();
        nodesToVisit.add(s);
        while (!nodesToVisit.isEmpty()) {
            Integer currentNode = nodesToVisit.poll();
            int[] neighbours = graphData.get(currentNode);
            if (neighbours == null) continue;
            for (Integer n : neighbours) {
                if (!visited.contains(n)) {
                    nodesToVisit.add(n);
                    visited.add(n);
                }
            }
        }
    }

    public List<Integer> finishingTimes() {
        List<Integer> finishingTimes = new ArrayList<>();
        Set<Integer> visited = new HashSet<Integer>(graphData.size());
        Integer[] keys = graphData.keySet().stream().toArray(Integer[]::new);
        // reverse loop keys
        for (int i = keys.length - 1; i > 0; i--) {
            Integer s = keys[i];
            if (!visited.contains(s)) {
                visited.add(s);
                dfsForFinishingTimes(s, visited, finishingTimes);
            }

        }
        return finishingTimes;
    }


    public List<Integer> strongComponentSizes(List<Integer> finishingTimes) {
        List<Integer> strongComponentSizes = new ArrayList<>();
        Set<Integer> visited = new HashSet<>(graphData.size());
        // reverse loop keys
        for (int i = finishingTimes.size() - 1; i > 0; i--) {
            Integer s = finishingTimes.get(i);
            if (!visited.contains(s)) {
                Integer visitedCountBeforeDfs = visited.size();
                visited.add(s);
                dfs(s, visited);
                strongComponentSizes.add(visited.size() - visitedCountBeforeDfs);
            }

        }
        return strongComponentSizes;
    }
}

public class StrongComponents {

    public List<Integer> strongComponent(Map<Integer, int[]> graphData) {
        System.out.println("1 - Create graph...");
        DirectedGraph graph = new DirectedGraph(graphData);
        System.out.println("-> Created");
        System.out.println("2 - Reverse graph");
        DirectedGraph reversedGraph = graph.reverseGraph();
        System.out.println("-> Reversed");
        System.out.println("Calculate finishing times in reversed...");
        List<Integer> finishingTimes = reversedGraph.finishingTimes();
        System.out.println("-> Calculated");
        System.out.println("Calculate strong components");
        List<Integer> strongComponentSizes = graph.strongComponentSizes(finishingTimes);
        System.out.println("-> Calculated");
        return strongComponentSizes;

    }
}
