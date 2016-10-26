package org.talangsoft.algorithms.week3;

interface PivotChooseStrategy {
    int choosePivotAndMakeItFirst(int[] array, int left, int right);
}

public class RandomizedSelection {

    private PivotChooseStrategy pivotStrategy;

    public RandomizedSelection(PivotChooseStrategy pivotStrategy) {
        this.pivotStrategy = pivotStrategy;
    }


    // modified quicksort
    private int nThSmallestElement(int[] array, int left, int right, int zeroBasedSearchIndex) {
        // basecase
        if (left == right) return array[left];

        // choose pivot element
        int pivot = pivotStrategy.choosePivotAndMakeItFirst(array, left, right);
        // partition
        int i = left + 1;
        for (int j = left + 1; j < right; j++) {
            if (array[j] < pivot) swap(array, i++, j);
        }
        // swap partition element, so it gets to the right position (elementIndex)
        int elementIndex = (i-1);
        swap(array, left, elementIndex);
        if (zeroBasedSearchIndex < elementIndex) {
            // element is on the left
            return nThSmallestElement(array, left, elementIndex, zeroBasedSearchIndex);
        } else if (zeroBasedSearchIndex > elementIndex) {
            // element is on the right
            return nThSmallestElement(array, elementIndex + 1, right, zeroBasedSearchIndex - elementIndex);
        }
        // we have the element!!
        return array[elementIndex];
    }

    public int selectNthSmallestElement(int[] array, int n) {
        return nThSmallestElement(array, 0, array.length, n-1);
    }

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

        private boolean isRightTheMedian(int[] array, int left, int right, int middle) {
            return (array[right - 1] <= Math.max(array[left], array[middle]) && array[right - 1] >= Math.min(array[left], array[middle]));
        }

        private boolean isMiddleTheMedian(int[] array, int left, int right, int middle) {
            return (array[middle] <= Math.max(array[left], array[right - 1]) && array[middle] >= Math.min(array[left], array[right - 1]));
        }

        @Override
        public int choosePivotAndMakeItFirst(int[] array, int left, int right) {
            int middle = (right - left - 1) / 2 + left;
            if (isRightTheMedian(array, left, right, middle)) {
                swap(array, left, right - 1);
            }
            if (isMiddleTheMedian(array, left, right, middle)) {
                swap(array, left, middle);
            }
            return array[left];
        }
    }

    private static void swap(int[] array, int i, int j) {
        int swap = array[j];
        array[j] = array[i];
        array[i] = swap;
    }

}
