import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author gvandomelen19
 */
public class Deque<E> implements Cloneable, Iterable<E> {

    private Node<E> head;
    private int length;
    
    public Deque() {
        head = null;
        length = 0;
    }
    
    public void addFirst(E data) {
        if (length == 0) {
            head = new Node(data);
            head.next = head;
            head.last = head;
        } else {
            Node<E> n = new Node(data);
            n.next = head;
            n.last = head.last;
            head.last.next = n;
            head.last = n;
            head = n;
        }
        ++length;
    }
    
    public E removeFirst() {
        E ret;
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
    
    public void addLast(E data) {
        if (length == 0) {
            head = new Node(data);
            head.next = head;
            head.last = head;
        } else {
            Node<E> n = new Node(data);
            n.next = head;
            n.last = head.last;
            head.last.next = n;
            head.last = n;
        }
        ++length;
    }
    
    public boolean isEmpty() { return length < 1; }
    
    public E removeLast() {
        E ret;
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
    public Iterator<E> iterator() {
        return new QueueIterator();
    }
    
    private class QueueIterator implements Iterator<E> {
        
        Node<E> node = head.last;
        int left = length;
        
        @Override
        public boolean hasNext() {
            return left > 0;
        }

        @Override
        public E next() {
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
    
    private class Node<E> {
        
        private Node<E> next;
        private Node<E> last;
        private E data;
        
        private Node(E data) {
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
    
}
