import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author gvandomelen19
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {

    private Node head;
    private int length;
    
    public Deque() {
        head = null;
        length = 0;
    }
    
    public void addFirst(Item data) {
        if (length == 0) {
            head = new Node(data);
            head.next = head;
            head.last = head;
        } else {
            Node n = new Node(data);
            n.next = head;
            n.last = head.last;
            head.last.next = n;
            head.last = n;
            head = n;
        }
        ++length;
    }
    
    public Item removeFirst() {
        Item ret;
        if (length < 1) {
            throw new NoSuchElementException("Deque is empty");
        } else if (length == 1) {
            ret = head.data;
            head = null;
        } else {
            ret = head.data;
            head.last.next = head.next;
            head.next.last = head.last;
            head = head.next;
        }
        --length;
        return ret;
    }
    
    public void addLast(Item data) {
        if (length == 0) {
            head = new Node(data);
            head.next = head;
            head.last = head;
        } else {
            Node n = new Node(data);
            n.next = head;
            n.last = head.last;
            head.last.next = n;
            head.last = n;
        }
        ++length;
    }
    
    public boolean isEmpty() { return length < 1; }
    
    public Item removeLast() {
        Item ret;
        if (length < 1) {
            throw new NoSuchElementException("Deque is empty");
        } else if (length == 1) {
            ret = head.data;
            head = null;
        } else {
            ret = head.data;
            head.last.next = head.next;
            head.next.last = head.last;
        }
        --length;
        return ret;
    }
    
    public int size() {
        return length;
    }
    
    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }
    
    private class QueueIterator implements Iterator<Item> {
        
        Node node = head.last;
        int left = length;
        
        @Override
        public boolean hasNext() {
            return left > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            --left;
            node = node.next;
            return node.data;
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }
    
    private class Node {
        
        public Node next;
        public Node last;
        public Item data;
        
        private Node(Item data) {
            this.data = data;
        }
        
    }
    
}
