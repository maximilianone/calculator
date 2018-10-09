package training;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    private static final int RESULT = 16;
    private static final int OPERATION_COUNT_AFTER_FIRST_EXECUTION = 3;
    private static final String FIRST_PRIORITY_OPERATIONS = "*/";
    private static final Integer[] NUMBERS_AFTER_FIRST_EXECUTION = new Integer[]{2, 12, 2, 2};

    private static String[] operations;
    private static Integer[] numbers;

    @Before
    public void setUp() {
        operations = new String[]{"", "+", "*", "+"};
        numbers = new Integer[]{2, 3, 4, 2};
    }

    @Test
    public void shouldReturnEvaluationResult() {
        assertEquals(RESULT, Calculator.calculate(operations, numbers));
    }

    @Test
    public void shouldExecuteOperationsAndReturnNewOperationArrayLength() {
        int count = Calculator.executeOperations(operations, FIRST_PRIORITY_OPERATIONS, numbers, operations.length);
        assertArrayEquals(NUMBERS_AFTER_FIRST_EXECUTION, numbers);
        assertEquals(OPERATION_COUNT_AFTER_FIRST_EXECUTION, count);
    }
}