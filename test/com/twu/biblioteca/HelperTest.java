package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HelperTest {

    @Test
    public void testGetLongestString() {
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("a");
        listOfStrings.add("abc");
        listOfStrings.add("abc");
        assertEquals(3, Helper.getLongestStringLength(listOfStrings.stream()));
        listOfStrings.add("abcde");
        assertEquals(5, Helper.getLongestStringLength(listOfStrings.stream()));
        listOfStrings = new ArrayList<>();
        assertEquals(0, Helper.getLongestStringLength(listOfStrings.stream()));
    }
}
