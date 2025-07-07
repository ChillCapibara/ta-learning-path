package com.szymon.basics.collections;

import java.util.Arrays;
import java.util.List;

public class GenericsPractice{

    public static void main(String[] args) {
        System.out.println(getFirstNonNullElementFromList(Arrays.asList(null, null, "lorem", null, 1)));
    }

    public static <E> E getFirstNonNullElementFromList(List<E> genericList) {
        if (!genericList.isEmpty()) {
            for (E element : genericList) {
                if (element != null) {
                    return element;
                }
            }
        }
        return null;
    }
}
