package org.talangsoft.algorithms.week6;


import java.util.Optional;

public final class Heap<T extends Comparable<T>> {
    public static final int MIN = -1;
    public static final int MAX = 1;
    public static final int CAPACITY = 2;

    int size = 0;
    T[] nodes;
    int type = MIN;

    public Heap() {
        this(MIN);
    }

    public boolean isEmpty(){
        return size==0;
    }


    public Heap(int type) {
        nodes = (T[]) new Comparable[CAPACITY];
        this.type = type;
    }

    private void swap(int index1, int index2) {
        System.out.println(String.format("Swap %d %d", index1, index2));
        T temp = nodes[index1];
        nodes[index1] = nodes[index2];
        nodes[index2] = temp;
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
        // bubble up
        for (; pos > 1 && elem.compareTo(nodes[parent(pos)]) == type; pos = pos / 2)
            nodes[pos] = nodes[pos / 2];

        nodes[pos] = elem;
    }

    public void insert(T... elements) {
        for (T t : elements) {
            insert(t);
        }
    }

    public Optional<T> extractRoot() {
        if (size == 0) return Optional.empty();
        else {
            T root = nodes[1];
            nodes[1] = nodes[size];
            nodes[size--] = null;
            bubbleDown(1);
            return Optional.of(root);
        }
    }

    private void bubbleDown(int k) {
        T tmp = nodes[k];
        int child = 0;

        for (; 2 * k <= size; k = child) {
            child = leftChild(k);

            if (child != size && nodes[child].compareTo(nodes[rightChild(k)]) == (-1 * type)) child++;

            if (tmp.compareTo(nodes[child]) == (-1 * type)) nodes[k] = nodes[child];
            else
                break;
        }
        nodes[k] = tmp;
    }

    public Optional<T> getRoot() {
        if (size == 0) return Optional.empty();
        else {
            return Optional.of(nodes[1]);
        }
    }

    public void printHeap() {
        throw new UnsupportedOperationException("Implement me");
    }
}
