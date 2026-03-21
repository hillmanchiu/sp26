package main;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    //A Hashmap containing each synset and the hyponyms associated with that synset.
    private HashMap<String, String> numberWords;
    private HashMap<String, List<String>> numberHyponyms;
    private List<String> listToReturn;

    public Graph(String synsetsFilename, String hyponymFilename) {
        //Create a hashmap of each number and its associated synset
        listToReturn = new ArrayList<>();
        HashMap<String, String> numberWord = new HashMap<>();
        In in = new In(synsetsFilename);
        while (!in.isEmpty()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split(",");
            numberWord.put(splitLine[0], splitLine[1]);
        }
        numberWords = numberWord;
        //Create a hashmap associating each number and its hyponym numbers
        HashMap<String, List<String>> hyponymList = new HashMap<>();
        In in2 = new In(hyponymFilename);
        while (!in2.isEmpty()) {
            String thisLine = in2.readLine();
            String[] currentLine = thisLine.split(",");
            List<String> currentList = new ArrayList<>();
            for (int i = 1; i < currentLine.length; i++) {
                currentList.add(currentLine[i]);
            }
            if (hyponymList.containsKey(currentLine[0])) {
                for (String insert : (hyponymList.get(currentLine[0]))) {
                    currentList.add(insert);
                }
            }
            hyponymList.put(currentLine[0], currentList);
        }
        numberHyponyms = hyponymList;
    }

    public List<String> RecursiveReturnHyponyms(String word) {
        listToReturn = new ArrayList<>();
        //Check every synset in the database
        for (Map.Entry<String, String> currentSyn : numberWords.entrySet()) {
            //Check every word in the synset
            if (checkSynset(currentSyn.getValue(), word)) {
                //if the word is in the synset, add its words to the returnList.
                recursiveHelper(currentSyn.getKey());
            }
        }
        return listToReturn;
    }

    private void recursiveHelper(String synset) {
        //synset is the synset we are currently iterating over
        String[] synsetWords = numberWords.get(synset).split(" ");
        for (String wordinSynset : synsetWords) {
            if (!listToReturn.contains(wordinSynset)) {
                listToReturn.add(wordinSynset);
            }
        }
        // Iterate over every hyponym of the synset:
        if (!numberHyponyms.containsKey(synset)) {
            return;
        } else {
            for (String currentSynset : numberHyponyms.get(synset)) {
                recursiveHelper(currentSynset);
            }
        }
    }

    private boolean checkSynset(String synset, String word) {
        //splits a synset into a list of strings
        if (synset.equals(word)) {
            return true;
        }
        for (String wordin: synset.split(" ")) {
            if (wordin.equals(word)) {
                return true;
            }
        }
        return false;
    }
}
