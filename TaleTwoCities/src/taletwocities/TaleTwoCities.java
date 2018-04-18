/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taletwocities;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author gvandomelen19
 */
public class TaleTwoCities {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ST<String, Integer> st = new ST();
        String max = "";
        st.put(max, 0);
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString()
                    .replaceAll("[^a-zA-Z]", "")
                    .toLowerCase();
            // StdOut.printf("Processing: %s%n", s);
            if (s.length() < 10) continue;
            if (st.contains(s)) {
                int freq = st.get(s) + 1;
                if (freq > st.get(max)) {
                    max = s;
                }
                st.put(s, freq);
            } else {
                st.put(s, 1);
            }
        }
        StdOut.printf("%s : %d%n", max, st.get(max));
    }
    /*
    monseigneur : 103
    */
}
