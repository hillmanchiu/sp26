package main;
import edu.princeton.cs.algs4.In;

import java.util.*;

public class Graph {

    //A Hashmap containing each synset and the hyponyms associated with that synset.
    private HashMap<String, String> numberWords = new HashMap<>();
    private HashMap<String, List<String>> numberHyponyms = new HashMap<>();

    public Graph(String synsetsFilename, String hyponymFilename) {
        In in = new In(synsetsFilename);
        while (!in.isEmpty()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split(",");
            numberWords.put(splitLine[0], splitLine[1]);
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
        for (Map.Entry<String, String> currentSynset : numberWords.entrySet()) {
            if (checkSynset(currentSynset.getValue(), word)) {
                returnSynsets.add(currentSynset.getKey());
                if (numberHyponyms.containsKey(currentSynset.getKey())) {
                    currentHyponyms.addAll(numberHyponyms.get(currentSynset.getKey()));
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
