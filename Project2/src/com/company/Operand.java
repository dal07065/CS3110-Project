package com.company;

public class Operand implements ExpressionItem {
    private float value;
    public Operand(float value)
    {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}

