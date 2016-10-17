package org.talangsoft.algorithms.week1;


import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MaximumInUnimodalArrayTest {

    private MaximumInUnimodalArray underTest = new MaximumInUnimodalArray();

    @Test
    public void maxInUnimodalShouldReturnMaxInOneElementBalancedArray() {
        assertThat(underTest.maxInUnimodal(new int[]{10}), is(10));
    }

    @Test @Ignore
    public void maxInUnimodalShouldReturnMaxInThreeElementBalancedArray() {
        assertThat(underTest.maxInUnimodal(new int[]{1,10,1}), is(10));
    }

    @Test @Ignore
    public void maxInUnimodalShouldReturnMaxInBalancedArray() {
        assertThat(underTest.maxInUnimodal(new int[]{1, 4, 5, 10, 5, 4, 1}), is(10));
    }
}
