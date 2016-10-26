package org.talangsoft.algorithms.week3;

import org.junit.Test;
import org.talangsoft.algorithms.helper.InputFileReader;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class KargerMinCutTest {

    private String graphSourceFile = "week3/parse_graph_test.txt";

    private String week3GraphSourceFile = "week3/karger_mincut.txt";

    private KargerMinCut kargerMinCut = new KargerMinCut();

    @Test
    public void solveTheProblemOfWeek3With1IterationTest() throws Exception {
        int nrOfIterations = 100;
        Integer minCut = Integer.MAX_VALUE;
        Map<Integer, List<Integer>> week3GraphData = InputFileReader.readGraphElements(week3GraphSourceFile);

        for (int i = 0; i < nrOfIterations; i++) {
            Graph week3Graph = new Graph(week3GraphData);
            Integer currentCut =  kargerMinCut.cut(week3Graph);
            if (currentCut < minCut) {
                minCut = currentCut;
                System.out.println(String.format("In iteration %d mincut was improved to -> %d ", i+1, minCut));
            }
        }
        assertThat(minCut, is(17));
    }

    @Test
    public void parseGraphSourceFileTest() throws Exception {
        Map<Integer, List<Integer>> graph = InputFileReader.readGraphElements(graphSourceFile);
        assertThat(graph.size(), is(2));
        assertThat(graph.get(1), is(Arrays.asList(new Integer[]{37, 79, 164})));
        assertThat(graph.get(2), is(Arrays.asList(new Integer[]{123, 134, 10, 141})));
    }
}