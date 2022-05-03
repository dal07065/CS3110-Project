import java.util.Scanner;

public class Main {

    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        Project1 project1 = new Project1();

        String expression = "";
        float result;

        while(!expression.equalsIgnoreCase("-1"))
        {
            System.out.print("Enter float value: ");
            expression = input.nextLine();

            if(project1.convertStringToFloat(expression))
                System.out.println("Result: " + project1.getResult());
            else
                System.out.println("Invalid Input!");
        }
    }

}
