package org.talangsoft.algorithms.week3;

import org.junit.Test;
import org.talangsoft.algorithms.helper.InputFileReader;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        Map<Integer, Integer[]> week3GraphData = InputFileReader.readGraphElements(week3GraphSourceFile);

        long before = System.currentTimeMillis();
        for (int i = 0; i < nrOfIterations; i++) {
            Graph week3Graph = new Graph(week3GraphData);
            Integer currentCut =  kargerMinCut.cut(week3Graph);
            if (currentCut < minCut) {
                minCut = currentCut;
                System.out.println(String.format("In iteration %d mincut was improved to -> %d ", i+1, minCut));
            }
        }
        long after = System.currentTimeMillis();

        System.out.println(String.format("Execution for number %d took time %dms",nrOfIterations,(after - before)));

        assertThat(minCut, is(17));
    }
    // Execution for number 500 took time 7746ms
    // Execution for number 500 took time 9186ms
    // Execution for number 500 took time 7952ms
    // Execution for number 1000 took time 14438ms
    // with profiler
    // Execution for number 100 took time 17760ms
    // Execution for number 100 took time 17024ms
    // with detailed profiler
    // Execution for number 100 took time 36476ms
    // Execution for number 100 took time 36592ms
    //strArrToIntListWithoutHead


    @Test
    public void parseGraphSourceFileTest() throws Exception {
        Map<Integer, Integer[]> graph = InputFileReader.readGraphElements(graphSourceFile);
        assertThat(graph.size(), is(2));
        assertThat(graph.get(1), is(new Integer[]{37, 79, 164}));
        assertThat(graph.get(2), is(new Integer[]{123, 134, 10, 141}));
    }
}