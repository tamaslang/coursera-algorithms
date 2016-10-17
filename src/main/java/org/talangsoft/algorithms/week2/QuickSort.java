package org.talangsoft.algorithms.week2;


interface PivotChooseStrategy {
    int choosePivotAndMakeItFirst(int[] array, int left, int right);
}


public class QuickSort {


    static class ChooseFirstPivot implements PivotChooseStrategy {
        @Override
        public int choosePivotAndMakeItFirst(int[] array, int left, int right) {
            return array[left];
        }
    }

    static class ChooseLastPivot implements PivotChooseStrategy {
        @Override
        public int choosePivotAndMakeItFirst(int[] array, int left, int right) {
            swap(array, left, right - 1);
            return array[left];
        }
    }

    static class ChooseMedianPivot implements PivotChooseStrategy {

        private boolean isRightMedian(int[] array, int left, int right, int middle) {
            return (array[right - 1] <= Math.max(array[left], array[middle]) && array[right - 1] >= Math.min(array[left], array[middle]));
        }

        private boolean isMiddleMedian(int[] array, int left, int right, int middle) {
            return (array[middle] <= Math.max(array[left], array[right - 1]) && array[middle] >= Math.min(array[left], array[right - 1]));
        }

        @Override
        public int choosePivotAndMakeItFirst(int[] array, int left, int right) {
            int middle = (right - left - 1) / 2 + left;
            if (isRightMedian(array, left, right, middle)) {
                swap(array, left, right - 1);
            }
            if (isMiddleMedian(array, left, right, middle)) {
                swap(array, left, middle);
            }
            return array[left];
        }
    }

    private long totalNrOfComputations = 0;

    private PivotChooseStrategy pivotStrategy;

    public QuickSort(PivotChooseStrategy pivotStrategy) {
        this.pivotStrategy = pivotStrategy;
    }

    public long getTotalNrOfComputations() {
        return totalNrOfComputations;
    }

    private static void swap(int[] array, int i, int j) {
        int swap = array[j];
        array[j] = array[i];
        array[i] = swap;
    }

    private int[] partitionAndSort(int[] array, int left, int right) {
        // basecase
        if (left == right) return array;

        // count number of computations
        long computations = Math.max(0, right - left - 1);
        totalNrOfComputations += computations;

        // choose picot element
        int p = pivotStrategy.choosePivotAndMakeItFirst(array, left, right);
        // partition
        int i = left + 1;
        for (int j = left + 1; j < right; j++) {
            if (array[j] < p) {
                swap(array, i, j);
                i++;
            }
        }
        // swap partition element
        swap(array, left, i - 1);
        // sort left
        partitionAndSort(array, left, i - 1);
        // sort right
        partitionAndSort(array, i, right);
        return array;
    }

    public int[] quickSort(int[] array) {
        return partitionAndSort(array, 0, array.length);
    }
}
