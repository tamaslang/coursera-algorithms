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
        Map<Integer, Integer[]> graphVerticlesAndAdjacents = new HashMap<>();
        graphVerticlesAndAdjacents.put(1, new Integer[]{2, 3});
        graphVerticlesAndAdjacents.put(2, new Integer[]{1, 3, 4, 5});
        graphVerticlesAndAdjacents.put(3, new Integer[]{1, 2, 5});
        graphVerticlesAndAdjacents.put(4, new Integer[]{2, 5});
        graphVerticlesAndAdjacents.put(5, new Integer[]{2, 3, 4});
        return Graph.from(graphVerticlesAndAdjacents);
    }


    @Test
    public void testMerge() {
        Graph graph =getGraph1();
        graph.mergeEdge(2,3);
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
        assertThat(graph.getAdjacents(1), is(new Integer[]{2, 2}));
        assertThat(graph.getAdjacents(2), is(new Integer[]{1, 4, 5, 1, 5}));
        assertThat(graph.getAdjacents(4), is(new Integer[]{2, 5}));
        assertThat(graph.getAdjacents(5), is(new Integer[]{2, 2, 4})); // TODO order should not matter
    }
}
