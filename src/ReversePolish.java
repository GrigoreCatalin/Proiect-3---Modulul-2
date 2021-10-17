import java.util.ArrayDeque;
import java.util.Deque;

public class ReversePolish {

    public static int precedence(char c) {
        if (c == '+' || c == '-') {
            return 11;
        } else if (c == '*' || c == '/') {
            return 12;
        } else if (c == '^') {
            return 13;
        }
        return -1;
    }

    public static String reversepolish(String expression) {
        Deque<Character> stack = new ArrayDeque();

        String result = "";
        int i = 0;

        while (i < expression.length()) {
            if (Character.isDigit(expression.charAt(i))) {
                result += expression.charAt(i);
            } else if (!Character.isDigit(expression.charAt(i)) && expression.charAt(i) != '(' && expression.charAt(i) != ')') {
                while (!stack.isEmpty() && stack.peek() != '(' && precedence(expression.charAt(i)) <= precedence(stack.peek()) && precedence(expression.charAt(i)) != precedence(('^'))) {
                    result += stack.pop();
                }
                stack.push(expression.charAt(i));
            } else if (expression.charAt(i) == '(') {
                stack.push('(');
            } else if (expression.charAt(i) == ')') {
                while (stack.peek() != '(') {
                    result += stack.pop();
                }
                if (stack.isEmpty() && stack.peek() != '(') {
                    String error = "This expression is invalid";
                    return error;
                }
                stack.pop();
            }
            i++;
        }
        while (!stack.isEmpty()) {
            char el = stack.pop();
            result += el;
            if (el == '(') {
                String error = "This stack is invalid";
                return error;
            }


        }

        return result;
    }

    public static int postFix(String expression) {
        Deque<Integer> stack = new ArrayDeque();
        int i = 0;
        int op1 = 0;
        int op2 = 0;

        while (i < expression.length()) {
            if (Character.isDigit(expression.charAt(i))) {
                stack.push(expression.charAt(i) - '0');
            } else {
                char c = expression.charAt(i);
                if (stack.peek() != null) {
                    op1 = stack.pop();
                    if (stack.peek() != null) {
                        op2 = stack.pop();
                    } else {
                        System.out.println("This stack is invalid");
                        return -1;
                    }
                    switch (c) {
                        case '+':
                            stack.push(op2 + op1);
                            break;
                        case '-':
                            stack.push(op2 - op1);
                            break;
                        case '*':
                            stack.push(op2 * op1);
                            break;
                        case '/':
                            stack.push(op2 / op1);
                            break;
                        case '^':
                            stack.push((int) Math.pow(op2, op1));
                            break;
                    }
                }
            }
            i++;
        }
        int result = stack.peek();
        System.out.println(stack.size());

        if (stack.isEmpty()) {
            System.out.println("This stack is invalid");
            return -1;
        }
        return result;
    }


    public static void main(String[] args) {
        String expression = "3+(2+1)*2^3^2-8/(5-1*2/2)";
        String result = reversepolish(expression);
        //  System.out.println(result);
        System.out.println(postFix(result));

    }
}
