package org.talangsoft.algorithms.week1;

import org.junit.Test;
import org.talangsoft.algorithms.helper.InputFileReader;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;


public class NrOfInversionsAssignmentTest {

    private String sourceFile = "week1/nr_of_inversions.txt";

    @Test
    public void countNrOfInversions() throws Exception{
        int[] elements = InputFileReader.readIntElements(sourceFile);
        assertThat(elements.length, is(100000));
        assertThat(new NumberOfInversions().countNumberOfInversions(elements),is(2407905288L));
    }
}
