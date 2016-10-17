package org.talangsoft.algorithms.week1;

/*
  You are given a sorted(from smallest to largest)array A of n distinct integers which can be positive,negative,or zero.
  You want to decide whether or not there is an index i such that A[i]=i.
  Design the fastest algorithm that you can for solving this problem.
 */
public class IndexWithSpecificValue {

    // TODO this is here to test efficiency
    private int numberOfIterations = 0;

    public int getNumberOfIterations() {
        return numberOfIterations;
    }

    public boolean indexExistsWithSpecificValueInSortedArrBruteForce(int[] array) {
        for (int i = 0; i < array.length; i++) {
            numberOfIterations++;
            if (i == array[i]) return true;
        }
        return false;
    }


    public boolean indexExistsWithSpecificValueInSortedArr(int startIndex, int[] array){
        numberOfIterations++;
        if(startIndex>=array.length) return false;
        if(startIndex == array[startIndex]) return true;
        int nextIndex = Math.max(startIndex+1,array[startIndex]);
        return indexExistsWithSpecificValueInSortedArr(nextIndex,array);
    }

    public boolean indexExistsWithSpecificValueInSortedArr(int[] array) {
        return indexExistsWithSpecificValueInSortedArr(0,array);
    }
}
