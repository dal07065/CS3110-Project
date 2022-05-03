package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Calculator calc = new Calculator();

        String expression = "";
        float result;

        while(!expression.equalsIgnoreCase("-1"))
        {
            System.out.print("Enter expression (Type -1 to quit): ");
            expression = input.nextLine();

            result = calc.calculate(expression);

            System.out.println("Result: " + result);
        }

	// write your code here
    }
}
