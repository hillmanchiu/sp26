import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import org.junit.jupiter.api.Test;
import main.AutograderBuddy;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Tests the case where the list of words is length greater than 1, but k is still zero.
 * The word history and year history files do not matter for the k==0 case, but are provided
 * as input for the constructor of the HyponymsHandler.
 */
public class TestMultiWordK0Hyponyms {
    private static final String PREFIX = "./data/";

    /**
     * NGrams Files
     */
    public static final String WORD_HISTORY_EECS_FILE = PREFIX + "word_history_eecs.csv";
    public static final String WORD_HISTORY_SIZE3_FILE = PREFIX + "word_history_size3.csv";
    public static final String WORD_HISTORY_SIZE4_FILE = PREFIX + "word_history_size4.csv";
    public static final String WORD_HISTORY_SIZE1291_FILE = PREFIX + "word_history_size1291.csv";
    public static final String WORD_HISTORY_SIZE14377_FILE = PREFIX + "word_history_size14377.csv";
    public static final String YEAR_HISTORY_FILE = PREFIX + "year_history.csv";

    /**
     * Wordnet Files
     */
    public static final String SYNSETS_EECS_FILE = PREFIX + "synsets_eecs.txt";
    public static final String HYPONYMS_EECS_FILE = PREFIX + "hyponyms_eecs.txt";
    public static final String SYNSET_SIZE16_FILE = PREFIX + "synsets_size16.txt";
    public static final String HYPONYM_SIZE16_FILE = PREFIX + "hyponyms_size16.txt";
    public static final String SYNSET_SIZE1000_FILE = PREFIX + "synsets_size1000.txt";
    public static final String HYPONYM_SIZE1000_FILE = PREFIX + "hyponyms_size1000.txt";


    /**
     * This is an example from the spec.
     */
    @Test
    public void testOccurrenceAndChangeK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORD_HISTORY_SIZE3_FILE, YEAR_HISTORY_FILE, SYNSET_SIZE16_FILE, HYPONYM_SIZE16_FILE);
        List<String> words = new ArrayList<>();
        words.add("change");
        words.add("occurrence");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[alteration, change, increase, jump, leap, modification, saltation, transition]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testMultipleWords() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORD_HISTORY_SIZE3_FILE, YEAR_HISTORY_FILE, "./data/synsets_size82191.txt",
                "./data/hyponyms_size82191.txt");
        List<String> words = new ArrayList<>();
        String expected2 = "[video, video_recording, videocassette, videotape]";
        String expected1 = "[apple_tart, lobster_tart, quiche, quiche_Lorraine, tart, tartlet]";
        words.add("pastry");
        words.add("tart");
        NgordnetQuery nq1 = new NgordnetQuery(words, 0, 0, 0);
        String actual1 = studentHandler.handle(nq1);
        assertThat(actual1).isEqualTo(expected1);

        List<String> word = new ArrayList<>();
        word.add("video");
        word.add("recording");
        NgordnetQuery nq2 = new NgordnetQuery(word, 0, 0, 0);
        String actual2 = studentHandler.handle(nq2);
        assertThat(actual2).isEqualTo(expected2);
    }

    @Test
    public void testTransition() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORD_HISTORY_SIZE3_FILE, YEAR_HISTORY_FILE, "./data/synsets_size82191.txt",
                "./data/hyponyms_size82191.txt");
        List<String> words = new ArrayList<>();
        String expected1 = "[changeover, conversion, cut, dissolve, flash-forward, flashback, fossilisation, fossilization, glycogenesis, ground_swell, isomerisation, isomerization, jump, jump_cut, leap, modulation, passage, quantum_jump, rectification, saltation, segue, transition]";
        words.add("transition");
        NgordnetQuery nq1 = new NgordnetQuery(words, 0, 0, 0);
        String actual1 = studentHandler.handle(nq1);
        assertThat(actual1).isEqualTo(expected1);
    }

    @Test
    public void testMultipleWords2() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORD_HISTORY_SIZE3_FILE, YEAR_HISTORY_FILE, "./data/synsets_size82191.txt",
                "./data/hyponyms_size82191.txt");
        List<String> words = new ArrayList<>();
        String expected2 = "[video, video_recording, videocassette, videotape]";
        String expected1 = "[apple_tart, lobster_tart, quiche, quiche_Lorraine, tart, tartlet]";
        words.add("pastry");
        words.add("tart");
        NgordnetQuery nq1 = new NgordnetQuery(words, 0, 0, 0);
        String actual1 = studentHandler.handle(nq1);
    }
}
