package com.szymon.basics;

public class ArraySum {
    public static void main(String args[]) throws IllegalArgumentException {
        System.out.println(arraySum(new int[]{1, 2, 3}));
        System.out.println(arraySum(new int[]{-1, 0, 4}));
        System.out.println(arraySum(new int[]{}));
        System.out.println(arraySum(null));
    }

    public static int arraySum(int[] numbersArray) throws IllegalArgumentException{
        if (numbersArray == null || numbersArray.length == 0){
            throw new IllegalArgumentException("Array cannot be empty");
        }
        int sum = 0;
        for(int num : numbersArray){
            sum += num;
        }
        return sum;
    }
}
