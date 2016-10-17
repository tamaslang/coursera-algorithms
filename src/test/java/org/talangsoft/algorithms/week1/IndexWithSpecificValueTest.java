package org.talangsoft.algorithms.week1;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class IndexWithSpecificValueTest {

    private IndexWithSpecificValue underTest = new IndexWithSpecificValue();

    @Test
    public void shouldReturnFalseForEmptyArray(){
        assertThat(underTest.indexExistsWithSpecificValueInSortedArr(new int[]{}),is(false));
        assertThat(underTest.getNumberOfIterations(),is(1));
    }

    @Test
    public void shouldReturnTrueForOneElementArray(){
        assertThat(underTest.indexExistsWithSpecificValueInSortedArr(new int[]{0}),is(true));
        assertThat(underTest.getNumberOfIterations(),is(1));
    }

    @Test
    public void shouldReturnTrueForIndexedValueAtLastPosition(){
        assertThat(underTest.indexExistsWithSpecificValueInSortedArr(new int[]{-1,-1,-1,-1, 4}),is(true));
        assertThat(underTest.getNumberOfIterations(),is(5));
    }


    @Test
    public void shouldReturnTrueForIndexedValueAtLastPositionWithMinimumHopsAsIndexEqualsArrayLength(){
        assertThat(underTest.indexExistsWithSpecificValueInSortedArr(new int[]{10,10,10,10,10,10,10,10,10,10,10}),is(true));
        assertThat(underTest.getNumberOfIterations(),is(2));
    }


    @Test
    public void shouldReturnTrueForIndexedValueAtLastPositionWithMinimumHopsAsIndexIsBiggerThanArrayLength(){
        assertThat(underTest.indexExistsWithSpecificValueInSortedArr(new int[]{6,7,8,9,10}),is(false));
        assertThat(underTest.getNumberOfIterations(),is(2));
    }

    @Test
    public void shouldReturnTrueForIndexedValueAtFirstPosition(){
        assertThat(underTest.indexExistsWithSpecificValueInSortedArr(new int[]{0,0,0,0,4}),is(true));
        assertThat(underTest.getNumberOfIterations(),is(1));
    }

    @Test
    public void shouldReturnTrueForIndexedValueAtMiddlePosition(){
        assertThat(underTest.indexExistsWithSpecificValueInSortedArr(new int[]{-1,-1, 2,10,10}),is(true));
        assertThat(underTest.getNumberOfIterations(),is(3));
    }
}
