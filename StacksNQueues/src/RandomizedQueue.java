
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
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int length;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        length = 0;
    }                 // construct an empty randomized queue

    public boolean isEmpty() {
        return length == 0;
    }                 // is the randomized queue empty?

    public int size() {
        return length;
    }                        // return the number of items on the randomized queue

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (length == items.length) {
            items = Arrays.copyOf(items, items.length << 1);
        }
        items[length++] = item;
    }           // add the item

    public Item dequeue() {
        if (length < 1) {
            throw new NoSuchElementException("Queue is empty");
        }
        int r = StdRandom.uniform(length);
        swap(items, r, --length);
        Item ret = items[length];
        items[length] = null;
        if (length < items.length >> 2) {
            items = Arrays.copyOf(items, items.length >> 1);
        }
        return ret;
    }                    // remove and return a random item

    private void swap(Object[] objs, int x, int y) {
        Object a = objs[x];
        objs[x] = objs[y];
        objs[y] = a;
    }

    private void swap(int[] ints, int x, int y) {
        int a = ints[x];
        ints[x] = ints[y];
        ints[y] = a;
    }

    public Item sample() {
        if (length < 1) throw new NoSuchElementException();
        int r = StdRandom.uniform(length);
        swap(items, r, length - 1);
        return items[length - 1];
    }                     // return a random item (but do not remove it)

    @Override
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }         // return an independent iterator over items in random order

    private class RandomQueueIterator implements Iterator<Item> {

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
        public Item next() {
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
