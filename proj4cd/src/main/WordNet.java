package main;

import java.util.*;

public class WordNet {

    //There should be an instance variable that uses the files to generate a graph;
    private Graph instanceGraph;

    public WordNet(String synset, String hyponym) {
        instanceGraph = new Graph(synset, hyponym);
    }

    public String findHyponymsGeneral(String word) {
        String[] splitword = word.split(",");
        List<String> returnList = instanceGraph.recursiveHyponymsReturn(splitword[0]);
        for (int i = 1; i < splitword.length; i++) {
            List<String> currentList = instanceGraph.recursiveHyponymsReturn(splitword[i]);
            int j = 0;
            while (j < returnList.size()) {
                if (!currentList.contains(returnList.get(j))) {
                    returnList.remove(returnList.get(j));
                    j--;
                }
                j++;
            }
        }
        Collections.sort(returnList);
        return returnList.toString();
    }

}
