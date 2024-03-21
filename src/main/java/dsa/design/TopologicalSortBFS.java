package dsa.design;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSortBFS {

    int vertices;
    List<List<Integer>> adj = new ArrayList<>();
    public TopologicalSortBFS(int vertices) {
        this.vertices = vertices;
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    public void topologicalSort() {
        Queue<Integer> queue = new LinkedList<>();
        int visited = 0;
        int[] inDegree = new int[vertices];
        for (List<Integer> u: adj) {
            for (Integer v: u) {
                inDegree[v]++;
            }
        }
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            System.out.print(u);
            for (int v: adj.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.add(v);
                }
            }
            visited++;
        }
        if (visited != vertices) {
            System.out.println("There exists a cycle in the graph");
        }
    }
}

