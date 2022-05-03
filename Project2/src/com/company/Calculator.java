package com.company;

import java.util.Stack;

public class Calculator
{
    private Project1 project1 = new Project1();
    public float calculate(String expression)
    {
        Stack<ExpressionItem> postfix = new Stack<ExpressionItem>();

        postfix = InfixToPostfix(postfix, expression);

        return evaluatePostfix(postfix);
    }

    private float evaluatePostfix2(Stack<ExpressionItem> postfix) {
        Stack<ExpressionItem> orderOfOperation = new Stack<>();

        float finalValue = 0.0f;

        ExpressionItem op1, op2;
        ExpressionItem highest;

        ExpressionItem top = postfix.pop();

        while(true)
        {
            System.out.println("Postfix: " + postfix);
            if(top instanceof Operator)
            {
                op2 = postfix.pop();
                if(op2 instanceof Operand)
                {
                    op1 = postfix.pop();
                    if(op1 instanceof Operand)
                    {
                        highest = top;
                        finalValue = evaluate((Operator)highest, ((Operand) op1).getValue(), ((Operand) op2).getValue());
                        orderOfOperation.push(new Operand(finalValue));
                        if(!postfix.isEmpty())
                            top = postfix.pop();
                        else
                            break;
                    }
                    else {

                        orderOfOperation.push(top);
                        orderOfOperation.push(op2);
                        top = op1;
                    }
                }
                else {
                    orderOfOperation.push(top);
                    top = op2;
                }
            }
            else
            {
                orderOfOperation.push(top);
                if(!postfix.isEmpty())
                    top = postfix.pop();
                else
                    break;
            }
        }
        //Add last operand in the postfix that was popped
//        orderOfOperation.push(top);

        while(!orderOfOperation.isEmpty())
        {
            top = orderOfOperation.pop();
            if(top instanceof Operator)
            {
                finalValue += evaluate((Operator) top, ((Operand)orderOfOperation.pop()).getValue(), ((Operand)orderOfOperation.pop()).getValue());
            }
            else
            {
                Operand operand2 = (Operand) orderOfOperation.pop();
                Operator operator = (Operator) orderOfOperation.pop();
                finalValue += evaluate(operator, ((Operand)top).getValue(), operand2.getValue());
            }
        }
        return finalValue;
    }

    private float evaluatePostfix(Stack<ExpressionItem> postfix)
    {
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

    private float evaluate(Operator highest, float op1, float op2) {
        switch(highest.getOperator())
        {
            case '+':
                return op1 + op2;
            case '-':
                return op1 - op2;
            case '*':
                return op1 * op2;
            case '/':
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
                floatValue = project1.convertStringToFloat(tempFloatValue);
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
