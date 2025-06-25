package com.szymon.oop.calculator;

import java.util.Scanner;

public interface Calculator {

    // ToDo: redo the naive implementation. Enter num -> enter symbol -> enter num -> ... -> figure out order

    default int getUserInput(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    default boolean continueOperation(){
        Scanner sc = new Scanner(System.in);
        String answer = "";
        System.out.println("Do you wish to enter more numbers? y/n");
        try{
            answer = sc.next();
        } catch (Exception e){
            System.out.println("Provide an answer!");
        }
        return answer.equals("y");
    }

    default int getNumber(String nthNumber){
        System.out.println("Provide a " + nthNumber + " number: ");
        return getUserInput();
    }

    default int getNumber(){
        System.out.println("Provide next number: ");
        return getUserInput();
    }

    default void announceResult(int result){
        System.out.println("Result: " + result);
    }


    public void add();
    public void subtract();
    public void multiply();
    public void divide();
    public void square();
    public void power();
}
