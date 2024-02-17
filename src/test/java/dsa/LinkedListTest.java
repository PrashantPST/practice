package dsa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkedListTest {
    private LinkedList linkedList;

    @BeforeEach
    public void setUp() {
        linkedList = new LinkedList(0, null);
    }

    @Test
    public void testReverseLinkedListWhenListIsNullThenReturnNull() {
        LinkedList result = LinkedList.reverseLinkedList(null);
        assertNull(result, "The result should be null when the linked list is null");
    }

    @Test
    public void testReverseLinkedListWhenListHasOneNodeThenReturnSameNode() {
        LinkedList node = new LinkedList(1, null);
        LinkedList result = LinkedList.reverseLinkedList(node);
        assertEquals(node, result, "The result should be the same node when the linked list has only one node");
    }

    @Test
    public void testReverseLinkedListWhenListHasMultipleNodesThenReturnReversedList() {
        LinkedList node1 = new LinkedList(1, null);
        LinkedList node2 = new LinkedList(2, node1);
        LinkedList node3 = new LinkedList(3, node2);
        LinkedList result = LinkedList.reverseLinkedList(node3);
        assertEquals(node1, result, "The result should be the reversed list when the linked list has multiple nodes");
        assertEquals(node2, result.getNext(), "The next node of the result should be the second node of the original list");
        assertEquals(node3, result.getNext().getNext(), "The next node of the next node of the result should be the first node of the original list");
    }

    @Test
    public void testReverseLinkedListWhenListHasCycleThenReturnSameList() {
        LinkedList node1 = new LinkedList(1, null);
        LinkedList node2 = new LinkedList(2, node1);
        LinkedList node3 = new LinkedList(3, node2);
        node1.setNext(node3); // create a cycle
        LinkedList result = LinkedList.reverseLinkedList(node3);
        assertEquals(node3, result, "The result should be the same list when the linked list has a cycle");
        assertEquals(node2, result.getNext(), "The next node of the result should be the second node of the original list");
        assertEquals(node1, result.getNext().getNext(), "The next node of the next node of the result should be the first node of the original list");
    }
}