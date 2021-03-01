import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private int[] id;
    private final int size;
    private int sumOpen = 0;
    private final WeightedQuickUnionUF uf;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException(" n < or == 0 ");
        this.size = n;
        this.id = new int[n * n + 2];
        this.uf = new WeightedQuickUnionUF(n * n + 2);
        id[0] = 0;
        id[n * n + 1] = n * n + 1;
        for (int i = 1; i < id.length - 1; i++) {
            id[i] = -1;
        }
    }

    private int xyTo1D(int row, int col) {
        if (col < 1 || col > this.size) return -1;
        if (row == 0) return 0;
        if (row == (size + 1)) return id.length - 1;
        int temp = row * size - (size - col);
        if (temp < 0 || temp >= id.length) return -1;
        return temp;
    }

    public void open(int row, int col) {
        if (row < 1 || row > this.size || col < 1 || col > this.size)
            throw new IllegalArgumentException("row or col is not between 1...n.");
        if (isOpen(row, col)) return;
        int coordOpen = xyTo1D(row, col);
        int upCell = xyTo1D(row - 1, col);
        int rightCell = xyTo1D(row, col + 1);
        int downCell = xyTo1D(row + 1, col);
        int leftCell = xyTo1D(row, col - 1);
        id[coordOpen] = 1;
        sumOpen++;
        if (upCell != -1) {
            if (this.id[upCell] != -1)
                uf.union(upCell, coordOpen);
        }
        if (rightCell != -1) {
            if (this.id[rightCell] != -1)
                uf.union(rightCell, coordOpen);
        }
        if (downCell != -1) {
            if (this.id[downCell] != -1)
                uf.union(downCell, coordOpen);
        }
        if (leftCell != -1) {
            if (this.id[leftCell] != -1)
                uf.union(leftCell, coordOpen);
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || row > this.size || col < 1 || col > this.size)
            throw new IllegalArgumentException("row or col is not between 1...n.");
        return !(this.id[xyTo1D(row, col)] == -1);

    }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > this.size || col < 1 || col > this.size)
            throw new IllegalArgumentException("row or col is not between 1...n.");
        return uf.find(0) == uf.find(xyTo1D(row, col));
    }

    public int numberOfOpenSites() {
        return sumOpen;
    }

    public boolean percolates() {
        return uf.find(0) == uf.find(this.id.length - 1);
    }


    public static void main(String[] args) {
    }
}
