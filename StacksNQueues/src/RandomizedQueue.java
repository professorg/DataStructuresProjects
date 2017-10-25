import java.lang.reflect.Array;
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

    private Object[] items;
    private int length;

    public RandomizedQueue() {
        items = new Object[1];
        length = 0;
    }                 // construct an empty randomized queue

    public boolean isEmpty() {
        return length == 0;
    }                 // is the randomized queue empty?

    public int size() {
        return length;
    }                        // return the number of items on the randomized queue

    public void enqueue(E item) {
    }           // add the item

    public E dequeue() {
    }                    // remove and return a random item

    public E sample() {
    }                     // return a random item (but do not remove it)

    public Iterator<E> iterator() {
        return new RandomQueueIterator();
    }         // return an independent iterator over items in random order

    public static void main(String[] args) {
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

        Node<E> node = taill.next;
        int left = length;

        @Override
        public boolean hasNext() {
            return left > 0;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            --left;
            node = node.next;
            return node.data;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}
