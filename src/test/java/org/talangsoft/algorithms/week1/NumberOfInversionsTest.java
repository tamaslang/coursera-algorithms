package org.talangsoft.algorithms.week1;

import org.junit.Test;
import org.talangsoft.algoritms.week1.NumberOfInversions;

import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class NumberOfInversionsTest {
    private NumberOfInversions underTest = new NumberOfInversions();

    @Test
    public void countNrOfInversionsInSortedArrayShouldBe0() {
        assertThat(underTest.countNumberOfInversions(IntStream.rangeClosed(1, 6).toArray()), is(0L));
    }

    @Test
    public void countNrOfInversionsInArrayShouldBe3() {
        assertThat(underTest.countNumberOfInversions(new int[]{1,3,5,2,4,6}),is(3L));
    }

    @Test
    public void countNrOfInversionsInReverseSortedArrayShouldBe15() {
        assertThat(underTest.countNumberOfInversions(IntStream.rangeClosed(1, 6).map(nr -> 6-nr).toArray()),is(15L));
    }
}
