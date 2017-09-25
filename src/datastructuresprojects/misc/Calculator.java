/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresprojects.misc;

import datastructuresprojects.types.Stack;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;

/**
 *
 * @author gvandomelen19
 */
public class Calculator {
    
    public static void main(String[] args) {
        
        Stack<Double> nums = new Stack();
        Stack<Integer> operators = new Stack();
        
        String input = "(1+1)";
        String currNum = "";
        
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (currNum.length() > 0 && !"0123456789.".contains(c + "")) {
                nums.push(Double.parseDouble(currNum));
                currNum = "";
            }
            if ("0123456789.".contains(c + "")) {
                currNum += c;
            }
            int o = "+-*/".indexOf(c + "");
            if (o >= 0) operators.push(o);
            if (c == ')') {
                int op;
                double n1;
                double n2;
                try {
                    op = operators.pop();
                } catch (NoSuchElementException e) {
                    throw new IllegalArgumentException("Not enough operators");
                }
                try {
                    n1 = nums.pop();
                    n2 = nums.pop();
                } catch (NoSuchElementException e) {
                    throw new IllegalArgumentException("Not enough operands");
                }
                switch (op) {
                    case 0:
                        nums.push(n1 + n2);
                        break;
                    case 1:
                        nums.push(n1 - n2);
                        break;
                    case 2:
                        nums.push(n1 * n2);
                        break;
                    case 3:
                        nums.push(n1 / n2);
                        break;
                }
            }
        }
        
        if (currNum.length() > 0)
            throw new IllegalArgumentException("Misplaced number at end of string");
        if (!operators.isEmpty())
            throw new IllegalArgumentException("Too many operators");
        if (nums.size() > 1)
            throw new IllegalArgumentException("Too many operands");
        if (nums.size() < 1)
            throw new IllegalArgumentException("No result");
        
        StdOut.println(nums.pop());
        
    }
    
}
