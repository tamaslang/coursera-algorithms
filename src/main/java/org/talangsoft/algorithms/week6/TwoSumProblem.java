package org.talangsoft.algorithms.week6;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
                // System.out.println(String.format("Pair %d %d -> %d",nr,sum - nr, sum));
                return true;
            }
        }
        return false;
    }

    public boolean findTwoSumNaiveWithHashNegativePositive(long[] positiveNumbers, Map<Long, Long> negativeNumbers, long sum) {
        for (long nr : positiveNumbers) {
            if (negativeNumbers.containsKey(sum - nr)) {
                return true;
            }
        }
        return false;
    }

    public int findIndexOfFirstElementBiggerThan(long[] sortedNumbers, long lookupNumber, int beginIndex, int endIndex) {
        if (lookupNumber <= sortedNumbers[beginIndex] || beginIndex == endIndex) return beginIndex;
        int splitIndex = (beginIndex + endIndex) / 2;
        if (lookupNumber < sortedNumbers[splitIndex])
            return findIndexOfFirstElementBiggerThan(sortedNumbers, lookupNumber, beginIndex + 1, splitIndex - 1);
        else return findIndexOfFirstElementBiggerThan(sortedNumbers, lookupNumber, splitIndex, endIndex);
    }

    private Supplier<TreeSet<Long>> treeSetSupplier = () -> new TreeSet<Long>();

    public int findTwoSumNumberForSumsInInterval(long[] numbers, int diff) {
        long[] positives = Arrays.stream(numbers).filter(n -> n >= 0).toArray();
        long[] negativeAbs = Arrays.stream(numbers).filter(n -> n < 0).map(n -> n * -1).toArray();
        TreeSet<Long> negativeAbsesSet  = Arrays.stream(negativeAbs).mapToObj(Long::valueOf).collect(Collectors.toCollection(treeSetSupplier));

        int[] diffFoundsForDistance = new int[diff * 2 + 1];
        for (int i = 0; i < positives.length; i++) {
            long intervalStart = positives[i] - diff - 1;
            long intervalEnd = positives[i] + diff;

            Long aHigherKey = negativeAbsesSet.higher(intervalStart);
            if (aHigherKey != null && aHigherKey <= intervalEnd) {
                diffFoundsForDistance[(int) (aHigherKey - intervalStart)] = 1;
            }
        }

        return (int) Arrays.stream(diffFoundsForDistance).filter(f -> f == 1).count();
    }
}
