package hw1;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Problem0 {
    public static void main(String[] args) throws FileNotFoundException {
        inputscanner();
        keystroke();
    }

    public static void keystroke() {
        int num1;
        double num2;
        String name;
        double sum;

        Scanner in = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        num1 = in.nextInt();
        System.out.print("Enter a floating point number: ");
        num2 = in.nextDouble();
        System.out.print("Enter your name: ");
        name = in.next();
        System.out.printf("Hi! %s, the sum of %d and %.2f is %.2f \n", name, num1, num2, (num1 + num2));
    }

    public static void inputscanner() throws FileNotFoundException {
        int num1;
        double num2;
        String name;
        double sum;
        Scanner in = new Scanner(new File("./input/input0.txt"));
        num1 = in.nextInt();
        num2 = in.nextDouble();
        name = in.next();
        System.out.printf("Hi! %s, the sum of %d and %.2f is %.2f\n", name, num1, num2, (num1 + num2));
        in.close();
    }
}

