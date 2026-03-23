package main;
import edu.princeton.cs.algs4.In;

import java.util.*;

public class GraphArray {

    private final List<Integer>[] numberHyponyms;
    private final HashMap<String, List<Integer>> wordSynsets = new HashMap<>();
    private final String[] numberWords;

    public GraphArray(String synset, String hyponyms) {
        int arrayLength = Integer.parseInt(((synset.split("_"))[1].split("\\."))[0].split("e")[1]);
        numberHyponyms = new List[arrayLength];
        Arrays.fill(numberHyponyms, null);
        numberWords = new String[arrayLength];
        In in = new In(synset);
        while (!in.isEmpty()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split(",");
            int currentIndex = Integer.parseInt(splitLine[0]);
            numberWords[currentIndex] = splitLine[1];
            for (String indivWord : splitLine[1].split(" ")) {
                if (!wordSynsets.containsKey(indivWord)) {
                    List<Integer> synsetList = new ArrayList<>();
                    synsetList.add(currentIndex);
                    wordSynsets.put(indivWord, synsetList);
                } else {
                    wordSynsets.get(indivWord).add(currentIndex);
                }
            }
        }
        In in2 = new In(hyponyms);
        while (!in2.isEmpty()) {
            String thisLine = in2.readLine();
            String[] currentLine = thisLine.split(",");
            List<Integer> currentList = new ArrayList<>();
            if (numberHyponyms[Integer.parseInt(currentLine[0])] != null) {
                for (String stringHyponym : currentLine) {
                    numberHyponyms[Integer.parseInt(currentLine[0])].add(Integer.parseInt(stringHyponym));
                }
            } else {
                for (String stringHyponym : currentLine) {
                    currentList.add(Integer.parseInt(stringHyponym));
                }
                numberHyponyms[Integer.parseInt(currentLine[0])] = currentList;
            }
        }
    }

    public List<String> iterativeHyponymsReturn(String word) {
        List<String> returnSynsets = new ArrayList<>();
        List<Integer> storageList = new ArrayList<>();
        List<Integer> currentHyponyms = new ArrayList<>();
        List<Integer> nextSynsets = new ArrayList<>();
        if (!wordSynsets.containsKey(word)) {
            return new ArrayList<>();
        }
        for (int currentSynset : wordSynsets.get(word)) {
            storageList.add(currentSynset);
            if (numberHyponyms[currentSynset] != null) {
                currentHyponyms.addAll(numberHyponyms[currentSynset]);
                while (!currentHyponyms.isEmpty()) {
                    nextSynsets.clear();
                    for (int currentHyponym : currentHyponyms) {
                        if (numberHyponyms[currentHyponym] != null) {
                            nextSynsets.addAll(numberHyponyms[currentHyponym]);
                        }
                        storageList.addAll(currentHyponyms);
                        currentHyponyms = new ArrayList<>(nextSynsets);
                    }
                }
            }
        }
        for (int numberSynset : storageList) {
            for (String insertWord : (numberWords[numberSynset].split(" "))) {
                if (!returnSynsets.contains(insertWord)) {
                    returnSynsets.add(insertWord);
                }
            }
        }
        return returnSynsets;
    }
}
