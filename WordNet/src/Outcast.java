/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gvandomelen19
 */
public class Outcast {
    
    private final WordNet wordnet;
    
    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    } // constructor takes a WordNet object


    public String outcast(String[] nouns) {
        int shortestDistanceSum = -1;
        String outcast = null;
        for (String noun : nouns) {
            int sum = 0;
            for (String otherNoun : nouns) {
                sum += wordnet.distance(noun, otherNoun);
            }
            if (shortestDistanceSum < 0 || sum < shortestDistanceSum) {
                shortestDistanceSum = sum;
                outcast = noun;
            }
        }
        return outcast;
    } // given an array of WordNet nouns, return an outcast


    public static void main(String[] args) {
        // Empty
    } // see test client below
    
}
