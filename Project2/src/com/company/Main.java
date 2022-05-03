package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        Calculator calc = new Calculator();

        String expression = "";
        float result;

        System.out.print("Enter expression (Type -1 to quit): ");
        expression = input.nextLine();

        while(!expression.equalsIgnoreCase("-1"))
        {
            result = calc.calculate(expression);

            System.out.println("Result: " + result);

            System.out.print("Enter expression (Type -1 to quit): ");
            expression = input.nextLine();
        }

	// write your code here
    }
}
