package com.szymon.basics.unit;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.szymon.basics.collections.ReturnUnique.getUniqueWords;
import static com.szymon.basics.collections.ReturnUnique.getWordsInOrder;

public class ReturnUniqueTests {

    @Test
    public void checkUniqueSet(){
        Set<String> actualResults = getUniqueWords("a b c d a b");
        Assert.assertEquals(actualResults.size(), 4);
        Assert.assertTrue(actualResults.contains("a"));
    }

    @Test
    public void checkWordsInOrder(){
        List<String> actualResults = getWordsInOrder("Lorem ipsum dolor sit amet");
        List<String> expected = new ArrayList<>();

        expected.add("Lorem");
        expected.add("ipsum");
        expected.add("dolor");
        expected.add("sit");
        expected.add("amet");

        Assert.assertEquals(actualResults.size(), 5);
        Assert.assertEquals(actualResults, expected);
    }

}
