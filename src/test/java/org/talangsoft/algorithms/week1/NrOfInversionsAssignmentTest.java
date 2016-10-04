package org.talangsoft.algorithms.week1;

import org.junit.Before;
import org.junit.Test;
import org.talangsoft.algoritms.week1.NumberOfInversions;

import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;


public class NrOfInversionsAssignmentTest {

    private String sourceFile = "week1/nr_of_inversions.txt";


    @Test
    public void countNrOfInversions() throws Exception{
        // reading lines
        ClassLoader classLoader = getClass().getClassLoader();
        java.net.URL url = classLoader.getResource(sourceFile);
        java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
        int[] elements = java.nio.file.Files.readAllLines(resPath).stream().mapToInt(Integer::valueOf).toArray();
        assertThat(elements.length, is(100000));
        assertThat(new NumberOfInversions().countNumberOfInversions(elements),is(2407905288L));
    }
}
