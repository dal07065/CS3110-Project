package com.company;

import java.util.Stack;

public class Calculator
{
    private Project1 project1 = new Project1();
    public float calculate(String expression) throws Exception {
        Stack<ExpressionItem> postfix = new Stack<ExpressionItem>();

        postfix = InfixToPostfix(postfix, expression);

        return evaluatePostfix(postfix);
    }

    private float evaluatePostfix(Stack<ExpressionItem> postfix) throws Exception {
        // postfix: [1.0 , 3.0 , 2.0 , * , + , 4.0 , 3.0 , 2.0 , - , * , - , 3.4 , - , 5.6 , + ]

        Stack<ExpressionItem> stack = new Stack<>();

        while(!postfix.isEmpty())
        {
            ExpressionItem top = postfix.pop();
            if(top instanceof Operand)
            {
                stack.push(top);
            }
            else
            {
                Operand op2 = (Operand) stack.pop();
                Operand op1 = (Operand) stack.pop();

                stack.push(new Operand(evaluate((Operator) top, op1.getValue(), op2.getValue())));
            }
        }

        return ((Operand)stack.pop()).getValue();

    }

    private float evaluate(Operator highest, float op1, float op2) throws Exception {
        switch(highest.getOperator())
        {
            case '+':
                return op1 + op2;
            case '-':
                return op1 - op2;
            case '*':
                return op1 * op2;
            case '/':
                if(op2 == 0)
                    throw new Exception("Division by zero error!");

                return op1 / op2;
            default:
                return -1;
        }
    }


    private Stack<ExpressionItem> InfixToPostfix(Stack<ExpressionItem> postfix, String expression) {

        Stack<Operator> stack = new Stack<Operator>();

        String tempFloatValue = "";
        float floatValue = 0.0f;

        expression = trimExpression(expression);

        // iterate through expression
        for(int i = 0; i < expression.length(); i++)
        {
            char currentChar = expression.charAt(i);

            if(!isOperator(currentChar))
            {
                while(i < expression.length() && isValidFloatInput(expression, i))
                {
                    tempFloatValue += expression.charAt(i);
                    i++;
                }
                i--;
                if(project1.convertStringToFloat(tempFloatValue))
                    floatValue = project1.getResult();
                tempFloatValue = "";
                postfix.push(new Operand(floatValue));
            }
            else if(currentChar == ')')
            {
                // Until '(' is found, pop the stack and put into post fix
                while(stack.peek().getOperator() != '(')
                {
                    postfix.push(stack.pop());
                }
                stack.pop();
            }
            else if(isOperator(currentChar))
            {
                Operator currentOp = new Operator(currentChar);

                if(stack.isEmpty() || currentOp.isGreaterThan(stack.peek()) || currentChar == '(')
                    stack.push(currentOp);
                else
                {
                    while(!stack.isEmpty() && !currentOp.isGreaterThan(stack.peek()))
                        postfix.push(stack.pop());
                    stack.push(currentOp);
                }
            }
        }

        while(!stack.isEmpty())
            postfix.add(stack.pop());

        Stack<ExpressionItem> newPostFix = new Stack<>();
        int postfixSize = postfix.size();
        for(int i = 0; i < postfixSize; i++)
            newPostFix.push(postfix.pop());

        return newPostFix;
    }

    private boolean isValidFloatInput(String expression, int index) {
        char charAt = expression.charAt(index);
        if(isNumber(charAt) || charAt == 'e' || charAt == 'E' || charAt == 'f' || charAt == 'F' || charAt == '.')
            return true;
        else if((charAt == '-' || charAt == '+') && (expression.charAt(index-1) == 'e' || expression.charAt(index-1) == 'E'))
            return true;
        return false;
    }

    // Trims all white space and underscores
    private String trimExpression(String expression) {

        String newExpression = "";

        for(int i = 0; i < expression.length(); i++)
        {
            if(expression.charAt(i) != ' ' && expression.charAt(i) != '_')
                newExpression += expression.charAt(i);
        }

        return newExpression;
    }

    public static boolean isOperator(char currentChar) {
        if(currentChar == '+' ||
        currentChar == '-' ||
        currentChar == '/' ||
        currentChar == '*' ||
        currentChar == '(' ||
        currentChar == ')')
            return true;
        return false;
    }

    public static boolean isNumber(char currentChar) {
        if(currentChar >=48 && currentChar <= 57)
            return true;
        return false;
    }


}
