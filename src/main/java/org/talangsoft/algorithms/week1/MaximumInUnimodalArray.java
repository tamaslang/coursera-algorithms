package org.talangsoft.algorithms.week1;

/*
  You are a given a unimodal array of n distinct elements,
  meaning that its entries are in increasing order up until its maximum element,
  after which its elements are in decreasing order.
  Give an algorithm to compute the maximum element that runs in O(log n)time.
*/
public class MaximumInUnimodalArray {


    public int maxInUnimodalWithNarrowingInterval(int[] array, int left, int right) {
        if (left == right) {
            return array[left];
        } else if(array[left] > array[right]){
            maxInUnimodalWithNarrowingInterval(array, left,  right);
        }
        return 0; // TODO
    }

    public int maxInUnimodal(int[] array) {
        return maxInUnimodalWithNarrowingInterval(array, 0, array.length - 1);
    }
}
