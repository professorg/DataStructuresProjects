/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresprojects.nbodysimulation.types;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author gvandomelen19
 */
public class Stack<E> implements Cloneable, Iterable {

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
    
    public E pop(E data) {
        if (length < 1) throw new NoSuchElementException("Stack is empty");
        Node<E> current = head;
        while (current.next != null) {
            current = current.next;
        }
        --length;
        return current.data;
    }
    
    @Override
    public Iterator iterator() {
        return new StackIterator(this);
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length < 1;
    }
    
    private class Node<E> {
        
        private Node next;
        private E data;
        
        public Node(Node next, E data) {
            this.next = next;
            this.data = data;
        }
    }
    
    private class StackIterator<E> implements Iterator {

        Node<E> node;
        
        public StackIterator(Stack stack) {
            node = stack.head;
        }
        
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
