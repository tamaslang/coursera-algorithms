package org.talangsoft.algorithms.week6;
import java.util.Optional;

public final class Heap<T extends Comparable<T>> {
    public static final int CAPACITY = 2;

    public enum SortType {
        MIN(-1),
        MAX(1);

        private int compareConstant;

        SortType(int compareConstant) {
            this.compareConstant = compareConstant;
        }
    }

    private int size = 0;
    private T[] nodes;
    private SortType sortType = SortType.MIN;

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public Heap(SortType sortType) {
        nodes = (T[]) new Comparable[CAPACITY];
        this.sortType = sortType;
    }

    private int parent(int index) {
        return (index) / 2;
    }

    private int leftChild(int index) {
        return 2 * index;
    }

    private int rightChild(int index) {
        return 2 * index + 1;
    }

    private void doubleSize() {
        T[] old = nodes;
        nodes = (T[]) new Comparable[nodes.length * 2];
        System.arraycopy(old, 1, nodes, 1, size);
    }

    private void buildHeap() {
        for (int k = size / 2; k > 0; k--) {
            bubbleDown(k);
        }
    }

    public void heapSort(T[] array) {
        size = array.length;
        nodes = (T[]) new Comparable[size + 1];
        System.arraycopy(array, 0, nodes, 1, size);
        buildHeap();

        for (int i = size; i > 0; i--) {
            T tmp = nodes[i]; //move top item to the end of the heap array
            nodes[i] = nodes[1];
            nodes[1] = tmp;
            size--;
            bubbleDown(1);
        }
        for (int k = 0; k < nodes.length - 1; k++)
            array[k] = nodes[nodes.length - 1 - k];
    }

    public void insert(T elem) {
        if (size == nodes.length - 1) doubleSize();
        int pos = ++size;
        nodes[pos] = elem;
        bubbleUp(pos);
    }

    public void insert(T... elements) {
        for (T t : elements) {
            insert(t);
        }
    }

    public Optional<T> extract() {
        if (size == 0) return Optional.empty();
        else {
            T root = nodes[1];
            nodes[1] = nodes[size];
            nodes[size--] = null;
            bubbleDown(1);
            return Optional.of(root);
        }
    }

    private void bubbleUp(int pos) {
        T elem = nodes[pos];
        for (; pos > 1 && elem.compareTo(nodes[parent(pos)]) == sortType.compareConstant; pos = pos / 2) {
            nodes[pos] = nodes[pos / 2];
        }
        nodes[pos] = elem;
    }

    private void bubbleDown(int pos) {
        T elem = nodes[pos];
        int child = 0;

        for (; 2 * pos <= size; pos = child) {
            child = leftChild(pos);

            if (child != size && nodes[child].compareTo(nodes[rightChild(pos)]) == (-1 * sortType.compareConstant)) {
                child++;
            }
            if (elem.compareTo(nodes[child]) == (-1 * sortType.compareConstant)) {
                nodes[pos] = nodes[child];
            } else {
                break;
            }
        }
        nodes[pos] = elem;
    }

    public Optional<T> getRoot() {
        if (size == 0) return Optional.empty();
        else {
            return Optional.of(nodes[1]);
        }
    }
}
