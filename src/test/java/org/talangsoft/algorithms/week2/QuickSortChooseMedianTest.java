package org.talangsoft.algorithms.week2;


import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class QuickSortChooseMedianTest {

    private QuickSort underTest = new QuickSort(new QuickSort.ChooseMedianPivot());

    @Test
    public void quickSortForExampleInputShouldReturnSortedOutput() {
        assertThat(underTest.quickSort(new int[]{3, 8, 2, 5, 1, 4, 7, 6}), is(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }

    @Test
    public void quickSortForSortedInputShouldReturnSortedOutput() {
        assertThat(underTest.quickSort(new int[]{1, 2, 3, 4, 5, 6, 7, 8}), is(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }

    @Test
    public void quickSortForReverseSortedInputShouldReturnSortedOutput() {
        assertThat(underTest.quickSort(new int[]{8, 7, 6, 5, 4, 3, 2, 1}), is(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }

    @Test
    public void quickSortForInputWithOddElementShouldReturnSortedOutput() {
        assertThat(underTest.quickSort(new int[]{1, 2, 3, 4, 5,}), is(new int[]{1, 2, 3, 4, 5}));
    }

}