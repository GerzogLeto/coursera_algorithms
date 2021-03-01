import java.util.Iterator;

public class LinkedQueue<T> implements Iterable<T> {
    private Node first, last;

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private class  QueueIterator implements Iterator<T> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    private class Node{
        private T item;
        private Node next;
    }
    public boolean isEmpty(){
        return first == null;
    }

    public void enqueue(T item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else oldLast.next = last;
    }

    public T dequeue(){
        T item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        return item;
    }
}
