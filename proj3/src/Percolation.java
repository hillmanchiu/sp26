import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF waterpath;
    private WeightedQuickUnionUF waterpath2;
    private boolean[][] grid;
    private int gridLength;
    private int squaresFilled;

    public Percolation(int N) {
        waterpath = new WeightedQuickUnionUF(N * N + 2);
        waterpath2 = new WeightedQuickUnionUF(N * N + 1);
        grid = new boolean[N][N];
        gridLength = N;
        squaresFilled = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
    }

    public void open(int row, int col) {
        if (row >= gridLength || col >= gridLength) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (row < 0 || col < 0) {
            throw new java.lang.IllegalArgumentException();
        }
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = true;
        squaresFilled += 1;
        int square1 = xyToIndex(row, col);
        int square2;
        if (squaresFilled > 1) {
            //connect the squares together
            if (checkIfExists(row + 1, col)) {
                if (isOpen(row + 1, col)) {
                    square2 = xyToIndex(row + 1, col);
                    waterpath.union(square1, square2);
                    waterpath2.union(square1, square2);
                }
            }
            if (checkIfExists(row - 1, col)) {
                if (isOpen(row - 1, col)) {
                    square2 = xyToIndex(row - 1, col);
                    waterpath.union(square1, square2);
                    waterpath2.union(square1, square2);
                }
            }
            if (checkIfExists(row, col + 1)) {
                if (isOpen(row, col + 1)) {
                    square2 = xyToIndex(row, col + 1);
                    waterpath.union(square1, square2);
                    waterpath2.union(square1, square2);
                }
            }
            if (checkIfExists(row, col - 1)) {
                if (isOpen(row, col - 1)) {
                    square2 = xyToIndex(row, col - 1);
                    waterpath.union(square1, square2);
                    waterpath2.union(square1, square2);
                }
            }
        }
        if (square1 < gridLength && square1 >= 0) {
            waterpath.union(square1, gridLength * gridLength);
            waterpath2.union(square1, gridLength * gridLength);
        }
        if (square1 < (gridLength * gridLength) && square1 >= (gridLength * (gridLength - 1))) {
            waterpath.union(square1, gridLength * gridLength + 1);
        }
    }

    public int xyToIndex(int row, int col) {
        if (row >= gridLength || col >= gridLength) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (row < 0 || col < 0) {
            throw new java.lang.IllegalArgumentException();
        }
        return (row * gridLength) + col;
    }

    public boolean checkIfExists(int row, int col) {
        boolean returnBoolean = true;
        if ((row < 0 || row >= gridLength) || (col < 0 || col >= gridLength)) {
            returnBoolean =  false;
        }
        return returnBoolean;
    }

    public boolean isOpen(int row, int col) {
        if (row >= gridLength || col >= gridLength) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (row < 0 || col < 0) {
            throw new java.lang.IllegalArgumentException();
        }
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row >= gridLength || col >= gridLength) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (row < 0 || col < 0) {
            throw new java.lang.IllegalArgumentException();
        }
        int checkIndex = xyToIndex(row, col);
        return waterpath2.connected(checkIndex, gridLength * gridLength);
    }

    public int numberOfOpenSites() {
        return squaresFilled;
    }

    public boolean percolates() {
        return waterpath.connected(gridLength * gridLength, gridLength * gridLength + 1);
    }

}
