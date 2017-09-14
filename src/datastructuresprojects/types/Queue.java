/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresprojects.types;

import java.util.Iterator;

/**
 *
 * @author gvandomelen19
 */
public class Queue<E> implements Cloneable, Iterable {

    Node head;
    
    public Queue enqueue(E data) {
        if (head == null)
            head = new Node(null, data);
        else
            head.tail().next = new Node(null, data);
        return this;
    }
    
    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class Node<E> {
        
        private Node next;
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
