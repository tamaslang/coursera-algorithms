package org.talangsoft.algorithms.week6;

import java.util.Optional;

/**
 * The goal of this problem is to implement the "Median Maintenance" algorithm (covered in the Week 5 lecture on heap applications).
 * The text file contains a list of the integers from 1 to 10000 in unsorted order;
 * you should treat this as a stream of numbers, arriving one by one. Letting xi denote the ith number of the file,
 * the kth median mk is defined as the median of the numbers x1,…,xk. (So, if k is odd, then mk is ((k+1)/2)th smallest number among x1,…,xk;
 * if k is even, then mk is the (k/2)th smallest number among x1,…,xk.)
 * <p>
 * In the box below you should type the sum of these 10000 medians, modulo 10000 (i.e., only the last 4 digits).
 * That is, you should compute (m1+m2+m3+⋯+m10000)mod10000.
 */


public class Median {

    private Heap<Integer> low = new Heap(Heap.MAX);
    private Heap<Integer> high = new Heap(Heap.MIN);

    public void insert(Integer elem) {
        if (evenDistribution()) {
            insertToLow(elem);
        } else {
            insertToHigh(elem);
        }
    }

    private boolean evenDistribution() {
        return (low.size + high.size) % 2 == 0;
    }

    private void insertToHigh(Integer elem) {
        if (elem < low.getRoot().get()) {
            high.insert(low.extractRoot().get());
            low.insert(elem);
        } else {
            high.insert(elem);
        }
    }

    private void insertToLow(Integer elem) {
        if (!low.isEmpty() && elem > high.getRoot().get()) {
            low.insert(high.extractRoot().get());
            high.insert(elem);
        } else {
            low.insert(elem);
        }
    }

    public Optional<Integer> median() {
        if (low.size == 0) return Optional.empty();
        return Optional.of(low.getRoot().get());
    }


}
