package com.szymon.oop.calculator;

import java.util.Scanner;

public class BasicCalculator implements Calculator {

    private int getNumber(String[] array, int index) {
        return Integer.parseInt(array[index].trim());
    }

    public BasicCalculator() {
        System.out.println("Enter the full operation:");
        String userInput = new Scanner(System.in).nextLine();
        triggerOperation(userInput);
    }

    private void triggerOperation(String input) {
        try {
            if (input.contains("sqrt")) {
                int value = Integer.parseInt(input.split("sqrt")[1]);
                square(value);
            } else if (input.contains("^")) {
                String[] splitInput = input.split("\\^");
                power(getNumber(splitInput, 0), getNumber(splitInput, 1));
            } else if (input.contains("*")) {
                String[] splitInput = input.split("\\*");
                multiply(getNumber(splitInput, 0), getNumber(splitInput, 1));
            } else if (input.contains("/")) {
                String[] splitInput = input.split("/");
                divide(getNumber(splitInput, 0), getNumber(splitInput, 1));
            } else if (input.contains("+")) {
                String[] splitInput = input.split("\\+");
                add(getNumber(splitInput, 0), getNumber(splitInput, 1));
            } else if (input.contains("-")) {
                String[] splitInput = input.split("-");
                subtract(getNumber(splitInput, 0), getNumber(splitInput, 1));
            }
        } catch (Exception e) {
            System.out.println("Invalid operation");
        }
    }

    @Override
    public void add(int num1, int num2) {
        System.out.println(num1 + num2);
    }

    @Override
    public void subtract(int num1, int num2) {
        System.out.println(-num2);
    }

    @Override
    public void multiply(int num1, int num2) {
        System.out.println(num1 * num2);
    }

    @Override
    public void divide(int num1, int num2) {
        System.out.println((float) num1 / num2);
    }

    @Override
    public void square(int num) {
        System.out.println(Math.sqrt(num));
    }

    @Override
    public void power(int baseNum, int power) {
        System.out.println(Math.pow(baseNum, power));
    }
}