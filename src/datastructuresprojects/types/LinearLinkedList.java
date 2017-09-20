/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresprojects.types;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author gvandomelen19
 */
public class LinearLinkedList<E> implements Cloneable, Iterable<E>, List<E> {

    Node<E> head;
    int length;
    
    public LinearLinkedList() {
        head = null;
        length = 0;
    }
    
    public LinearLinkedList addFirst(E data) {
        head = new Node(head, data);
        length++;
        return this;
    }
    
    @Override
    public boolean add(E data) {
        if (head == null)
            head = new Node(null, data);
        else
            head.tail().next = new Node(null, data);
        length++;
        return true;
    }
    
    public void addLast(E data) {
        if (head == null)
            head = new Node(null, data);
        else
            head.tail().next = new Node(null, data);
        length++;
    }
    
    public E removeFirst() {
        if (head != null) {
            E ret = head.data;
            head = head.next;
            return ret;
        }
        return null;
    }
    
    public E removeLast() {
        if (head == null) return null;
        if (head.next == null) {
            E ret = head.data;
            head = null;
            return ret;
        }
        Node<E> curr = head;
        while (curr.next.next != null) {
            curr = curr.next;
        }
        E ret = (E) curr.next.data;
        curr.next = null;
        return ret;
    }
    
    @Override
    public int size() {
        return length;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new LinearLinkedListIterator(this);
    }

    @Override
    public boolean isEmpty() {
        return length < 1;
    }

    @Override
    public boolean contains(Object o) {
        if (length < 1) return false;
        Node<E> next = head;
        while (next != null) {
            if (next.data.equals(o)) return true;
            next = next.next;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(Collection<? extends E> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addAll(int i, Collection<? extends E> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean retainAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        head = null;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E set(int i, E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(int i, E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E remove(int i) {
        Node<E> curr = head;
        for (int j = 1; j < i; j++) {
            if (curr.next != null)
                curr = curr.next;
            else
                throw new IndexOutOfBoundsException();
        }
        E data = (E) curr.next.data;
        curr.next = curr.next.next;
        return data;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<E> subList(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {
        String s = "[";
        for (E e : this) {
            s += e;
            s += ", ";
        }
        if (s.length() >= 2) s = s.substring(0, s.length()-2);
        s += "]";
        return s;
    }
    
    private class Node<F> {
        
        public Node next;
        public F data;
        
        public Node(Node next, F data) {
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

    
    private class LinearLinkedListIterator<F> implements Iterator<F> {

        private Node<F> node;
        
        private LinearLinkedListIterator(LinearLinkedList lll) {
            node = lll.head;
        }
        
        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public F next() {
            F ret = node.data;
            node = node.next;
            return ret;
        }
        
    }
    
}
