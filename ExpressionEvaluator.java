import java.util.Stack;

public class ExpressionEvaluator {
    public static double evaluate(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty");
        }

        expression = expression.replaceAll("\\s+", "");
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '(') {
                operators.push(c);
            } else if (Character.isDigit(c) || c == '.') {
                StringBuilder num = new StringBuilder();
                boolean hasDecimal = false;
                while (i < expression.length() &&
                       (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    if (expression.charAt(i) == '.') {
                        if (hasDecimal) {
                            throw new IllegalArgumentException("Invalid number format");
                        }
                        hasDecimal = true;
                    }
                    num.append(expression.charAt(i));
                    i++;
                }
                i--;
                try {
                    values.push(Double.parseDouble(num.toString()));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number format");
                }
            } else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    evaluateTop(values, operators);
                }
                if (operators.isEmpty()) {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
                operators.pop(); // Remove '('
            } else if (isOperator(c)) {
                if (c == '-' && (i == 0 || expression.charAt(i - 1) == '(' || isOperator(expression.charAt(i - 1)))) {
                    // Handle negative numbers
                    values.push(0.0);
                    operators.push('-');
                } else {
                    while (!operators.isEmpty() && operators.peek() != '(' &&
                           hasHigherPrecedence(operators.peek(), c)) {
                        evaluateTop(values, operators);
                    }
                    operators.push(c);
                }
            } else {
                throw new IllegalArgumentException("Invalid character: " + c);
            }
        }

        while (!operators.isEmpty()) {
            if (operators.peek() == '(') {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
            evaluateTop(values, operators);
        }

        if (values.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return values.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean hasHigherPrecedence(char op1, char op2) {
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return true;
        }
        return false;
    }

    private static void evaluateTop(Stack<Double> values, Stack<Character> operators) {
        if (values.size() < 2 || operators.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression");
        }
        double b = values.pop();
        double a = values.pop();
        char op = operators.pop();

        switch (op) {
            case '+':
                values.push(a + b);
                break;
            case '-':
                values.push(a - b);
                break;
            case '*':
                values.push(a * b);
                break;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                values.push(a / b);
                break;
            default:
                throw new IllegalArgumentException("Invalid operator: " + op);
        }
    }
}