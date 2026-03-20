package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import java.util.List;

public class HyponymsHandler extends NgordnetQueryHandler {

    WordNet currentMap;

    public HyponymsHandler(WordNet thisMap) {
        currentMap = thisMap;
    }

    public HyponymsHandler() {
        currentMap = new WordNet("./data/synsets_size82191.txt", "./data/hyponyms_size82191.txt");
    }

    @Override
    public String handle(NgordnetQuery q) {

        List<String> listWords = q.words();
        String returnString = "";
        for (int i = 0; i < listWords.size() - 1; i++) {
            returnString = returnString + listWords.get(i) + ",";
        }
        returnString = returnString + listWords.getLast();
        return currentMap.findHyponymsGeneral(returnString);
    }
}
