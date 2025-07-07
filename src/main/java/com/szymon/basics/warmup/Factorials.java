package com.szymon.basics.warmup;

public class Factorials {

    public static void main(String[] args){
        System.out.println(calculateFactorial(5));
    }

    public static int calculateFactorial(int num) {
        int sum = 1;
        while (num > 0) {
            sum *= num--;
        }
        return sum;
    }
}

