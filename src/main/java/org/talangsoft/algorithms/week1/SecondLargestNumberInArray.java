package org.talangsoft.algorithms.week1;

/*
  You are given as input an unsorted array of n distinct numbers,where n is a power of 2.
  Give an algorithm that identifies the second-largest number in the array,
  and that uses at most n+log2n−2comparisons.
 */
public class SecondLargestNumberInArray {

    public int secondLargestNumber(int[] array){
      int largest = Math.max(array[0], array[1]);
      int secondLargest = Math.min(array[0], array[1]);

      for(int i = 2; i<array.length; i++ ){
          if(array[i] > largest){
              secondLargest = largest;
              largest = array[i];
          } else if(array[i]> secondLargest){
              secondLargest = array[i];
          }
      }
      return secondLargest;
    }

}
