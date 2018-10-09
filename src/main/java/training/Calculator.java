package training;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiFunction;

public class Calculator {
    private static final String FIRST_PRIORITY_OPERATIONS = "*/";
    private static final String SECOND_PRIORITY_OPERATIONS = "-+";

    public static void main(String[] args) throws IOException {
        System.out.println("Enter an expression: ");

        String line = new Scanner(System.in).nextLine();
        System.out.println(line);

        String[] operations = line.split("[0-9]+");
        System.out.println(Arrays.toString(operations));
        String[] numbers = line.split("[+\\-*/]");
        System.out.println(Arrays.toString(numbers));

        Integer[] numbersConverted = Arrays.stream(numbers).map(Integer::parseInt).toArray(Integer[]::new);
        System.out.println(Arrays.toString(numbersConverted));

        int result = calculate(operations, numbersConverted);
        System.out.println(result);
    }

    static int calculate(String[] operations, Integer[] numbers) {
        int operationsCount = executeOperations(operations, FIRST_PRIORITY_OPERATIONS, numbers, operations.length);
        executeOperations(operations, SECOND_PRIORITY_OPERATIONS, numbers, operationsCount);
        return numbers[0];
    }

    static int executeOperations(String[] operations, String toExecute, Integer[] numbers, int count) {
        int index = 1;
        while (index < count) {
            if (toExecute.contains(operations[index])) {
                numbers[index - 1] = getFunction(operations[index]).apply(numbers[index - 1], numbers[index]);
                for (int j = index; j < count - 1; j++) {
                    numbers[j] = numbers[j + 1];
                    operations[j] = operations[j + 1];
                }
                count--;
                continue;
            }
            index++;
        }
        return count;
    }

    private static BiFunction<Integer, Integer, Integer> getFunction(String operation) {
        switch (operation) {
            case "*":
                return (a, b) -> a * b;
            case "/":
                return (a, b) -> a / b;
            case "+":
                return (a, b) -> a + b;
            case "-":
                return (a, b) -> a - b;
            default:
                throw new RuntimeException("Unsupported operation");
        }
    }
}
