
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Topological;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gvandomelen19
 */
public class WordNet {
    
    private static final int SYNSET_ID = 0;
    private static final int HYPERNYM_ID = 0;
    private static final int SYNSET_NOUNS = 1;
    
    private final Set<String> nouns;
    private Digraph hypernymGraph;
    private String[] synsets;
    private SAP shortestAncestralPaths;
    private final Map<String, List<Integer>> wordIndices;
    private final Map<String, Integer> synsetIndices;
    
    // constructor takes the name of the two input files
    public WordNet(String synsetsFile, String hypernymsFile) {
        if (synsetsFile == null || hypernymsFile == null)
            throw new IllegalArgumentException();
        
        In synsetStream = new In(synsetsFile);
        String[] synsetStrings = synsetStream.readAllLines();
        
        int numSynsets = synsetStrings.length;
        String[] sets = new String[numSynsets];
        
        wordIndices = new HashMap<>();
        synsetIndices = new HashMap<>();
        nouns = new TreeSet<>();
        
        for (String synsetString : synsetStrings) {
            String[] synsetData = synsetString.split(",");
            int synsetID = Integer.parseInt(synsetData[SYNSET_ID]);
            String synsetNouns = synsetData[SYNSET_NOUNS];
            synsetIndices.put(synsetNouns, synsetID);
            sets[synsetID] = synsetNouns;
            for (String synsetNoun : synsetNouns.split(" ")) {
                List<Integer> indices = wordIndices.get(synsetNoun);
                if (indices == null) {
                    indices = new ArrayList<>();
                }
                indices.add(synsetID);
                nouns.add(synsetNoun);
                wordIndices.put(synsetNoun, indices);
            }
        }
        Digraph hypGraph = new Digraph(numSynsets);
        
        In hypernymStream = new In(hypernymsFile);
        String[] hypernymStrings = hypernymStream.readAllLines();
        
        for (String hypernymString : hypernymStrings) {
            String[] hypernymData = hypernymString.split(",");
            int hypernymID = Integer.parseInt(hypernymData[HYPERNYM_ID]);
            int numHypernyms = hypernymData.length;
            for (int i = HYPERNYM_ID + 1; i < numHypernyms; i++) {
                hypGraph.addEdge(hypernymID, Integer.parseInt(hypernymData[i]));
            }
        }
        
        Topological hypernymTopological = new Topological(hypGraph);
        
        if (hypernymTopological.hasOrder()) {
            int root = -1;
            for (int vertex : hypernymTopological.order()) {
                if (hypGraph.outdegree(vertex) == 0) {
                    if (root < 0)   root = vertex;
                    else            throw new IllegalArgumentException();
                }
            }
            hypernymGraph = hypGraph;
            synsets = sets;
        } else {
            throw new IllegalArgumentException();
        }
        
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nouns;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException();
        for (String noun : nouns()) {
            if (noun.equals(word)) return true;
        }
        return false;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (shortestAncestralPaths == null) {
            shortestAncestralPaths = new SAP(hypernymGraph);
        }
        return shortestAncestralPaths.length(wordIndices.get(nounA), wordIndices.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (shortestAncestralPaths == null) {
            shortestAncestralPaths = new SAP(hypernymGraph);
        }
        return synsets[shortestAncestralPaths.ancestor(wordIndices.get(nounA), wordIndices.get(nounB))];
    }

    // do unit testing of this class
    public static void main(String[] args) {
        // Empty
        WordNet wn = new WordNet("wordnet/synsets.txt", "wordnet/hypernyms.txt");
        System.out.println(wn.distance("indiscreetness", "Cardigan_Welsh_corgi"));
    }
}
