import main.Graph;
import main.WordNet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

public class TestGraphandWordNet {
    @Test
    public void testConstructors() {
        //test that the graph constructor makes something
        Graph testGraph = new Graph(TestOneWordK0Hyponyms.SYNSET_SIZE16_FILE,
                TestOneWordK0Hyponyms.HYPONYM_SIZE16_FILE);
        assertThat(testGraph).isNotEqualTo(null);

        //test that the WordNet constructor makes something and it is equivalent to the graph constructor
        WordNet testWordNet = new WordNet(
                TestOneWordK0Hyponyms.SYNSET_SIZE16_FILE, TestOneWordK0Hyponyms.HYPONYM_SIZE16_FILE,
                "./data/word_history_size14377.csv", "./data/year_history.csv");
        List<String> testGraphList = testGraph.iterativeHyponymsReturn("event");
        //List<String> testWordNetList = testWordNet.instanceGraph.returnHyponyms("event");
        //assertThat(testGraphList).isEqualTo(testWordNetList);
    }

    @Test
    public void testreturnHyponymsGeneral() {
        //test that returnhyponyms works when there is one string in the input.
        WordNet testWordNet = new WordNet(
                TestOneWordK0Hyponyms.SYNSET_SIZE16_FILE, TestOneWordK0Hyponyms.HYPONYM_SIZE16_FILE,
                "./data/word_history_size25.csv", "./data/year_history.csv");
        String testString = "[alteration, change, demotion, increase, jump, leap, modification, saltation, " +
                "transition, variation]";
        assertThat(testWordNet.findHyponymsGeneral("change")).isEqualTo(testString);

        //second test if returnhyponyms works when there is one string in the input.
        WordNet testWordNet2 = new WordNet("./data/synsets_size11.txt", "./data/hyponyms_size11.txt",
                "./data/word_history_size25.csv", "./data/year_history.csv");
        String testString2 = "[descent, jump, parachuting]";
        assertThat(testWordNet2.findHyponymsGeneral("descent")).isEqualTo(testString2);
    }

    @Test
    public void testreturnHyponymsMultiple() {
        //Test if returnHyponymsGeneral works with more than one word arguments.
        WordNet testWordNet = new WordNet(
                TestOneWordK0Hyponyms.SYNSET_SIZE16_FILE, TestOneWordK0Hyponyms.HYPONYM_SIZE16_FILE,
                "./data/word_history_size25.csv", "./data/year_history.csv");
        String testString = "[alteration, change, increase, jump, leap, modification, saltation, transition]";
        assertThat(testWordNet.findHyponymsGeneral("occurrence,change")).isEqualTo(testString);
    }

}