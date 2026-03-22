package main;
import edu.princeton.cs.algs4.In;

import java.util.*;

public class Graph {

    //A Hashmap containing each synset and the hyponyms associated with that synset.
    private HashMap<String, String> numberWords = new HashMap<>();
    private HashMap<String, List<String>> numberHyponyms = new HashMap<>();
    private HashMap<String, List<String>> wordSynsets = new HashMap<>();

    public Graph(String synsetsFilename, String hyponymFilename) {
        In in = new In(synsetsFilename);
        while (!in.isEmpty()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split(",");
            numberWords.put(splitLine[0], splitLine[1]);
            for (String indivWord : splitLine[1].split(" ")) {
                if (!wordSynsets.containsKey(indivWord)) {
                    List<String> synsetList = new ArrayList<>();
                    synsetList.add(splitLine[0]);
                    wordSynsets.put(indivWord, synsetList);
                } else {
                    wordSynsets.get(indivWord).add(splitLine[0]);
                }
            }
        }
        In in2 = new In(hyponymFilename);
        while (!in2.isEmpty()) {
            String thisLine = in2.readLine();
            String[] currentLine = thisLine.split(",");
            List<String> currentList = new ArrayList<>(Arrays.asList(currentLine).subList(1, currentLine.length));
            if (numberHyponyms.containsKey(currentLine[0])) {
                currentList.addAll((numberHyponyms.get(currentLine[0])));
            }
            numberHyponyms.put(currentLine[0], currentList);
        }
    }


    private boolean checkSynset(String synset, String word) {
        //splits a synset into a list of strings
        for (String wordin: synset.split(" ")) {
            if (wordin.equals(word)) {
                return true;
            }
        }
        return false;
    }

    public List<String> iterativeHyponymsReturn(String word) {
        List<String> returnSynsets = new ArrayList<>();
        List<String> currentHyponyms = new ArrayList<>();
        List<String> nextSynsets = new ArrayList<>();
        for (String currentSynset : wordSynsets.get(word)) {
            returnSynsets.add(currentSynset);
            if (numberHyponyms.containsKey(currentSynset)) {
                currentHyponyms.addAll(numberHyponyms.get(currentSynset));
                while (!currentHyponyms.isEmpty()) {
                    nextSynsets.clear();
                    for (String currentHyponym : currentHyponyms) {
                        if (numberHyponyms.containsKey(currentHyponym)) {
                            nextSynsets.addAll(numberHyponyms.get(currentHyponym));
                        }
                    }
                    returnSynsets.addAll(currentHyponyms);
                    currentHyponyms = new ArrayList<>(nextSynsets);
                }
            }
        }
        for (String numberSynset : returnSynsets) {
            for (String insertWord : (numberWords.get(numberSynset)).split(" ")) {
                if (!currentHyponyms.contains(insertWord)) {
                    currentHyponyms.add(insertWord);
                }
            }
        }
        return currentHyponyms;
    }

}
