package main;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    //A Hashmap containing each synset and the hyponyms associated with that synset.
    private HashMap<String, List<String>> synSets = new HashMap<>();
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
        //Create a hashmap associating each number and its hyponym numbers
        HashMap<String, List<String>> hyponymList = new HashMap<>();
        In in2 = new In(hyponymFilename);
        while (!in2.isEmpty()) {
            String thisLine = in2.readLine();
            String[] currentLine = thisLine.split(",");
            List<String> currentList = new ArrayList<>();
            if (!hyponymList.containsKey(currentLine[0])) {
                for (int i = 1; i < currentLine.length; i++) {
                    currentList.add(currentLine[i]);
                }
                hyponymList.put(currentLine[0], currentList);
            } else {
                for (int i = 1; i < currentLine.length; i++) {
                    (hyponymList.get(currentLine[0])).add(currentLine[i]);
                }
            }
        }
        //Translate hyponymList into Strings rather than numbers
        for (Map.Entry<String, String> currentMap : numberWord.entrySet()) {
            List<String> insertList = new ArrayList<>();
            String getKey = currentMap.getKey();
            if (hyponymList.containsKey(getKey)) {
                for (String insertString : hyponymList.get(getKey)) {
                    insertList.add(numberWord.get(insertString));
                }
                synSets.put(currentMap.getValue(), insertList);
            } else {
                synSets.put(currentMap.getValue(), null);
            }
        }
        //At this point you should have associated every synset to its hyponym synsets.
    }

    public List<String> returnHyponyms(String word) {
        listToReturn = new ArrayList<>();
        //Check every synset in the database
        for (Map.Entry<String, List<String>> currentSyn : synSets.entrySet()) {
            //Check every word in the synset
            if (checkSynset(currentSyn.getKey(), word)) {
                //if the word is in the synset, add its words to the returnList.
                recursiveHelper(currentSyn.getKey());
            }
        }
        return listToReturn;
    }

    private void recursiveHelper(String synset) {
        //synset is the synset we are currently iterating over
        String[] synsetWords = synset.split(" ");
        for (String wordinSynset : synsetWords) {
            if (!listToReturn.contains(wordinSynset)) {
                listToReturn.add(wordinSynset);
            }
        }
        // Iterate over every hyponym of the synset:
        if (synSets.get(synset) != null) {
            for (String currentSynset : synSets.get(synset)) {
                recursiveHelper(currentSynset);
            }
        }
    }

    private boolean checkSynset(String synset, String word) {
        //splits a synset into a list of strings
        String[] synsetWords = synset.split(" ");
        for (String wordin: synsetWords) {
            if (wordin.equals(word)) {
                return true;
            }
        }
        return false;
    }

}
