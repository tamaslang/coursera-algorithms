package org.talangsoft.algorithms.week4;

import org.junit.Test;
import org.talangsoft.algorithms.helper.InputFileReader;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GraphTest {
    private String graphSourceFile = "week4/week4graph.txt";


    @Test
    public void readWeek4Graph() throws Exception{
        long before = System.currentTimeMillis();
        Map<Integer, int[]> week4GraphData = InputFileReader.readGraphElementsFormatOfAtoBEdges(graphSourceFile);

        long after = System.currentTimeMillis();
        System.out.println(String.format("Reading input file '%s' with vertices %d took %dms", graphSourceFile,week4GraphData.size(), (after - before)));
        assertThat(week4GraphData.size(),is(739454));
        assertThat(week4GraphData.get(875713),is(new int[]{233422, 233423, 233424, 233425, 233426, 233427, 233428, 233429, 233430, 233431, 233432, 233433, 233434, 233435, 233436}));
    }
}
