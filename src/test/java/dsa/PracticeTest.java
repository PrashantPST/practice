package dsa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PracticeTest {

    private Practice practice;

    @BeforeEach
    public void setup() {
        practice = new Practice();
    }

    @Test
    public void testEvaluateReversePolishNotationWhenValidExpressionThenCorrectResult() {
        // Arrange
        String[] expression = new String[]{"2", "1", "+", "3", "*"};
        int expected = 9;

        // Act
        int result = practice.evaluateReversePolishNotation(expression);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testEvaluateReversePolishNotationWhenInvalidExpressionThenException() {
        // Arrange
        String[] expression = new String[]{"2", "1", "+", "*", "3"};

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> practice.evaluateReversePolishNotation(expression));
    }

    @Test
    public void testEvaluateReversePolishNotationWhenEmptyExpressionThenException() {
        // Arrange
        String[] expression = new String[]{};

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> practice.evaluateReversePolishNotation(expression));
    }
}