package dsa;

public class UnionFind {

  private final int[] parent, rank;
  private int count;

  // Constructor to create and initialize the DSU
  public UnionFind(int size) {
    count = size;
    parent = new int[size];
    rank = new int[size];
    for (int i = 0; i < size; i++) {
      parent[i] = i; // Initially, each element is its own parent
    }
  }

  // Find the root of the set in which element 'x' is present
  public int find(int x) {
    if (parent[x] != x) {
      parent[x] = find(parent[x]); // Path compression
    }
    return parent[x];
  }

  // Perform the union of two sets, one containing 'x' and the other containing 'y'
  public void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);
    if (rootX != rootY) {
      // Attach the smaller rank tree under the root of the higher rank tree
      if (rank[rootX] < rank[rootY]) {
        parent[rootX] = rootY;
      } else if (rank[rootX] > rank[rootY]) {
        parent[rootY] = rootX;
      } else {
        // If ranks are the same, make one as root and increment its rank by one
        parent[rootY] = rootX;
        rank[rootX]++;
      }
      count--;
    }
  }

  public int count() {
    return count;
  }

  public static void main(String[] args) {
    int size = 10; // Number of elements
    UnionFind uf = new UnionFind(size);

    // Perform some union operations
    uf.union(1, 2);
    uf.union(2, 3);
    uf.union(4, 5);
    uf.union(6, 7);
    uf.union(5, 6);
    uf.union(3, 7);

    // Find the root of certain elements
    System.out.println("Root of 1: " + uf.find(1));
    System.out.println("Root of 5: " + uf.find(5));
    System.out.println("Root of 8: " + uf.find(8));
  }
}

