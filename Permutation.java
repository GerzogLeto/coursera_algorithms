import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        int i = 0;
        while (!StdIn.isEmpty()) {
            String value = StdIn.readString();
            queue.enqueue(value);
        }
        while (i < Integer.parseInt(args[0])) {
            System.out.println(queue.dequeue());
            i++;
        }

    }
}
