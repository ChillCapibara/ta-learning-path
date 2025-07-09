package com.szymon.basics.unit;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.szymon.basics.collections.GenericsPractice.getFirstNonNullElementFromList;

public class GenericTests {

    @Test
    public void ensureFirstNonNullValueIsReturned(){
        Assert.assertEquals(getFirstNonNullElementFromList(Arrays.asList(null, null, "lorem", null, 1)), "lorem");
    }

    @Test
    public void getFirstInteger(){
        Assert.assertEquals(getFirstNonNullElementFromList(Arrays.asList(null, null, 1, "lorem")), 1);
    }

    @Test
    public void checkEmptyArray(){
        Assert.assertNull(getFirstNonNullElementFromList(new ArrayList<>()));
    }

}
