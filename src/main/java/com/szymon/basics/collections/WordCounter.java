package com.szymon.basics.collections;

import java.util.*;

public class WordCounter {

    public static void main(String[] args) {
        wordCounter("lorem lorem ipsum ipsum  dolor ipsum dolor sit amet lorem ipsum, lorem");
    }

    public static void wordCounter(String sentence) {
        Map<String, Integer> countedWords = new LinkedHashMap<>();
        String[] splited = sentence.split(" ");
        for (String word : splited) {
            if (countedWords.containsKey(word)) {
                countedWords.replace(word, countedWords.get(word) + 1);
            } else {
                countedWords.put(word, 1);
            }
        }

        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(countedWords.entrySet());
        sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        for (int i = 0; i < 3; i++){
            Map.Entry<String, Integer> entry = sorted.get(i);
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}