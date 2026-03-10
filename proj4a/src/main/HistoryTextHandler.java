package main;

import browser.NgordnetQuery;
import demo.DummyHistoryTextHandler;

import java.util.List;

public class HistoryTextHandler extends DummyHistoryTextHandler {

    NGramMap currentMap;

    public HistoryTextHandler(NGramMap ngm) {
        currentMap = ngm;
    }

    @Override
    public String handle(NgordnetQuery q) {
        //initialize the return String
        String returnString;
        String currentChunk = "";
        //Iterate over all the necessary words
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        for (String currentWord : words) {
            //access the weighted popularity TimeSeries of the specified word.
            TimeSeries currentSeries = currentMap.weightHistory(currentWord, startYear, endYear);
            //check if the currentWord exists in currentSeries
            returnString = currentWord + ": " + currentSeries.toString();
            String checkString = currentWord + ": ";
            if (!returnString.equals(checkString)) {
                currentChunk = currentChunk + returnString + "\n";
            }
        }
        return currentChunk;
    }
}
