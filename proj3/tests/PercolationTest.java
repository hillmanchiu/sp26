import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class PercolationTest {

    /**
     * Enum to represent the state of a cell in the grid. Use this enum to help you write tests.
     * <p>
     * (0) CLOSED: isOpen() returns true, isFull() return false
     * <p>
     * (1) OPEN: isOpen() returns true, isFull() returns false
     * <p>
     * (2) INVALID: isOpen() returns false, isFull() returns true
     *              (This should not happen! Only open cells should be full.)
     * <p>
     * (3) FULL: isOpen() returns true, isFull() returns true
     * <p>
     */
    private enum Cell {
        CLOSED, OPEN, INVALID, FULL
    }

    /**
     * Creates a Cell[][] based off of what Percolation p returns.
     * Use this method in your tests to see if isOpen and isFull are returning the
     * correct things.
     */
    private static Cell[][] getState(int N, Percolation p) {
        Cell[][] state = new Cell[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int open = p.isOpen(r, c) ? 1 : 0;
                int full = p.isFull(r, c) ? 2 : 0;
                state[r][c] = Cell.values()[open + full];
            }
        }
        return state;
    }

    @Test
    public void basicTest() {
        int N = 5;
        Percolation p = new Percolation(N);
        // open sites at (r, c) = (0, 1), (2, 0), (3, 1), etc. (0, 0) is top-left
        int[][] openSites = {
                {0, 1},
                {2, 0},
                {3, 1},
                {4, 1},
                {1, 0},
                {1, 1}
        };
        Cell[][] expectedState = {
                {Cell.CLOSED, Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED}
        };
        for (int[] site : openSites) {
            p.open(site[0], site[1]);
        }
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isFalse();
    }

    @Test
    public void oneByOneTest() {
        int N = 1;
        Percolation p = new Percolation(N);
        p.open(0, 0);
        Cell[][] expectedState = {
                {Cell.FULL}
        };
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isTrue();
    }

    @Test
    public void testConstructor() {
        //check that the constructor is initialized with zero open sites.
        Percolation test1 = new Percolation(100);
        assertThat(test1.numberOfOpenSites()).isEqualTo(0);

        //check that the constructor is initialized with zero full spots
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                assertThat(test1.isFull(i, j)).isEqualTo(false);
            }
        }
    }

    @Test
    public void testOpen() {
        //Test that opening up one spot works for a 1x1
        Percolation test1 = new Percolation(1);
        assertThat(test1.numberOfOpenSites()).isEqualTo(0);
        assertThat(test1.percolates()).isFalse();
        test1.open(0, 0);
        assertThat(test1.numberOfOpenSites()).isEqualTo(1);
        assertThat(test1.percolates()).isTrue();

        //Test that opening spots works for 10X10
        //automate the two tests above to fill the entire box row by row
        //Test that when you reach the last row
        Percolation test2 = new Percolation(10);
        assertThat(test2.numberOfOpenSites()).isEqualTo(0);
        assertThat(test2.percolates()).isFalse();

        int numberOpen = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                test2.open(i, j);
                numberOpen += 1;
                assertThat(test2.numberOfOpenSites()).isEqualTo(numberOpen);
                assertThat(test2.isOpen(i, j)).isTrue();
                assertThat(test2.percolates()).isFalse();
                assertThat(test2.isFull(i, j)).isTrue();
            }
        }
        test2.open(9, 0);
        numberOpen += 1;
        assertThat(test2.isFull(9, 0)).isTrue();
        assertThat(test2.percolates()).isTrue();
        assertThat(test2.isOpen(9, 0)).isTrue();
        assertThat(test2.numberOfOpenSites()).isEqualTo(numberOpen);

        //Test that opening the same spot twice does not do anything on the second time.
        test1.open(0, 0);
        assertThat(test1.numberOfOpenSites()).isEqualTo(1);
        assertThat(test1.percolates()).isTrue();

        //Test that using an index too small throws an exception
        test1.open(29, 50);
    }

    @Test
    public void testExceptions() {
        Percolation test1 = new Percolation(1);
        try {
            test1.open(0, -1);
            fail("This should throw an IllegalArgumentsException");
        } catch (IllegalArgumentException expected1) {
        }
        try {
            test1.open(10, 10);
            fail("This should throw an IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException expected2) {
        }
    }

}
