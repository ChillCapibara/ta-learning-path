package com.szymon.basics.warmup;

public class Palindrome {
    public static void main(String[] args){
        System.out.println(isPalindrome("lorem"));
        System.out.println(isPalindrome("madam"));
        System.out.println(isPalindrome("RaceCar"));
    }

    public static boolean isPalindrome(String input){
        if (input == null) return false;
        return input.equalsIgnoreCase(ReverseString.reverse(input));
    }

}
