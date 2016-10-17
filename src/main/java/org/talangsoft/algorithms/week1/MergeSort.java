package org.talangsoft.algorithms.week1;

import java.util.Arrays;

public class MergeSort {

    private int[] leftOf(int[] array) {
        return Arrays.copyOfRange(array, 0, array.length / 2);
    }

    private int[] rightOf(int[] array) {
        return Arrays.copyOfRange(array, array.length / 2, array.length);
    }

    private int[] sort(int[] left, int[] right) {
        int[] mergedOut = new int[left.length + right.length];
        int[] sortedLeft = left.length == 1 ? left : sort(leftOf(left), rightOf(left));
        int[] sortedRight = right.length == 1 ? right : sort(leftOf(right), rightOf(right));

        for (int n = 0, i = 0, j = 0; n < mergedOut.length; n++) {
            if (j >= sortedRight.length || (i < sortedLeft.length && sortedLeft[i] < sortedRight[j])) {
                mergedOut[n] = sortedLeft[i++];
            } else {
                mergedOut[n] = sortedRight[j++];
            }
        }
        return mergedOut;
    }

    public int[] mergeSort(int[] numbers) {
        if(numbers.length < 2) {
            return numbers;
        } else {
            return sort(leftOf(numbers), rightOf(numbers));
        }
    }
}
