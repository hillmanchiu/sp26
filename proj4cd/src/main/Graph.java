package main;
import edu.princeton.cs.algs4.In;

import java.util.*;

public class Graph {

    //A Hashmap containing each synset and the hyponyms associated with that synset.
    private final HashMap<Integer, String> numberWords;
    private final HashMap<Integer, List<Integer>> numberHyponyms;
    private final HashMap<String, List<Integer>> wordSynsets = new HashMap<>();

    public Graph(String synsetsFilename, String hyponymFilename) {
        int arrayLength = Integer.parseInt(
                ((synsetsFilename.split("_"))[1].split("\\."))[0].split("e")[1]);
        numberWords = new HashMap<>(arrayLength);
        numberHyponyms = new HashMap<>(arrayLength);
        In in = new In(synsetsFilename);
        while (!in.isEmpty()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split(",");
            numberWords.put(Integer.parseInt(splitLine[0]), splitLine[1]);
            for (String indivWord : splitLine[1].split(" ")) {
                if (!wordSynsets.containsKey(indivWord)) {
                    List<Integer> synsetList = new ArrayList<>();
                    synsetList.add(Integer.parseInt(splitLine[0]));
                    wordSynsets.put(indivWord, synsetList);
                } else {
                    wordSynsets.get(indivWord).add(Integer.parseInt(splitLine[0]));
                }
            }
        }
        In in2 = new In(hyponymFilename);
        while (!in2.isEmpty()) {
            String thisLine = in2.readLine();
            String[] currentLine = thisLine.split(",");
            List<Integer> currentList = new ArrayList<>();
            int currentIndex = Integer.parseInt(currentLine[0]);
            for (int i = 1; i < currentLine.length; i++) {
                currentList.add(Integer.parseInt(currentLine[i]));
            }
            if (numberHyponyms.containsKey(currentIndex)) {
                currentList.addAll(numberHyponyms.get(currentIndex));
            }
            numberHyponyms.put(currentIndex, currentList);
        }
    }

    public List<String> iterativeHyponymsReturn(String word) {
        List<String> returnList = new ArrayList<>();
        List<Integer> returnSynsets = new ArrayList<>();
        List<Integer> currentHyponyms = new ArrayList<>();
        List<Integer> nextSynsets = new ArrayList<>();
        if (!wordSynsets.containsKey(word)) {
            return new ArrayList<>();
        }
        for (Integer currentSynset : wordSynsets.get(word)) {
            returnSynsets.add(currentSynset);
            if (numberHyponyms.containsKey(currentSynset)) {
                currentHyponyms.addAll(numberHyponyms.get(currentSynset));
                while (!currentHyponyms.isEmpty()) {
                    nextSynsets.clear();
                    for (Integer currentHyponym : currentHyponyms) {
                        if (numberHyponyms.containsKey(currentHyponym)) {
                            nextSynsets.addAll(numberHyponyms.get(currentHyponym));
                        }
                    }
                    returnSynsets.addAll(currentHyponyms);
                    currentHyponyms = new ArrayList<>(nextSynsets);
                }
            }
        }
        for (Integer numberSynset : returnSynsets) {
            for (String insertWord : (numberWords.get(numberSynset)).split(" ")) {
                if (!returnList.contains(insertWord)) {
                    returnList.add(insertWord);
                }
            }
        }
        return returnList;
    }

    public List<String> recursiveHyponymsReturn(String word) {
        List<String> returnList = new ArrayList<>();
        if (!wordSynsets.containsKey(word)) {
            return new ArrayList<>();
        }
        for (Integer currentSynset : wordSynsets.get(word)) {
            recursiveHelper(returnList, currentSynset);
        }
        return returnList;
    }

    public void recursiveHelper(List<String> inputList, Integer currentSynset) {
        for (String word : (numberWords.get(currentSynset)).split(" ")) {
            if (!inputList.contains(word)) {
                inputList.add(word);
            }
        }
        if (numberHyponyms.containsKey(currentSynset)) {
            for (Integer nextSynset : numberHyponyms.get(currentSynset)) {
                recursiveHelper(inputList, nextSynset);
            }
        }
    }

}
