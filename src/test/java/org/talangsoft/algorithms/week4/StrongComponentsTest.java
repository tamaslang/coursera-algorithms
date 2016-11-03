package org.talangsoft.algorithms.week4;

import org.junit.Test;
import org.talangsoft.algorithms.helper.InputFileReader;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StrongComponentsTest {


    private String lectureGraphSourceFile = "week4/week4_lecture_graph.txt";
    private String lectureGraphNoIndexNrsSourceFile = "week4/week4_lecture_graph_not_index_nrs.txt";

    private String assignmentGraphSourceFile = "week4/week4graph.txt";


    private StrongComponents underTest = new StrongComponents();

    @Test
    public void readWeek4LectureGraphGraph() throws Exception {
        long before = System.currentTimeMillis();
        Map<Integer, int[]> week4GraphData = InputFileReader.readDirectedGraphElementsFormatOfAtoBEdges(lectureGraphSourceFile);

        long after = System.currentTimeMillis();
        System.out.println(String.format("Reading input file '%s' with vertices %d took %dms", lectureGraphSourceFile, week4GraphData.size(), (after - before)));
        assertThat(week4GraphData.size(), is(9));
    }


    @Test
    public void strongComponent4LectureGraph() throws Exception {
        Map<Integer, int[]> week4GraphData = InputFileReader.readDirectedGraphElementsFormatOfAtoBEdges(lectureGraphSourceFile);
        List<Integer> strongComponentSizes = underTest.strongComponent(week4GraphData);
        assertThat(getComponentSizes(strongComponentSizes), is("3,3,3"));
    }

    @Test
    public void strongComponent4LectureGraphNoIndexNrs() throws Exception {
        Map<Integer, int[]> week4GraphData = InputFileReader.readDirectedGraphElementsFormatOfAtoBEdges(lectureGraphNoIndexNrsSourceFile);
        List<Integer> strongComponentSizes = underTest.strongComponent(week4GraphData);
        assertThat(getComponentSizes(strongComponentSizes), is("3,3,1,1,1"));
    }

    @Test
    public void strongComponent4AssignmentGraph() throws Exception {
        Map<Integer, int[]> week4Assignment = InputFileReader.readDirectedGraphElementsFormatOfAtoBEdges(assignmentGraphSourceFile);
        List<Integer> strongComponentSizes = underTest.strongComponent(week4Assignment);
        assertThat(getComponentSizes(strongComponentSizes, 5), is("434821,968,459,313,211"));
    }

    private String getComponentSizes(List<Integer> strongComponentSizes, int to) {
        strongComponentSizes.sort((i1, i2) -> i2.compareTo(i1));
        return String.join(",", strongComponentSizes.subList(0, to).stream().map(s -> s.toString()).toArray(String[]::new));
    }

    private String getComponentSizes(List<Integer> strongComponentSizes) {
        return getComponentSizes(strongComponentSizes, strongComponentSizes.size());
    }
}
