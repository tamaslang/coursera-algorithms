package org.talangsoft.algorithms.week1;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SecondLargestNumberInArrayTest {

    private SecondLargestNumberInArray underTest = new SecondLargestNumberInArray();

    @Test
    public void secondLargestNumberInSortedArray() {
        assertThat(underTest.secondLargestNumber(new int[]{1, 2, 3, 4, 5, 6, 7, 8}), is(7));
    }

    @Test
    public void scondLargestNumberInReverseSortedArray() {
        assertThat(underTest.secondLargestNumber(new int[]{8, 7, 6, 5, 4, 3, 2, 1}), is(7));
    }

    @Test
    public void secondLargestNumberInRandomSortedArray() {
        assertThat(underTest.secondLargestNumber(new int[]{1, 2, 4, 3, 7, 6, 5, 8}), is(7));
    }

    @Test
    public void secondLargestNumberInArrayWithDuplicatedElements() {
        assertThat(underTest.secondLargestNumber(new int[]{1, 2, 6, 7, 8, 7, 5, 4}), is(7));
    }

    @Test
    public void secondLargestNumberInArrayWithDuplicatedLargestElement() {
        assertThat(underTest.secondLargestNumber(new int[]{1, 2, 6, 7, 8, 7, 5, 4, 8}), is(8));
    }
}
