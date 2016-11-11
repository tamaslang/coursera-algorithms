package org.talangsoft.algorithms.week6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumProblem {

    public boolean findTwoSumNaive(long[] array, Long sum) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == sum) return true;
            }
        }
        return false;
    }

    public boolean findTwoSumWithSort(long[] array, long sum) {
        long[] sortedNums = Arrays.stream(array).sorted().toArray();
        return findTwoSumInSorted(sortedNums, sum);
    }

    public boolean findTwoSumInSorted(long[] sortedNums, long sum) {
        for (int i = 0; i < sortedNums.length; i++) {
            if (Arrays.binarySearch(sortedNums, sum - sortedNums[i]) > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean findTwoSumWithHashmap(long[] array, long sum) {
        Map<Long, Long> valueMap = new HashMap<>(array.length);
        for (int i = 0; i < array.length; i++) {
            if (valueMap.containsKey(array[i])) {
                return true;
            } else {
                valueMap.put(sum - array[i], array[i]);
            }
        }
        return false;
    }

    public Map<Long, Long> arrayToMap(long[] array) {
        Map<Long, Long> valueMap = new HashMap<>(array.length);
        for (long l : array) {
            valueMap.put(l, l);
        }
        return valueMap;
    }

    public boolean findTwoSumNaiveWithHash(long[] numbers, Map<Long, Long> numberMap, long sum) {
        for (long nr : numbers) {
            if (numberMap.containsKey(sum - nr)) {
                //System.out.println(String.format("Pair %d %d",nr,sum - nr));
                return true;
            }
        }
        return false;
    }


}
