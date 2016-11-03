package org.talangsoft.algorithms.week3;

import org.junit.Test;
import org.talangsoft.algorithms.helper.InputFileReader;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class KargerMinCutTest {

    private String graphSourceFile = "week3/parse_graph_test.txt";

    private String recursiveGraphSourceFile = "week3/recursive_graph.txt";

    private String week3GraphSourceFile = "week3/karger_mincut.txt";

    private KargerMinCut kargerMinCut = new KargerMinCut();

    @Test
    public void solveTheProblemOfWeek3With1IterationTest() throws Exception {
        int nrOfIterations = 100;
        Integer minCut = Integer.MAX_VALUE;
        Map<Integer, int[]> week3GraphData = InputFileReader.readGraphOfNodesAndAdjacentsList(week3GraphSourceFile);

        long before = System.currentTimeMillis();
        for (int i = 0; i < nrOfIterations; i++) {
            Graph week3Graph = new Graph(week3GraphData);
            Integer currentCut = kargerMinCut.cut(week3Graph);
            if (currentCut < minCut) {
                minCut = currentCut;
                System.out.println(String.format("In iteration %d mincut was improved to -> %d ", i + 1, minCut));
            }
        }
        long after = System.currentTimeMillis();

        System.out.println(String.format("Execution for number %d took %dms", nrOfIterations, (after - before)));

        assertThat(minCut, is(17));
    }
    // Execution before optimisation (with using Lists and Boxed primitives) for 1000 iterations took time 14438ms
    // Execution after optimisation (with using arrays and primitives) for 1000 iterations took time 8791ms


    @Test
    public void cutRecursiveGraphTest() throws Exception {
        Map<Integer, int[]> recursiveGraphData = InputFileReader.readGraphOfNodesAndAdjacentsList(recursiveGraphSourceFile);
        int nrOfIterations = 100;
        Graph.from(recursiveGraphData).mergeEdge(3,1);
        for (int i = 0; i < nrOfIterations; i++) {
            kargerMinCut.cut(Graph.from(recursiveGraphData));
        }

    }

    @Test
    public void parseGraphSourceFileTest() throws Exception {
        Map<Integer, int[]> graph = InputFileReader.readGraphOfNodesAndAdjacentsList(graphSourceFile);
        assertThat(graph.size(), is(2));
        assertThat(graph.get(1), is(new int[]{37, 79, 164}));
        assertThat(graph.get(2), is(new int[]{123, 134, 10, 141}));
    }
}