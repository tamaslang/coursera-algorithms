package org.talangsoft.algorithms.week3;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GraphTest {




    public Graph getGraph1(){
        /**
         * graph 1
         *
         *    1
         *  /  \
         * /    \
         * 2 -- 3
         * | \  |
         * |  \ |
         * |   \|
         * 4----5
         */
        Map<Integer, List<Integer>> graphVerticlesAndAdjacents = new HashMap<>();
        graphVerticlesAndAdjacents.put(1, Arrays.asList(new Integer[]{2, 3}));
        graphVerticlesAndAdjacents.put(2, Arrays.asList(new Integer[]{1, 3, 4, 5}));
        graphVerticlesAndAdjacents.put(3, Arrays.asList(new Integer[]{1, 2, 5}));
        graphVerticlesAndAdjacents.put(4, Arrays.asList(new Integer[]{2, 5}));
        graphVerticlesAndAdjacents.put(5, Arrays.asList(new Integer[]{2, 3, 4}));
        return Graph.from(graphVerticlesAndAdjacents);
    }


    @Test
    public void testMerge() {
        Graph<Integer> graph =getGraph1();
        Graph.Edge mergeThis = graph.new Edge<Integer>(2,3);

        graph.mergeEdge(mergeThis);
        /**
         * expected graph:
         *
         *
         *      1
         *     ||
         *     ||
         *     2
         *    /||
         *   / ||
         *  /  ||
         * 4----5
         */
        assertThat(graph.getVerticles().size(), is(4));
        assertThat(graph.getAdjacents(1), is(Arrays.asList(new Integer[]{2, 2})));
        assertThat(graph.getAdjacents(2), is(Arrays.asList(new Integer[]{1, 4, 5, 1, 5})));
        assertThat(graph.getAdjacents(4), is(Arrays.asList(new Integer[]{2, 5})));
        assertThat(graph.getAdjacents(5), is(Arrays.asList(new Integer[]{2, 4, 2}))); // TODO order should not matter
    }
}
