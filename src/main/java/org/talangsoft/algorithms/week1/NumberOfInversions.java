package org.talangsoft.algorithms.week1;

import java.util.Arrays;

public class NumberOfInversions {

    private int[] leftOf(int[] array) {
        return Arrays.copyOfRange(array, 0, array.length / 2);
    }

    private int[] rightOf(int[] array) {
        return Arrays.copyOfRange(array, array.length / 2, array.length);
    }

    private long sortAndCount(final int[] left, final int[] right, int[] mergedOut) {

        long splitInversions = 0;

        int[] sortedLeft = left;
        if (left.length > 1) {
            sortedLeft = new int[left.length];
            splitInversions += sortAndCount(leftOf(left), rightOf(left), sortedLeft);
        }

        int[] sortedRight = right;
        if (right.length > 1) {
            sortedRight = new int[right.length];
            splitInversions += sortAndCount(leftOf(right), rightOf(right), sortedRight);
        }

        for (int n = 0, i = 0, j = 0; n < mergedOut.length; n++) {
            if (j >= sortedRight.length || (i < sortedLeft.length && sortedLeft[i] < sortedRight[j])) {
                mergedOut[n] = sortedLeft[i++];
            } else {
                splitInversions += sortedLeft.length - i;
                mergedOut[n] = sortedRight[j++];
            }
        }
        return splitInversions;
    }


    public long countNumberOfInversions(int[] array) {
        if (array.length < 2) {
            return 0;
        } else {
            return sortAndCount(leftOf(array), rightOf(array), new int[array.length]);
        }
    }

    public long countNumberOfInversionsBruteForce(int[] array) {
        long nrOfInversions = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    nrOfInversions++;
                }
            }
        }
        return nrOfInversions;
    }
}
