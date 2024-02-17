package dsa.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dsa.design.MinMaxStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MinMaxStackTest {

    private MinMaxStack minMaxStack;

    @BeforeEach
    void setUp() {
        minMaxStack = new MinMaxStack();
    }

    @Test
    void testStackOperations() {
        assertNull(minMaxStack.peek());
        assertNull(minMaxStack.pop());

        minMaxStack.push(5);
        assertEquals(5, minMaxStack.peek());
        assertEquals(5, minMaxStack.getMin());
        assertEquals(5, minMaxStack.getMax());

        minMaxStack.push(2);
        assertEquals(2, minMaxStack.getMin());
        assertEquals(5, minMaxStack.getMax());

        minMaxStack.push(8);
        assertEquals(2, minMaxStack.getMin());
        assertEquals(8, minMaxStack.getMax());

        assertEquals(8, minMaxStack.pop());
        assertEquals(5, minMaxStack.getMax());

        assertEquals(2, minMaxStack.pop());
        assertEquals(5, minMaxStack.getMin());

        assertEquals(5, minMaxStack.pop());
        assertTrue(minMaxStack.isEmpty());
    }

    @Test
    void testEmptyStack() {
        assertNull(minMaxStack.peek());
        assertNull(minMaxStack.pop());
        assertNull(minMaxStack.getMin());
        assertNull(minMaxStack.getMax());
    }

    @Test
    void testSingleElementStack() {
        minMaxStack.push(3);
        assertEquals(3, minMaxStack.peek());
        assertEquals(3, minMaxStack.getMin());
        assertEquals(3, minMaxStack.getMax());
        assertEquals(3, minMaxStack.pop());
        assertNull(minMaxStack.peek());
    }

    @Test
    void testPushNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            minMaxStack.push(null);
        });
        String expectedMessage = "Number cannot be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}

