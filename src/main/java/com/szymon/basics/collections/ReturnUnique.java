package com.szymon.basics.collections;

import java.util.*;

public class ReturnUnique {

    public static void main(String[] args) {
        getUniqueWords("Lorem ipsum dolor sit amet lorem dolor");
        getWordsInOrder("Lorem ipsum dolor sit amet lorem dolor");
    }

    public static Set<String> getUniqueWords(String txt){
        String[] splited = txt.split(" ");
        Set<String> setOfUniqueValues = new LinkedHashSet<>(Arrays.asList(splited));
        System.out.println(setOfUniqueValues);
        return setOfUniqueValues;
    }

    public static List<String> getWordsInOrder(String txt){
        String[] splited = txt.split(" ");
        List<String> listOfUniqueValues = new ArrayList<>(Arrays.asList(splited));
        System.out.println(listOfUniqueValues);
        return listOfUniqueValues;
    }

}
