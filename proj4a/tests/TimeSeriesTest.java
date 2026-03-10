import main.TimeSeries;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TimeSeriesTest {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>();
        expectedYears.add(1991);
        expectedYears.add(1992);
        expectedYears.add(1994);
        expectedYears.add(1995);

        assertThat(totalPopulation.years()).isEqualTo(expectedYears);

        List<Double> expectedTotal = new ArrayList<>();
        expectedTotal.add(0.0);
        expectedTotal.add(100.0);
        expectedTotal.add(600.0);
        expectedTotal.add(500.0);

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedTotal.get(i));
        }
    }

    @Test
    public void testEmptyBasic() {
        TimeSeries catPopulation = new TimeSeries();
        TimeSeries dogPopulation = new TimeSeries();

        assertThat(catPopulation.years()).isEmpty();
        assertThat(catPopulation.data()).isEmpty();

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);

        assertThat(totalPopulation.years()).isEmpty();
        assertThat(totalPopulation.data()).isEmpty();
    }

    @Test
    public void testDividedBy1() {
        //Check that DividedBy works as intended
        TimeSeries series1 = new TimeSeries();
        TimeSeries series2 = new TimeSeries();
        series1.put(1700, 1.0);
        series2.put(1700, 1.0);
        series1.put(1800, 4.0);
        series2.put(1800, 2.0);
        series1.put(1900, 9.0);
        series2.put(1900, 3.0);
        TimeSeries series3 = series1.dividedBy(series2);

        TimeSeries series4 = new TimeSeries();
        series4.put(1700, 1.0);
        series4.put(1800, 2.0);
        series4.put(1900, 3.0);

        assertThat(series3).isEqualTo(series4);
    }

    @Test
    public void testDividedBy2() {
        //Check that an IllegalArgumentsException gets thrown
        TimeSeries series1 = new TimeSeries();
        TimeSeries series2 = new TimeSeries();
        series1.put(1700, 1.0);
        series2.put(1700, 1.0);
        series1.put(1800, 4.0);
        series2.put(1800, 2.0);
        series1.put(1900, 9.0);
        series2.put(1900, 3.0);
        series2.put(1750, 3.0);
        try {
            series2.dividedBy(series1);
        } catch(IllegalArgumentException exception){}
    }

    @Test
    public void testDividedBy3() {
        //Check that if a value does not exist in this TimeSeries it is ignored
        TimeSeries series1 = new TimeSeries();
        TimeSeries series2 = new TimeSeries();
        series1.put(1700, 1.0);
        series2.put(1700, 1.0);
        series1.put(1800, 4.0);
        series2.put(1800, 2.0);
        series1.put(1900, 9.0);
        series2.put(1900, 3.0);
        series2.put(1750, 1.0);
        TimeSeries series3 = series1.dividedBy(series2);

        TimeSeries series4 = new TimeSeries();
        series4.put(1700, 1.0);
        series4.put(1800, 2.0);
        series4.put(1900, 3.0);

        assertThat(series3).isEqualTo(series4);
    }
} 