import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class ArrayQueue<T> implements Iterable<T> {
    private T[] arr;
    private int head;
    private int tail;

    @Override
    public Iterator<T> iterator() {
        return new DirectIterator();
    }

    private class DirectIterator implements Iterator<T>{
        private int counter;

        public DirectIterator() {
            this.counter = head;
        }

        @Override
        public boolean hasNext() {
            return this.counter != tail;
        }

        @Override
        public T next() {
            return arr[counter++];
        }
    }

    private class Node{
        private T item;
        private Node next;
    }


    public ArrayQueue() {
        arr  =(T[]) new Object[1];
        tail = 0;
        head = 0;
    }

    public boolean isEmpty(){
        return head == tail;
    }

    public void addLast(T item){
        if(tail - head == arr.length) resize(2 * arr.length);
        if(head > 0 && head == arr.length / 4) resize(arr.length / 2);
        arr[tail++] = item;
    }

    public T removeFirst(){
        T temp = arr[head++];
        arr[head - 1] = null;
        return temp;
    }

    private void resize(int capacity){
        T[] nArr = (T[]) new Object[capacity];
        int j = 0;
        for (int i = head; i < tail; i++, j++) {
            nArr[j] = arr[i];
        }
        head = 0;
        tail = j;
        arr = nArr;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        while (!StdIn.isEmpty()) {
            int value = StdIn.readInt();
            queue.addLast(value);
        }

        for (Integer integer : queue) {
            System.out.println(integer);
        }
        System.out.println("remove first: " + queue.removeFirst());
        for (Integer integer : queue) {
            System.out.println(integer);
        }

    }
}
