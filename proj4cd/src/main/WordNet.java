package main;

import java.util.*;

public class WordNet {

    //There should be an instance variable that uses the files to generate a graph;
    private Graph instanceGraph;
    private NGramMap instanceMap;

    public WordNet(String synset, String hyponym, String wordHistoryfile, String yearHistoryfile) {
        instanceGraph = new Graph(synset, hyponym);
        instanceMap = new NGramMap(wordHistoryfile, yearHistoryfile);
    }

    public String findHyponymsGeneral(String word) {
        String[] splitword = word.split(",");
        List<String> returnList = instanceGraph.iterativeHyponymsReturn(splitword[0]);
        for (int i = 1; i < splitword.length; i++) {
            List<String> currentList = instanceGraph.iterativeHyponymsReturn(splitword[i]);
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

    public String findHyponymsGeneral2(String word, int k, int startYear, int endYear) {
        String[] splitword = word.split(",");
        List<String> returnList = instanceGraph.iterativeHyponymsReturn(splitword[0]);
        for (int i = 1; i < splitword.length; i++) {
            List<String> currentList = instanceGraph.iterativeHyponymsReturn(splitword[i]);
            int j = 0;
            while (j < returnList.size()) {
                if (!currentList.contains(returnList.get(j))) {
                    returnList.remove(returnList.get(j));
                    j--;
                }
                j++;
            }
        }
        if (k == 0) {
            Collections.sort(returnList);
            return returnList.toString();
        }

        //sort the hyponyms by popularity.
        HashMap<Integer, String> popularity = new HashMap<>();
        List<Integer> popularities = new ArrayList<>();
        for (String hyponym : returnList) {
            int sum = 0;
            for (Map.Entry<Integer, Double> currentYear : instanceMap.countHistory(hyponym,
                    startYear, endYear).entrySet()) {
                sum += currentYear.getValue();
            }
            popularity.put(sum, hyponym);
            popularities.add(sum);
        }
        Collections.sort(popularities);
        returnList.clear();
        for (int i = 0; i < k; i++) {
            returnList.add(popularity.get(popularities.get(popularities.size() - 1 - i)));
        }
        Collections.sort(returnList);
        return returnList.toString();
    }

}
