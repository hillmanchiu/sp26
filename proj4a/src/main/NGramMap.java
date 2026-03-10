package main;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.HashMap;

import static main.TimeSeries.MAX_YEAR;
import static main.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    private TimeSeries wordsYear;
    private HashMap<String, TimeSeries> wordFrequency;

    /**
     * Constructs an NGramMap from WORDHISTORYFILENAME and YEARHISTORYFILENAME.
     */
    public NGramMap(String wordHistoryFilename, String yearHistoryFilename) {
        wordsYearMaker(yearHistoryFilename);
        wordFrequencyMaker2(wordHistoryFilename);
    }

    private void wordFrequencyMaker2(String wordHistoryFilename) {
        wordFrequency = new HashMap<>();
        In in = new In(wordHistoryFilename);
        TimeSeries currentSeries = new TimeSeries();
        String currentWord;
        while (!in.isEmpty()) {
            String currentLine = in.readLine();
            String[] splitLine = (currentLine.split("\t"));
            currentWord = splitLine[0];
            if (!wordFrequency.containsKey(currentWord)) {
                currentSeries = new TimeSeries();
            }
            currentSeries.put(Integer.parseInt(splitLine[1]), Double.parseDouble(splitLine[2]));
            wordFrequency.put(currentWord, currentSeries);
        }
    }

    private void wordsYearMaker(String yearHistoryFilename) {
        wordsYear = new TimeSeries();
        In in = new In(yearHistoryFilename);
        int i = 0;
        while (!in.isEmpty()) {
            i += 1;
            String[] splitLine = (in.readLine()).split(",");
            wordsYear.put(Integer.parseInt(splitLine[0]), Double.parseDouble(splitLine[1]));
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        if (!wordFrequency.containsKey(word)) {
            return new TimeSeries();
        } else {
            return new TimeSeries(wordFrequency.get(word), startYear, endYear);
        }
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        return countHistory(word, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return wordsYear;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        if (!wordFrequency.containsKey(word)) {
            return new TimeSeries();
        } else {
            return new TimeSeries((wordFrequency.get(word)).dividedBy(wordsYear), startYear, endYear);
        }
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        return weightHistory(word, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries returnSeries = new TimeSeries();
        for (String word : words) {
            if (wordFrequency.containsKey(word)) {
                returnSeries = returnSeries.plus(wordFrequency.get(word));
            }
        }
        return new TimeSeries(returnSeries.dividedBy(wordsYear), startYear, endYear);
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        return summedWeightHistory(words, MIN_YEAR, MAX_YEAR);
    }

}
