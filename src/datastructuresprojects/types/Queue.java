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
 * @param <E>
 */
public class Queue<E> implements Cloneable, Iterable<E> {

    private Node<E> head;
    private Node<E> tail;
    private int length;
    
    public Queue<E> enqueue(E data) {
        if (head == null) {
            head = new Node(null, data);
            tail = head;
        } else {
            tail.next = new Node(null, data);
            tail = tail.next;
        }
        ++length;
        return this;
    }
    
    public E dequeue() {
        if (length < 1) throw new NoSuchElementException("List is empty");
        --length;
        E ret = head.data;
        if (head == tail) tail = null;
        head = head.next;
        return ret;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }
    
    private class QueueIterator implements Iterator<E> {

        private Node<E> node = head;
        
        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public E next() {
            E ret = node.data;
            node = node.next;
            return ret;
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
