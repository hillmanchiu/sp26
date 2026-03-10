package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import browser.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class HistoryHandler extends NgordnetQueryHandler {

    public static final int YEAR_1900 = 1900;
    public static final int YEAR_1950 = 1950;
    private NGramMap currentMap;

    public HistoryHandler(NGramMap ngm) {
        currentMap = ngm;
    }

    @Override
    public String handle(NgordnetQuery q) {
        String returnString;
        List<String> words = q.words();
        ArrayList<TimeSeries> lts = new ArrayList<>();
        for (String word : words) {
            lts.add(currentMap.weightHistory(word, YEAR_1900, YEAR_1950));
        }
        XYChart chart = Plotter.generateTimeSeriesChart(words, lts);
        returnString = Plotter.encodeChartAsString(chart);

        return returnString;
    }

}
