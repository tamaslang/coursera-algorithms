package org.talangsoft.algorithms.week6;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HeapTest {

    @Test
    public void heapWithZeroElement() {
        Heap<Integer> heap = new Heap<>(Heap.SortType.MIN);
        assertThat(heap.extract(), is(Optional.empty()));
        heap.insert(1);
        assertThat(heap.extract(), is(Optional.of(1)));
        assertThat(heap.extract(), is(Optional.empty()));
    }

    @Test
    public void minHeap100to1Test() {
        Heap<Integer> heap = new Heap<>(Heap.SortType.MIN);
        for (int i = 100; i >= 1; i--) {
            heap.insert(i);
            verifyRootIs(heap, i);
        }
    }

    @Test
    public void minHeap1to100Test() {
        Heap<Integer> heap = new Heap<>(Heap.SortType.MIN);
        for (int i = 1; i <= 100; i++) {
            heap.insert(i);
            verifyRootIs(heap, 1);
        }
    }

    @Test
    public void extractMinRootFromHeap9Test() {
        Heap<Integer> heap = new Heap<>(Heap.SortType.MIN);
        heap.insert(4, 4, 8, 9, 4, 12, 9, 11, 13);
        verifyExtractedRootIs(heap, 4);
        verifyExtractedRootIs(heap, 4);
        verifyExtractedRootIs(heap, 4);
        verifyExtractedRootIs(heap, 8);
        verifyExtractedRootIs(heap, 9);
        verifyExtractedRootIs(heap, 9);
        verifyExtractedRootIs(heap, 11);
        verifyExtractedRootIs(heap, 12);
        verifyExtractedRootIs(heap, 13);
    }

    private void verifyExtractedRootIs(Heap<Integer> heap, int value) {
        assertThat(heap.extract(), is(Optional.of(value)));
    }

    private void verifyRootIs(Heap<Integer> heap, int value) {
        assertThat(heap.getRoot(), is(Optional.of(value)));
    }


    @Test
    public void extractMaxRootFromHeap9Test() {
        Heap<Integer> heap = new Heap<>(Heap.SortType.MAX);
        heap.insert(4, 4, 8, 9, 4, 12, 9, 11, 13);
        verifyExtractedRootIs(heap, 13);
        verifyExtractedRootIs(heap, 12);
        verifyExtractedRootIs(heap, 11);
        verifyExtractedRootIs(heap, 9);
        verifyExtractedRootIs(heap, 9);
        verifyExtractedRootIs(heap, 8);
        verifyExtractedRootIs(heap, 4);
        verifyExtractedRootIs(heap, 4);
        verifyExtractedRootIs(heap, 4);
    }


    @Test
    public void maxHeap100to1Test() {
        Heap<Integer> heap = new Heap<>(Heap.SortType.MAX);
        for (int i = 100; i >= 1; i--) {
            heap.insert(i);
            verifyRootIs(heap, 100);
        }
    }

    @Test
    public void maxHeap1to100Test() {
        Heap<Integer> heap = new Heap<>(Heap.SortType.MAX);
        for (int i = 1; i <= 100; i++) {
            heap.insert(i);
            verifyRootIs(heap, i);
        }
    }


    @Test
    public void heap1to100TestExtractMin() {
        Heap<Integer> heap = new Heap<>(Heap.SortType.MIN);
        for (int i = 1; i <= 100; i++) {
            heap.insert(i);
        }
        for (int i = 1; i <= 100; i++) {
            verifyExtractedRootIs(heap, i);
        }
    }

    @Test
    public void heap1to100TestExtractMax() {
        Heap<Integer> heap = new Heap<>(Heap.SortType.MAX);
        for (int i = 1; i <= 100; i++) {
            heap.insert(i);
        }
        for (int i = 100; i >= 1; i--) {
            verifyExtractedRootIs(heap, i);
        }
    }

}
