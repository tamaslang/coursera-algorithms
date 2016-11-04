package org.talangsoft.algorithms.week5;

import org.junit.Test;
import org.talangsoft.algorithms.helper.InputFileReader;

import java.util.Arrays;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class WeightedGraphTest {
    private String weightedGraphSourceFile = "week5/example_weighted_graph_format.txt";
    private String assignmentGraphSourceFile = "week5/dijkstra_data.txt";
    private String lectureGraphSourceFile = "week5/week5_lecture_graph.txt";
    private String testcase1GraphSourceFile = "week5/forum_testcase1.txt";
    private String testcase2GraphSourceFile = "week5/forum_testcase2.txt";
    private String testcase3GraphSourceFile = "week5/forum_testcase3.txt";
    private String testcase4GraphSourceFile = "week5/forum_testcase4.txt";

    @Test
    public void readSourceFileWithExampleGraph() throws Exception {
        Map<Integer, int[][]> graphData = InputFileReader.readDirectedWeightedGraph(weightedGraphSourceFile);
        WeightedDirectedGraph graph = new WeightedDirectedGraph(graphData);

        assertThat(graph.getVertices().length, is(5));

        assertThat(graph.getAdjacents(1), is(new int[][]{{3, 9}}));
        assertThat(graph.getAdjacents(2), is(new int[][]{{1, 3}, {5, 4}}));
        assertThat(graph.getAdjacents(3), is(new int[][]{{2, 4}, {5, 11}}));
        assertThat(graph.getAdjacents(4), is(new int[][]{{2, 7}}));
        assertThat(graph.getAdjacents(5), is(new int[][]{{4, 8}}));
    }

    @Test
    public void readSourceFileWithAssignmentGraph() throws Exception {
        Map<Integer, int[][]> graphData = InputFileReader.readDirectedWeightedGraph(assignmentGraphSourceFile);

        WeightedDirectedGraph graph = new WeightedDirectedGraph(graphData);

        assertThat(graph.getVertices().length, is(200));

        assertThat(graph.getAdjacents(1), is(new int[][]{
                {80, 982}, {163, 8164}, {170, 2620}, {145, 648},
                {200, 8021}, {173, 2069}, {92, 647}, {26, 4122},
                {140, 546}, {11, 1913}, {160, 6461}, {27, 7905},
                {40, 9047}, {150, 2183}, {61, 9146}, {159, 7420},
                {198, 1724}, {114, 508}, {104, 6647}, {30, 4612},
                {99, 2367}, {138, 7896}, {169, 8700}, {49, 2437},
                {125, 2909}, {117, 2597}, {55, 6399}
        }));

        // find shortest paths from edge '1'
        Map<Integer, Integer> shortestPaths = graph.shortestPathsWithDijkstra(1);
        assertThat(shortestPaths.get(1), is(0));
        assertThat(shortestPaths.get(200), is(2060));

    }


    @Test
    public void solveWeek5AssignmentByCalculatingSomeDistances() throws Exception{
        Map<Integer, int[][]> graphData = InputFileReader.readDirectedWeightedGraph(assignmentGraphSourceFile);

        WeightedDirectedGraph graph = new WeightedDirectedGraph(graphData);
        Map<Integer, Integer> shortestPaths = graph.shortestPathsWithDijkstra(1);
        int[] vertices = new int[]{7,37,59,82,99,115,133,165,188,197};
        String[] distancesForVertices = Arrays.stream(vertices).mapToObj(vertex -> shortestPaths.get(vertex).toString()).toArray(String[]::new);
        assertThat(String.join(",",distancesForVertices), is("2599,2610,2947,2052,2367,2399,2029,2442,2505,3068"));


    }

    @Test
    public void testcase1Graph() throws Exception{
        Map<Integer, int[][]> graphData = InputFileReader.readDirectedWeightedGraph(testcase1GraphSourceFile);
        WeightedDirectedGraph graph = new WeightedDirectedGraph(graphData);

        Map<Integer, Integer> shortestPathFromVertex1 = graph.shortestPathsWithDijkstra(1);
        assertThat(shortestPathFromVertex1.get(1), is(0));
        assertThat(shortestPathFromVertex1.get(2), is(1));
        assertThat(shortestPathFromVertex1.get(3), is(2));
        assertThat(shortestPathFromVertex1.get(4), is(3));
        assertThat(shortestPathFromVertex1.get(5), is(4));
        assertThat(shortestPathFromVertex1.get(6), is(4));
        assertThat(shortestPathFromVertex1.get(7), is(3));
        assertThat(shortestPathFromVertex1.get(8), is(2));

        /*
        1 0 []
        2 1 [2]
        3 2 [2, 3]
        4 3 [2, 3, 4]
        5 4 [2, 3, 4, 5]
        6 4 [8, 7, 6]
        7 3 [8, 7]
        8 2 [8]
        */
    }

    @Test
    public void testcase2Graph() throws Exception{
        Map<Integer, int[][]> graphData = InputFileReader.readDirectedWeightedGraph(testcase2GraphSourceFile);
        WeightedDirectedGraph graph = new WeightedDirectedGraph(graphData);

        Map<Integer, Integer> shortestPathFromVertex1 = graph.shortestPathsWithDijkstra(1);
        assertThat(shortestPathFromVertex1.get(1), is(0));
        assertThat(shortestPathFromVertex1.get(2), is(6));
        assertThat(shortestPathFromVertex1.get(3), is(2));
        assertThat(shortestPathFromVertex1.get(4), is(4));
        assertThat(shortestPathFromVertex1.get(5), is(3));
        assertThat(shortestPathFromVertex1.get(6), is(10));
        assertThat(shortestPathFromVertex1.get(7), is(11));
        assertThat(shortestPathFromVertex1.get(8), is(9));
        assertThat(shortestPathFromVertex1.get(9), is(12));

        /*
            1:0,
            2:6,
            3:2,
            4:4,
            5:3,
            6:10,
            7:11,
            8:9,
            9:12,
         */
    }

    @Test
    public void testcase3graph() throws Exception{
        Map<Integer, int[][]> graphData = InputFileReader.readDirectedWeightedGraph(testcase3GraphSourceFile);
        WeightedDirectedGraph graph = new WeightedDirectedGraph(graphData);

        Map<Integer, Integer> shortestPathFromVertex1 = graph.shortestPathsWithDijkstra(1);
        assertThat(shortestPathFromVertex1.get(1), is(0));
        assertThat(shortestPathFromVertex1.get(2), is(3));
        assertThat(shortestPathFromVertex1.get(3), is(2));
        assertThat(shortestPathFromVertex1.get(4), is(6));
        assertThat(shortestPathFromVertex1.get(5), is(9));
        assertThat(shortestPathFromVertex1.get(6), is(4));
        assertThat(shortestPathFromVertex1.get(7), is(4));
        assertThat(shortestPathFromVertex1.get(8), is(6));
        assertThat(shortestPathFromVertex1.get(9), is(8));
        assertThat(shortestPathFromVertex1.get(10), is(4));
    }

    @Test
    public void testcase4graph() throws Exception{
        Map<Integer, int[][]> graphData = InputFileReader.readDirectedWeightedGraph(testcase4GraphSourceFile);
        WeightedDirectedGraph graph = new WeightedDirectedGraph(graphData);

        Map<Integer, Integer> shortestPathFromVertex1 = graph.shortestPathsWithDijkstra(1);
        assertThat(shortestPathFromVertex1.get(1), is(0));
        assertThat(shortestPathFromVertex1.get(2), is(1));
        assertThat(shortestPathFromVertex1.get(3), is(3));
        assertThat(shortestPathFromVertex1.get(4), is(2));
        assertThat(shortestPathFromVertex1.get(5), is(2147483647)); // no path
    }

    @Test
    public void testWeek5ExampleGraph() throws Exception {
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
        Map<Integer, int[][]> graphData = InputFileReader.readDirectedWeightedGraph(lectureGraphSourceFile);
        WeightedDirectedGraph graph = new WeightedDirectedGraph(graphData);

        assertThat(graph.getVertices().length, is(4));

        assertThat(graph.getAdjacents(1), is(new int[][]{{2, 1}, {3, 4}}));
        assertThat(graph.getAdjacents(2), is(new int[][]{{3, 2}, {4, 6}}));
        assertThat(graph.getAdjacents(3), is(new int[][]{{4, 3}}));
        assertThat(graph.getAdjacents(4), is(new int[][]{}));

        // calculate shortest path
        Map<Integer, Integer> shortestPathFromVertex1 = graph.shortestPathsWithDijkstra(1);
        assertThat(shortestPathFromVertex1.get(1), is(0));
        assertThat(shortestPathFromVertex1.get(2), is(1));
        assertThat(shortestPathFromVertex1.get(3), is(3));
        assertThat(shortestPathFromVertex1.get(4), is(6));
    }
}