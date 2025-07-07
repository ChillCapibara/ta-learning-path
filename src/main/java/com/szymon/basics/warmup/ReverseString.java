package com.szymon.basics.warmup;

public class ReverseString {

    public static void main(String[] args){
        System.out.println(reverse("Lorem ipsum dolor sit amet."));
        System.out.println(reverse(""));
        System.out.println(reverse(null));
        System.out.println(reverse("a"));
    }

    public static String reverse(String input){
        if (input == null || input.length() < 1){
            return "";
        }
        StringBuilder reversed = new StringBuilder();
        for (int i = input.length()-1; i >=0; i--){
            reversed.append(input.charAt(i));
        }
        return reversed.toString();
    }
}
