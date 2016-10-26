package org.talangsoft.algorithms.week3;

import org.junit.Ignore;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RandomizedSelectionTest {

    private RandomizedSelection underTest = new RandomizedSelection(new RandomizedSelection.ChooseMedianPivot());

    @Test
    public void shouldSelect3rdSmallestElementFromSortedArray() {
        assertThat(underTest.selectNthSmallestElement(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 3), is(3));
    }

    @Test
    public void shouldSelect2ndSmallestElementFromSortedArray() {
        assertThat(underTest.selectNthSmallestElement(new int[]{1, 2, 3, 4, 5}, 2), is(2));
    }

    @Ignore("Needs to be fixed")
    public void shouldSelect4thSmallestElementFromSortedArray() {
        assertThat(underTest.selectNthSmallestElement(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 4), is(4));
    }

    @Test
    public void shouldSelect3rdSmallestElementFromReverseSortedArray() {
        assertThat(underTest.selectNthSmallestElement(new int[]{8, 7, 6, 5, 4, 3, 2, 1}, 3), is(3));
    }


    @Test
    public void shouldSelect3rdSmallestElementFromReverseSortedArrayWithOddElements() {
        assertThat(underTest.selectNthSmallestElement(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}, 3), is(3));
    }

    @Test
    public void shouldSelect3rdSmallestElementFromReverseSortedArrayWithOddDecimalElements() {
        assertThat(underTest.selectNthSmallestElement(new int[]{90, 80, 70, 60, 50, 40, 30, 20, 10}, 3), is(30));
    }

    @Ignore("Needs to be fixed")
    public void testSortedIncrementalElementArrayEveryElementValueShouldMatchNthSmallest() {
        int[] arr1to100 = IntStream.rangeClosed(1, 100).toArray();
        for (int i = 1; i <= 100; i++) {
            assertThat(String.format("Looking for nth (%d) smallest failed.", i), underTest.selectNthSmallestElement(arr1to100, i), is(arr1to100[i - 1]));
        }
    }

    @Ignore("Needs to be fixed")
    public void testReverseSortedIncrementalElementArrayEveryElementValueShouldMatchNthSmallest() {
        int[] arr1to100 = IntStream.rangeClosed(1, 100).toArray();
        int[] arr100to1 = IntStream.rangeClosed(1, 100).map(e -> Math.abs(e - 101)).toArray();
        for (int i = 1; i <= 100; i++) {
            assertThat(String.format("Looking for nth (%d) smallest failed.", i), underTest.selectNthSmallestElement(arr100to1, i), is(arr1to100[i - 1]));
        }
    }
}