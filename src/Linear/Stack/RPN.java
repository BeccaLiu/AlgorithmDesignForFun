package Linear.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by rliu on 11/10/16.
 */
public class RPN {

    /* Evaluate Reverse Polish Rotation
    * 2 1 + 3 *    =>  (2 + 1)*3
    * 6 3 9 / +    =>  6 + 3/9
    * Input:   String[] tokens     an string array of operations and operands representing the RPN expression
    * Output:  int value           the value of the RPN expression
    * Assumption:          1.only numbers and operations in the string array (No parentheses)
    *                      2.the expression is valid in terms of arithmetic rules
    * Corner Case:         Null or empty input-> 0
    */
    public static int evalPost(String[] tokens) {
        if (tokens == null || tokens.length == 0)
            return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < tokens.length; i++) {
            String t = tokens[i];
            switch (t) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b - a);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int c = stack.pop();
                    int d = stack.pop();
                    stack.push(d / c);
                    break;
                default:
                    stack.push(Integer.parseInt(t));
                    break;

            }
        }
        return stack.pop();
    }

    /*
     * ((3+4)*5-4*2)+1
     * Input:   char[] tokens     an string array of operations and operands representing the infix expression
     * Output:  int value           the value of the infix expression
     * Assumption:          1.only numbers, operations, parentheses in the string array
     *                      2.the expression is valid in terms of arithmetic rules
     * Corner Case:         Null or empty input-> 0
     * conclusion:          1.( push in stack
     *                      2. number push in stack
     *                      3. ) poll and caculate until (
     *                      4. higher precedence met->push in stack
     *                      5. lower precedence met-> calculate
     */
    public static int evalInfix(String[] tokens) {
        if (tokens == null || tokens.length == 0)
            return 0;
        Deque<Character> operation = new ArrayDeque<>();
        Deque<Integer> operands = new ArrayDeque<>();
        for (int i = 0; i < tokens.length; i++) {
            char curr = tokens[i].charAt(0);
            if (curr == '(')
                operation.push(curr);
            else if (curr == ')') {
                while (operation.peek() != '(') {
                    operands.push(calculate(operands.pop(), operands.pop(), operation.pop()));
                }
                operation.pop();
            } else if (curr == '+' || curr == '-' || curr == '*' || curr == '/') {
                while (!operation.isEmpty() && isLowerThan(curr, operation.peek())) {
                    operands.push(calculate(operands.pop(), operands.pop(), operation.pop()));
                }
                operation.push(curr);
            } else
                operands.push(curr - '0');
        }
        while (!operation.isEmpty()) {
            operands.push(calculate(operands.pop(), operands.pop(), operation.pop()));
        }
        return operands.pop();
    }

    public static boolean isLowerThan(char op1, char op2) {
        return (op1 == op2) || ((op2 == '*' || op2 == '/') && (op1 == '+' || op1 == '-'));
    }

    public static int calculate(int a, int b, char operation) {
        if (operation == '+')
            return a + b;
        else if (operation == '-')
            return b - a;
        else if (operation == '*')
            return a * b;
        else if (operation == '/')
            return b / a;
        else
            throw new IllegalStateException("Operation not found");
    }

    public static void main(String[] args) {
        String[] s1 = new String[]{"6", "3", "9", "/", "+"};
        System.out.println(evalPost(s1));

        String[] s2 = "( ( 3 + 4 ) * 5 - 1 / 4 * 2 ) + 1".split(" ");
        System.out.println(evalInfix(s2));

    }

}

