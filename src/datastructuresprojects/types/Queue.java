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
        head.tail().next = new Node(null, data);
        return this;
    }
    
    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
