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
public class Stack<E> implements Cloneable, Iterable<E> {

    private Node<E> head;
    private int length;
    
    public Stack() {
        this.length = 0;
        this.head = null;
    }
    
    public Stack push(E data) {
        head = new Node(head, data);
        ++length;
        return this;
    }
    
    public E peek() {
        if (head == null) return null;
        return head.data;
    }
    
    public E pop() {
        if (length < 1) throw new NoSuchElementException("Stack is empty");
        if (head.next == null) {
            E ret = head.data;
            return ret;
        }
        Node<E> current = head;
        while (current.next != null) {
            current = current.next;
        }
        --length;
        return current.data;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length < 1;
    }
    
    private class Node<E> {
        
        public Node next;
        public E data;
        
        public Node(Node next, E data) {
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
    
    private class StackIterator implements Iterator<E> {

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
    
}
