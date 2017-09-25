/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresprojects.types;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author gvandomelen19
 */
public class CircularQueue<E> implements Cloneable, Iterable<E> {

    private Node<E> tail;
    private int length;
    
    public CircularQueue<E> enqueue(E data) {
        ++length;
        if (tail == null) {
            tail = new Node(null, data);
            tail.next = tail;
        } else {
            tail.next = new Node(tail.next, data);
            tail = tail.next;
        }
        return this;
    }
    
    public E dequeue() {
        if (tail == null) throw new NoSuchElementException("Queue is empty");
        --length;
        E ret = tail.next.data;
        if (tail.next == tail) {
            tail = null;
            return ret;
        } else {
            tail.next = tail.next.next;
        }
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
        
        Node<E> node = tail;
        
        @Override
        public boolean hasNext() {
            return node.next != tail;
        }

        @Override
        public E next() {
            node = node.next;
            return node.data;
        }
        
    }
    
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
    
}
