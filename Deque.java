import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int counter = 0;

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public void remove() {
            throw new UnsupportedOperationException("this operation is not supported");
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!this.hasNext()) throw new NoSuchElementException("elements is ends");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public boolean isEmpty() {
        if (first == null)
            return true;
        if (last == null)
            return true;
        return false;
    }

    public int size() {
        return counter;
    }


    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("item equals null");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        if (isEmpty() || size() == 0) first = last;
        else oldLast.next = last;
        counter++;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("item equals null");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        if (isEmpty() || size() == 0) last = first;
        else oldFirst.prev = first;
        counter++;
    }

    public Item removeFirst() {
        if (this.isEmpty()) throw new NoSuchElementException("dequeue is empty");
        Item item = first.item;
        first = first.next;
        if (first != null) first.prev = null;
        else last = first;
        counter--;
        return item;
    }

    public Item removeLast() {
        if (this.isEmpty()) throw new NoSuchElementException("dequeue is empty");
        Item item = last.item;
        last = last.prev;
        if (last != null) last.next = null;
        else first = last;
        counter--;
        return item;
    }

    public static void main(String[] args) {
        int[] temp = new int[8];
        int count = 0;
        Deque<Integer> deque = new Deque<>();
//        System.out.println("size: " + dequeue.size());
        while (!StdIn.isEmpty()) {
            temp[count] = StdIn.readInt();
            count++;
        }
        for (int i = 0; i < 4; i++) {
            deque.addFirst(temp[i]);
        }
        for (Integer integer : deque) {
            System.out.print(integer);
            System.out.print(" ");
        }
        System.out.println();
        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();
        deque.removeLast();
        System.out.println(deque.isEmpty());
        for (int i = 4; i < 8; i++) {
            deque.addFirst(temp[i]);
        }
        for (Integer integer : deque) {
            System.out.print(integer);
            System.out.print(" ");
        }
        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();
        deque.removeLast();
        System.out.println(deque.isEmpty());
    }
}
