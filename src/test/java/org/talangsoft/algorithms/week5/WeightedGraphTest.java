package org.talangsoft.algorithms.week5;

import org.junit.Test;
import org.talangsoft.algorithms.helper.InputFileReader;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WeightedGraphTest {
    private String weightedGraphSourceFile = "week5/example_weighted_graph_format.txt";
    private String assignmentGraphSourceFile = "week5/dijkstra_data.txt";

    @Test
    public void readSourceFileWithExampleGraph() throws Exception {
        Map<Integer, int[][]> graph = InputFileReader.readDirectedWeightedGraph(weightedGraphSourceFile);

        assertThat(graph.get(1), is(new int[][]{{3, 9}}));
        assertThat(graph.get(2), is(new int[][]{{1, 3}, {5, 4}}));
        assertThat(graph.get(3), is(new int[][]{{2, 4}, {5, 11}}));
        assertThat(graph.get(4), is(new int[][]{{2, 7}}));
        assertThat(graph.get(5), is(new int[][]{{4, 8}}));
    }

    @Test
    public void readSourceFileWithAssignmentGraph() throws Exception {
        Map<Integer, int[][]> graph = InputFileReader.readDirectedWeightedGraph(assignmentGraphSourceFile);

        assertThat(graph.get(1), is(new int[][]{
                {80, 982}, {163, 8164}, {170, 2620}, {145, 648},
                {200, 8021}, {173, 2069}, {92, 647}, {26, 4122},
                {140, 546}, {11, 1913}, {160, 6461}, {27, 7905},
                {40, 9047}, {150, 2183}, {61, 9146}, {159, 7420},
                {198, 1724}, {114, 508}, {104, 6647}, {30, 4612},
                {99, 2367}, {138, 7896}, {169, 8700}, {49, 2437},
                {125, 2909}, {117, 2597}, {55, 6399}
        }));
    }
}


/*
1 3,9
2 1,3 5,4
3 2,4 5,11
4 2,7
5 4,8
 */