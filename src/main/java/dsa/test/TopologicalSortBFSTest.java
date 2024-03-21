package dsa.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class TopologicalSortBFSTest {

    @Test
    void testTopologicalSort() {
        TopologicalSortBFS graph = new TopologicalSortBFS(6);
        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        graph.topologicalSort();
        String expectedOutputRegex = ".*4.*5.*2.*0.*3.*1.*";
        assertTrue(outContent.toString().trim().matches(expectedOutputRegex));
    }

    @Test
    void testTopologicalSortWithCycle() {
        TopologicalSortBFS graph = new TopologicalSortBFS(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1); // Adding a cycle

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        graph.topologicalSort();
        String expectedOutput = "There exists a cycle in the graph";
        assertTrue(outContent.toString().trim().contains(expectedOutput));
    }
}

