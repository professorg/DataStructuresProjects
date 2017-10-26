import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gvandomelen19
 */
public class RandomizedQueue<E> implements Iterable<E> {

    private E[] items;
    private int length;

    public RandomizedQueue() {
        items = (E[])new Object[1];
        length = 0;
    }                 // construct an empty randomized queue

    public boolean isEmpty() {
        return length == 0;
    }                 // is the randomized queue empty?

    public int size() {
        return length;
    }                        // return the number of items on the randomized queue

    public void enqueue(E item) {
        if (length == items.length) {
            items = Arrays.copyOf(items, items.length << 1);
        }
        items[length++] = item;
    }           // add the item

    public E dequeue() {
        if (length < 1) throw new NoSuchElementException("Queue is empty");
        int r = StdRandom.uniform(length);
        swap(items, r, --length);
        E ret = items[length];
        if (length <= items.length >> 2) {
            items = Arrays.copyOf(items, items.length >> 1);
        }
        return ret;
    }                    // remove and return a random item
    
    private void swap(Object[] o, int x, int y) {
        Object a = o[x];
        o[x] = o[y];
        o[y] = a;
    }

    public E sample() {
        return sample(length);
    }                     // return a random item (but do not remove it)
    
    private E sample(int left) {
        int r = StdRandom.uniform(left);
        swap(items, r, left-1);
        return items[left-1];
    }                     // return a random item (but do not remove it)

    public Iterator<E> iterator() {
        return new RandomQueueIterator();
    }         // return an independent iterator over items in random order

    public static void main(String[] args) {
        
        RandomizedQueue<Double> rq = new RandomizedQueue();
        rq.enqueue(1.5);
        rq.enqueue(6.3);
        rq.enqueue(3.98);
        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
        
    }   // unit testing (optional)

    private class Node<E> {

        private Node<E> next;
        private E data;

        private Node(Node next, E data) {
            this.next = next;
            this.data = data;
        }

        private Node tail() {
            Node ret = this;
            while (ret.next != null) {
                ret = ret.next;
            }
            return ret;
        }

    }

    private class RandomQueueIterator implements Iterator<E> {

        int left = length;
        int[] indices = new int[length];
        
        private RandomQueueIterator() {
            Arrays.setAll(indices, i -> i);
        }

        @Override
        public boolean hasNext() {
            return left > 0;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int r = StdRandom.uniform(left);
            swap(indices, --left, r);
            return items[indices[left]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
