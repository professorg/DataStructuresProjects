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
public class Rational implements Iterable<Rational> {
    
    public final int numerator;
    public final int denominator;
    
    public Rational(int n, int d) {
        numerator = n;
        denominator = d;
    }

    @Override
    public Iterator<Rational> iterator() {
        return new RationalsIterator();
    }
    
    private class RationalsIterator implements Iterator<Rational> {
        
        private int n = 0;
        private int d = 1;

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Rational next() {
            Rational ret = new Rational(n, d);
            if (n == 0) {
                n++;
            }
            else {
                if (n == 1) {
                    n = d + 1;
                    d = 1;
                }
            }
            return new Rational(n, d);
        }
        
        
        
    }
    
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
    
}
