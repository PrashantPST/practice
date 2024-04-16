package dsa;

import dsa.models.NodeColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphProblems {

  private final static int[][] coordinates = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
  private int SIZE;
  private boolean end;

  public void dfsForNumberOfConnectedComponents(int vertex, int[][] adjMatrix, boolean[] visited) {
    visited[vertex] = true;
    for (int i = 0; i < adjMatrix.length; i++) {
      if (adjMatrix[vertex][i] == 1 && !visited[i]) {
        dfsForNumberOfConnectedComponents(i, adjMatrix, visited);
      }
    }
  }

  public int numberOfConnectedComponents(int[][] adjMatrix) {
    int n = adjMatrix.length;
    int numberOfComponents = 0;
    boolean[] visited = new boolean[n];
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        numberOfComponents++;
        dfsForNumberOfConnectedComponents(i, adjMatrix, visited);
      }
    }
    return numberOfComponents;
  }

  public boolean cycleInADirectedGraph(int[][] edges) {
    int nVertices = edges.length;
    NodeColor[] arr = new NodeColor[nVertices];
    Arrays.fill(arr, NodeColor.WHITE);
    for (int i = 0; i < nVertices; i++) {
      if (hasCycleInDirectedGraph(edges, i, arr)) {
        return true;
      }
    }
    return false;
  }

  private boolean hasCycleInDirectedGraph(int[][] edges, int i, NodeColor[] arr) {
    arr[i] = NodeColor.GREY;
    for (int v : edges[i]) {
      if (arr[v] == NodeColor.GREY) {
        return true;
      }
      if (hasCycleInDirectedGraph(edges, v, arr)) {
        return true;
      }
    }
    arr[i] = NodeColor.BLACK;
    return false;
  }

  public boolean isValidTree(int n, int[][] edges) {
    List<List<Integer>> adjacencyList = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adjacencyList.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      adjacencyList.get(u).add(v);
      adjacencyList.get(v).add(u);
    }
    int[] visited = new int[n];
    if (hasCycleInUndirectedGraph(adjacencyList, 0, visited, -1)) {
      return false;
    }
    return SIZE == n;
  }

  private boolean hasCycleInUndirectedGraph(List<List<Integer>> adjacencyList, int currentNode,
      int[] visited,
      int parent) {
    visited[currentNode] = 1;
    for (int neighbor : adjacencyList.get(currentNode)) {
      if (neighbor == parent) {
        continue;
      }
      if (visited[neighbor] == 1) {
        return true; // Cycle detected
      }
      if (hasCycleInUndirectedGraph(adjacencyList, neighbor, visited, currentNode)) {
        return true;
      }
    }
    visited[currentNode] = 2;
    SIZE++;
    return false;
  }


  /**
   * A binary grid, which represents '1's (land) and '0's (water), return the number of islands. An
   * island is surrounded by water and is formed by connecting adjacent lands horizontally or
   * vertically TC - O(MN) SC - O(MN)
   */
  public static int numberOfIslands(int[][] grid) {
    int count = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1) {
          dfsForNumberOfIslands(grid, i, j);
          count++;
        }
      }
    }
    return count;
  }

  private static void dfsForNumberOfIslands(int[][] grid, int i, int j) {
    if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && grid[i][j] == 1) {
      grid[i][j] = 0;
      for (int[] coordinate : coordinates) {
        dfsForNumberOfIslands(grid, i + coordinate[0], j + coordinate[1]);
      }
    }
  }

  /**
   * Calculates the number of islands formed after each addLand operation Initially, the grid is
   * filled with water. An addLand operation turns the water at (row, col) into land. An island is
   * formed by connecting adjacent lands horizontally or vertically and is surrounded by water.
   *
   * @param rows      the number of rows in the grid.
   * @param cols      the number of columns in the grid.
   * @param positions a list of positions, where each position is an array of two integers
   *                  representing the row and column indices to perform addLand operations.
   * @return a list of integers, where each integer represents the number of islands formed after
   * each corresponding addLand operation. Example: Input: m = 3, n = 3, positions = [[0,0], [0,1],
   * [1,2], [2,1]] Output: [1, 1, 2, 3] Here, after each addLand operation, the number of islands
   * changes as lands are added. The grid evolves with each operation, and the output array reflects
   * the number of islands at each stage.
   */
  public List<Integer> countIslandsAfterAddLand(int rows, int cols, int[][] positions) {
    UnionFind uf = new UnionFind(rows * cols);
    boolean[][] land = new boolean[rows][cols];
    List<Integer> result = new ArrayList<>();
    int count = 0;
    for (int[] op : positions) {
      int r = op[0];
      int c = op[1];
      int index = r * cols + c;
      if (!land[r][c]) {
        land[r][c] = true;
        count++; // New land potentially forms a new island
        for (int[] dir : coordinates) {
          int nr = r + dir[0];
          int nc = c + dir[1];
          int newIndex = nr * cols + nc;
          if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && land[nr][nc]) {
            if (uf.find(index) != uf.find(newIndex)) {
              uf.union(index, newIndex);
              count--; // Merge islands
            }
          }
        }
      }
      result.add(count);
    }
    return result;
  }

  public int maxAreaOfIsland(int[][] grid) {
    int maxArea = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        maxArea = Math.max(maxArea, maxAreaOfIsland(grid, i, j));
      }
    }
    return maxArea;
  }

  private int maxAreaOfIsland(int[][] grid, int r, int c) {
    if (r < 0 || c < 0 ||
        r == grid.length ||
        c == grid[0].length ||
        grid[r][c] == 0) {
      return 0;
    }
    grid[r][c] = 0;
    int local = 0;
    for (int[] coordinate : coordinates) {
      local += maxAreaOfIsland(grid, r + coordinate[0], c + coordinate[1]);
    }
    return local + 1;
  }

  public int orangesRotting(int[][] grid) {
    int freshOranges = 0;
    Queue<int[]> rottenQueue = new LinkedList<>();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 2) {
          rottenQueue.add(new int[]{i, j});
        } else if (grid[i][j] == 1) {
          freshOranges++;
        }
      }
    }
    if (freshOranges == 0) {
      return 0; // No fresh oranges to rot
    }
    int minutes = 0;
    while (!rottenQueue.isEmpty() && freshOranges > 0) {
      int size = rottenQueue.size();
      for (int i = 0; i < size; i++) {
        int[] current = rottenQueue.poll();
        for (int[] direction : coordinates) {
          assert current != null;
          int newRow = current[0] + direction[0];
          int newCol = current[1] + direction[1];
          if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length &&
              grid[newRow][newCol] == 1) {
            grid[newRow][newCol] = 2;
            rottenQueue.add(new int[]{newRow, newCol});
            freshOranges--;
          }
        }
      }
      minutes++;
    }
    return freshOranges == 0 ? minutes : -1;
  }

  public int[][] updateMatrix(int[][] mat) {
    int rows = mat.length;
    int cols = mat[0].length;
    int[][] result = new int[rows][cols];
    Queue<int[]> queue = new LinkedList<>();
    // Initialize the result matrix with maximum values and enqueue '0' cells
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (mat[i][j] == 0) {
          result[i][j] = 0;
          queue.offer(new int[]{i, j});
        } else {
          result[i][j] = Integer.MAX_VALUE;
        }
      }
    }
    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int row = cell[0];
      int col = cell[1];
      for (int[] dir : coordinates) {
        int newRow = row + dir[0];
        int newCol = col + dir[1];
        if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
          // Check if the new distance is shorter
          if (result[newRow][newCol] > result[row][col] + 1) {
            result[newRow][newCol] = result[row][col] + 1;
            queue.offer(new int[]{newRow, newCol});
          }
        }
      }
    }

    return result;
  }


  /**
   * board containing 'X' and 'O,' capture all regions that are 4-directionally surrounded by 'X' A
   * region is captured by flipping all 'O's into 'X's in that surrounded region.
   */
  public void solve(char[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (board[i][j] == 'O') {
          end = false;
          List<int[]> l = new ArrayList<>();
          dfs(board, i, j, l);
          if (end) {
            for (int[] arr : l) {
              int r = arr[0];
              int c = arr[1];
              board[r][c] = 'O';
            }
          }
        }
      }
    }
  }

  private void dfs(char[][] board, int r, int c, List<int[]> l) {
    if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
      end = true;
      return;
    } else if (board[r][c] == 'X') {
      return;
    }
    board[r][c] = 'X';
    l.add(new int[]{r, c});
    for (int[] dir : coordinates) {
      dfs(board, r + dir[0], c + dir[1], l);
    }
  }


  /**
   * binary matrix grid, where 0 represents a sea cell and 1 represents a land cell. A makeMove
   * consists of walking from one land cell to another adjacent (4-directionally) land cell or
   * walking off the boundary of the grid Return the number of land cells in the grid for which we
   * cannot walk off the boundary of the grid in any number of moves.
   */
  public int numEnclaves(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if ((i == 0 || j == 0 || i == m - 1 || j == n - 1) && grid[i][j] == 1) {
          dfsForNumEnclaves(grid, i, j);
        }
      }
    }
    return Arrays.stream(grid).mapToInt(row -> Arrays.stream(row).sum()).sum();
  }

  private void dfsForNumEnclaves(int[][] grid, int i, int j) {
    grid[i][j] = 0;
    for (int[] direction : coordinates) {
      int x = i + direction[0];
      int y = j + direction[1];
      if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
        dfsForNumEnclaves(grid, x, y);
      }
    }
  }

  /**
   * Calculates the number of provinces among a given set of cities. A province is defined as a
   * group of directly or indirectly connected cities.
   * <p>This method uses a connectivity matrix to determine the connections. Each city is
   * represented by an index in the matrix, and a direct connection between two cities is indicated
   * by a 1 in the corresponding cell of the matrix. The matrix is symmetric, and each city is
   * always connected to itself.
   *
   * @param isConnected A 2D array representing the connectivity matrix. It is an n x n matrix where
   *                    isConnected[i][j] = 1 indicates that the ith city and the jth city are
   *                    directly connected, and isConnected[i][j] = 0 otherwise.
   * @return The total number of provinces, which are groups of connected cities. Example 1: Input:
   * isConnected = [[1,1,0],[1,1,0],[0,0,1]] Output: 2 In this example, cities 0 and 1 are
   * connected, forming one province, while city 2 is isolated, forming another province. Therefore,
   * the output is 2. Example 2: Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]] Output: 3 In this
   * example, each city is isolated, so each city forms its own province. Therefore, the output is
   * 3. Constraints: n == isConnected[i].length isConnected[i][j] is 1 or 0. isConnected[i][i] == 1
   * isConnected[i][j] == isConnected[j][i]
   */
  public int findNumberOfProvinces(int[][] isConnected) {
    int numberOfProvinces = 0;
    int n = isConnected.length;
    boolean[] visited = new boolean[n];
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        exploreProvince(visited, isConnected, i, n);
        numberOfProvinces++; // Increment count for each unvisited province found
      }
    }
    return numberOfProvinces;
  }

  public int findNumberOfProvincesUsingDSU(int[][] isConnected) {
    int n = isConnected.length;
    UnionFind uf = new UnionFind(n);
    for (int i = 0; i < n - 1; i++) {
      for (int j = i + 1; j < n; j++) {
        if (isConnected[i][j] == 1) {
          uf.union(i, j);
        }
      }
    }
    return uf.count();
  }

  private void exploreProvince(boolean[] visited, int[][] isConnected, int node, int n) {
    visited[node] = true;
    for (int i = 0; i < n; i++) {
      if (isConnected[node][i] == 1 && !visited[i]) {
        exploreProvince(visited, isConnected, i, n);
      }
    }
  }
}
