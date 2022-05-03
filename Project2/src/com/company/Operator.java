package com.company;

public class Operator implements ExpressionItem {
    private char operator;

    // 1 for + -
    // 2 for * /
    private int precedence;

    public Operator(char operator)
    {
        this.operator = operator;

        if(operator == '*' || operator == '/')
            precedence = 2;
        else if(operator == '+' || operator == '-')
            precedence = 1;
        else
            precedence = 0;

    }
    public boolean isGreaterThan(Operator op)
    {
        if(precedence > op.getPrecedence() || op == null)
            return true;
        return false;
    }

    public char getOperator() {
        return operator;
    }

    public int getPrecedence() {
        return precedence;
    }

    @Override
    public String toString() {
        return String.valueOf(operator);
    }
}
