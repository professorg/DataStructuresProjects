/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresprojects.misc;

import datastructuresprojects.types.Rational;

/**
 *
 * @author gvandomelen19
 */
public class IteratorTest {
    
    public static void main(String[] args) {
        for (Rational r : new Rational(0,0)) {
            System.out.println(r);
        }
    }
    
}
