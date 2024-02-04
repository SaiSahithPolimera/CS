// Program to demonstrate interfaces.

import java.util.*;

interface Calculator {
    int Add(int Number1, int Number2);
    int Subtract(int Number1, int Number2);
    int Multiply(int Number1, int Number2);
    int Divide(int Number1, int Number2);
}

class BasicCalculator implements Calculator {
    public int Add(int Number1, int Number2) {
        return Number1 + Number2;
    }

    public int Subtract(int Number1, int Number2) {
        return Number1 - Number2;
    }

    public int Multiply(int Number1, int Number2) {
        return Number1 * Number2;
    }

    public int Divide(int Number1, int Number2) {
        return Number1 / Number2;
    }
}

public class SampleInterface {
    static Scanner input = new Scanner(System.in);

    public static int readInput(String prompt) {
        System.out.println(prompt);
        return input.nextInt();
    }

    public static void main(String[] args) {
        BasicCalculator calculator = new BasicCalculator();
        int Choice;
        do {
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");
            Choice = input.nextInt();
            switch (Choice) {
                case 1:
                    int Number1 = readInput("Enter Number1: ");
                    int Number2 = readInput("Enter Number2: ");
                    int sum = calculator.Add(Number1, Number2);
                    System.out.println("Sum: " + sum);
                    break;
                case 2:
                    Number1 = readInput("Enter Number1: ");
                    Number2 = readInput("Enter Number2: ");
                    int difference = calculator.Subtract(Number1, Number2);
                    System.out.println("Difference: " + difference);
                    break;
                case 3:
                    Number1 = readInput("Enter Number1: ");
                    Number2 = readInput("Enter Number2: ");
                    int product = calculator.Multiply(Number1, Number2);
                    System.out.println("Product: " + product);
                    break;
                case 4:
                    Number1 = readInput("Enter Number1: ");
                    Number2 = readInput("Enter Number2: ");
                    int quotient = calculator.Divide(Number1, Number2);
                    System.out.println("Quotient: " + quotient);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Enter a valid choice: ");
                    break;
            }
            System.out.println("Enter any number to continue or 5 to exit: ");
            Choice = input.nextInt();
        } while (Choice != 5);
        input.close();
    }
}
