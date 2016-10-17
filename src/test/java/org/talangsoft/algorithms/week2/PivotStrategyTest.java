package org.talangsoft.algorithms.week2;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PivotStrategyTest {
    @Test
    public void choosePivotalElementSorted() {
        PivotChooseStrategy choosePivot = new QuickSort.ChooseMedianPivot();
        int[] inputArr = new int[]{1, 2, 3};
        assertThat(choosePivot.choosePivotAndMakeItFirst(inputArr, 0, 3), is(2));
        assertThat(inputArr[0], is(2));
    }

    @Test
    public void choosePivotalElementSortedV2() {
        PivotChooseStrategy choosePivot = new QuickSort.ChooseMedianPivot();
        int[] inputArr = new int[]{2, 1, 3};
        assertThat(choosePivot.choosePivotAndMakeItFirst(inputArr, 0, 3), is(2));
        assertThat(inputArr[0], is(2));
    }

    @Test
    public void choosePivotalElementSortedV3() {
        PivotChooseStrategy choosePivot = new QuickSort.ChooseMedianPivot();
        int[] inputArr = new int[]{3, 2, 1};
        assertThat(choosePivot.choosePivotAndMakeItFirst(inputArr, 0, 3), is(2));
        assertThat(inputArr[0], is(2));
    }

    @Test
    public void choosePivotalElementSortedV4() {
        PivotChooseStrategy choosePivot = new QuickSort.ChooseMedianPivot();
        int[] inputArr = new int[]{3, 1, 2};
        assertThat(choosePivot.choosePivotAndMakeItFirst(inputArr, 0, 3), is(2));
        assertThat(inputArr[0], is(2));
    }

    @Test
    public void choosePivotalElementSortedV5() {
        PivotChooseStrategy choosePivot = new QuickSort.ChooseMedianPivot();
        int[] inputArr = new int[]{3, 1, 0, 2};
        assertThat(choosePivot.choosePivotAndMakeItFirst(inputArr, 0, 4), is(2));
        assertThat(inputArr[0], is(2));
    }

    @Test
    public void choosePivotalElementSortedV6() {
        PivotChooseStrategy choosePivot = new QuickSort.ChooseMedianPivot();
        int[] inputArr = new int[]{4, 5, 6, 7};
        assertThat(choosePivot.choosePivotAndMakeItFirst(inputArr, 0, 4), is(5));
        assertThat(inputArr[0], is(5));

    }

    @Test
    public void choosePivotalElementSortedV7() {
        PivotChooseStrategy choosePivot = new QuickSort.ChooseMedianPivot();
        int[] inputArr = new int[]{8, 2, 4, 5, 7, 1};
        assertThat(choosePivot.choosePivotAndMakeItFirst(inputArr, 0, 6), is(4));
        assertThat(inputArr[0], is(4));
    }

    @Test
    public void choosePivotalElementSortedV8() {
        PivotChooseStrategy choosePivot = new QuickSort.ChooseMedianPivot();
        int[] inputArr = new int[]{4, 6, 10, 9, 5, 7, 8};
        assertThat(choosePivot.choosePivotAndMakeItFirst(inputArr, 0, 7), is(8));
        assertThat(inputArr[0], is(8));
    }
}
