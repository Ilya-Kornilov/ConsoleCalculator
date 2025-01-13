import java.util.Scanner;

public class Main {

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 10;
    private static final String VALID_OPERATORS = "+-*/";

    private static final String INPUT_FORMAT_ERROR = "формат введённой строки не удовлетворяет заданию - " +
            "два операнда и один оператор (+, -, /, *) через пробел";
    private static final String RANGE_ERROR =
            "оба операнда должны быть в диапазоне от " + MIN_VALUE + " до " + MAX_VALUE;
    private static final String OPERATOR_ERROR = "допустимый набор операторов: +, -, *, /";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine().trim();
            if (input.isBlank())
                throw new IllegalArgumentException("Вы ввели пустую строку");
            System.out.println(calc(input));
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calc(String input) {
        String[] mathProblem = input.split(" ");

        if (mathProblem.length != 3)
            throw new IllegalArgumentException(INPUT_FORMAT_ERROR);

        int number1 = parseNumber(mathProblem[0]);
        int number2 = parseNumber(mathProblem[2]);

        validateRange(number1);
        validateRange(number2);

        String operator = mathProblem[1];
        if (operator.length() != 1 || !VALID_OPERATORS.contains(operator))
            throw new IllegalArgumentException(OPERATOR_ERROR);

        int result = calculate(number1, number2, operator);
        return String.valueOf(result);
    }

    private static int parseNumber(String inputNumber) {
        try {
            return Integer.parseInt(inputNumber);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("введено некорректное число(" + inputNumber + ")");
        }
    }

    private static void validateRange(int number) {
        if (number < MIN_VALUE || number > MAX_VALUE)
            throw new IllegalArgumentException(RANGE_ERROR);
    }

    private static int calculate(int number1, int number2, String operator) {
        return switch (operator) {
            case "+" -> number1 + number2;
            case "-" -> number1 - number2;
            case "*" -> number1 * number2;
            case "/" -> number1 / number2;
            default -> throw new IllegalArgumentException(OPERATOR_ERROR);
        };
    }
}