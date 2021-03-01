import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int head;
    private int tail;

    public RandomizedQueue() {
        arr = (Item[]) new Object[1];
        tail = 0;
        head = 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private int counter;

        public RandomIterator() {
            this.counter = head;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("this operation is not supported");
        }

        @Override
        public boolean hasNext() {
            return this.counter != tail;
        }

        @Override
        public Item next() {
            if (!this.hasNext()) throw new NoSuchElementException("elements is ends");
            return arr[StdRandom.uniform(counter++, tail)];
        }
    }


    public boolean isEmpty() {
        return head == tail;
    }

    public int size() {
        return this.tail - this.head;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("item equals null");
        if (tail - head == arr.length) resize(2 * arr.length);
        if (head > 0 && head == arr.length / 4) resize(arr.length / 2);
        arr[tail++] = item;
    }

    public Item sample() {
        if (this.isEmpty()) throw new NoSuchElementException("dequeue is empty");
        return arr[StdRandom.uniform(head, tail)];
    }

    private Item removeFirst() {
        Item temp = arr[head++];
        arr[head - 1] = null;
        return temp;
    }


    public Item dequeue() {
        if (this.isEmpty()) throw new NoSuchElementException("dequeue is empty");
        int count = StdRandom.uniform(head, tail);
        Item temp = arr[count];
        arr[count] = arr[head];
        arr[head] = temp;
//        StdRandom.shuffle(arr, this.head, this.tail);
        return this.removeFirst();
    }

    private void resize(int capacity) {
        Item[] nArr = (Item[]) new Object[capacity];
        int j = 0;
        for (int i = head; i < tail; i++, j++) {
            nArr[j] = arr[i];
        }
        head = 0;
        tail = j;
        arr = nArr;
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        System.out.println("queue is empty: " + queue.isEmpty());
        System.out.println("size queue: " + queue.size());
        while (!StdIn.isEmpty()) {
            int value = StdIn.readInt();
            queue.enqueue(value);
        }
        System.out.println("queue is empty: " + queue.isEmpty());
        System.out.println("size queue: " + queue.size());
//        for (int i = 0; i < 10; i++) {
//            System.out.println("random value: " + queue.sample());
//        }
        for (Integer integer : queue) {
            System.out.println("random value: " + integer);
        }
        System.out.println("size queue: " + queue.size());
    }


}
