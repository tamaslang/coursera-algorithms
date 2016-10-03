package org.talangsoft.algorithms.week1;

import org.junit.Test;
import org.talangsoft.algoritms.week1.MergeSort;

import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MergeSortTest {

    private MergeSort underTest = new MergeSort();

    int[] from1toMillion = IntStream.rangeClosed(1,1000000).toArray();
    int[] fromMillionTo1 = IntStream.rangeClosed(1,1000000).map(nr -> 1000001 - nr).toArray();

    @Test
    public void testMergeSortForSortedInputWith1MillionItems() {
        assertThat(underTest.mergeSort(from1toMillion), is(from1toMillion));
    }

    @Test
    public void testMergeSortForUnsortedInputWith1MillionItems() {
        assertThat(underTest.mergeSort(fromMillionTo1), is(from1toMillion));
    }

    @Test
    public void testMergeSortForSortedInputWith2Items() {
        assertThat(underTest.mergeSort(new int[]{1, 2}), is(new int[]{1, 2}));
    }

    @Test
    public void testMergeSortForUnSortedInputWith2Items() {
        assertThat(underTest.mergeSort(new int[]{2, 1}), is(new int[]{1, 2}));
    }

    @Test
    public void testMergeSortForUnSortedInputWith4Items() {
        assertThat(underTest.mergeSort(new int[]{4, 3, 2, 1}), is(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void testMergeSortForUnSortedInputWith5Items() {
        assertThat(underTest.mergeSort(new int[]{5, 4, 3, 2, 1}), is(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testMergeSortForInputWith1Items() {
        assertThat(underTest.mergeSort(new int[]{1}), is(new int[]{1}));
    }

    @Test
    public void testMergeSortForInputWithNoItems() {
        assertThat(underTest.mergeSort(new int[]{}), is(new int[]{}));
    }
}
