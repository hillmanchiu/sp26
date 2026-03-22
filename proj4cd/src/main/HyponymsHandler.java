package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import java.util.List;

public class HyponymsHandler extends NgordnetQueryHandler {

    WordNet currentNet;

    public HyponymsHandler(WordNet thisNet) {
        currentNet = thisNet;

    }

    public HyponymsHandler() {
        currentNet = new WordNet("./data/synsets_size82191.txt", "./data/hyponyms_size82191.txt",
                "./data/word_history_size14377.csv", "./data/year_history.csv");
    }

    @Override
    public String handle(NgordnetQuery q) {

        List<String> listWords = q.words();
        String returnString = "";
        for (int i = 0; i < listWords.size() - 1; i++) {
            returnString = returnString + listWords.get(i) + ",";
        }
        returnString = returnString + listWords.getLast();
        return currentNet.findHyponymsGeneral(returnString);
    }
}
