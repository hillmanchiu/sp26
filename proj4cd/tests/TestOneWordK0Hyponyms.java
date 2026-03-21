import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import org.junit.jupiter.api.Test;
import main.AutograderBuddy;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Tests the most basic case for Hyponyms where the list of words is one word long, and k = 0.
 * The word history and year history files do not matter for the k==0 case, but are provided
 * as input for the constructor of the HyponymsHandler.
 */
public class TestOneWordK0Hyponyms {
    private static final String PREFIX = "./data/";

    /** NGrams Files */
    public static final String WORD_HISTORY_EECS_FILE = PREFIX + "word_history_eecs.csv";
    public static final String WORD_HISTORY_SIZE3_FILE = PREFIX + "word_history_size3.csv";
    public static final String WORD_HISTORY_SIZE4_FILE = PREFIX + "word_history_size4.csv";
    public static final String WORD_HISTORY_SIZE1291_FILE = PREFIX + "word_history_size1291.csv";
    public static final String WORD_HISTORY_SIZE14377_FILE = PREFIX + "word_history_size14377.csv";
    public static final String YEAR_HISTORY_FILE = PREFIX + "year_history.csv";

    /** Wordnet Files */
    public static final String SYNSETS_EECS_FILE = PREFIX + "synsets_eecs.txt";
    public static final String HYPONYMS_EECS_FILE = PREFIX + "hyponyms_eecs.txt";
    public static final String SYNSET_SIZE16_FILE = PREFIX + "synsets_size16.txt";
    public static final String HYPONYM_SIZE16_FILE = PREFIX + "hyponyms_size16.txt";
    public static final String SYNSET_SIZE1000_FILE = PREFIX + "synsets_size1000.txt";
    public static final String HYPONYM_SIZE1000_FILE = PREFIX +  "hyponyms_size1000.txt";

    @Test
    public void testActK0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORD_HISTORY_SIZE3_FILE, YEAR_HISTORY_FILE, SYNSET_SIZE16_FILE, HYPONYM_SIZE16_FILE);
        List<String> words = new ArrayList<>();
        words.add("act");

        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[act, action, change, demotion, human_action, human_activity, variation]";
        assertThat(actual).isEqualTo(expected);
    }

    // TODO: Add more unit tests (including edge case tests) here.

    @Test
    public void testActK2() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORD_HISTORY_SIZE3_FILE, YEAR_HISTORY_FILE, "./data/synsets_size82191.txt",
                "./data/hyponyms_size82191.txt");
        List<String> words = new ArrayList<>();
        words.add("cat");
        NgordnetQuery nq = new NgordnetQuery(words, 0, 0, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[Abyssinian, Abyssinian_cat, Acinonyx_jubatus, African_tea, Angora, Angora_cat, Arabian_tea, Bengal_tiger, Burmese_cat, Canada_lynx, Caterpillar, Egyptian_cat, European_wildcat, Felis_bengalensis, Felis_catus, Felis_chaus, Felis_concolor, Felis_domesticus, Felis_manul, Felis_ocreata, Felis_onca, Felis_pardalis, Felis_serval, Felis_silvestris, Felis_tigrina, Felis_wiedi, Felis_yagouaroundi, Lynx_canadensis, Lynx_caracal, Lynx_lynx, Lynx_pardina, Lynx_rufus, Maltese, Maltese_cat, Manx, Manx_cat, Pallas's_cat, Panthera_leo, Panthera_onca, Panthera_pardus, Panthera_tigris, Panthera_uncia, Persian_cat, Siamese, Siamese_cat, Smiledon_californicus, alley_cat, bay_lynx, big_cat, blue_point_Siamese, bobcat, bozo, caffer_cat, calico_cat, caracal, cat, cat-o'-nine-tails, catamount, catamountain, cheetah, chetah, common_lynx, cougar, desert_lynx, domestic_cat, eyra, false_saber-toothed_tiger, gib, guy, hombre, house_cat, jaguar, jaguarondi, jaguarundi, jaguarundi_cat, jungle_cat, kaffir_cat, kat, khat, king_of_beasts, kitty, kitty-cat, leopard, leopard_cat, leopardess, liger, lion, lion_cub, lioness, lionet, lynx, manul, margay, margay_cat, mountain_lion, mouser, ocelot, ounce, painter, panther, panther_cat, puma, puss, pussy, pussycat, qat, quat, queen, saber-toothed_tiger, sabertooth, sand_cat, serval, snow_leopard, sod, spotted_lynx, tabby, tabby_cat, tiger, tiger_cat, tiger_cub, tiglon, tigon, tigress, tom, tomcat, tortoiseshell, tortoiseshell-cat, true_cat, wildcat]";
        assertThat(actual).isEqualTo(expected);

    }

}
