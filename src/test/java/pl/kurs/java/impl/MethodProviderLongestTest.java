package pl.kurs.java.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class MethodProviderLongestTest {

    private final String expected;
    private final List<String> sourceStrings;

    public MethodProviderLongestTest(String expected, List<String> sourceStrings) {
        this.expected = expected;
        this.sourceStrings = sourceStrings;
    }

    @Test
    public void shouldReturnLongestString() {
        assertEquals(expected, MethodProvider.longest(sourceStrings));
    }

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {"1990-10-12", Arrays.asList("1990-10-12", "elo", "1854-12-16", "no i git", "10/01/2002")},
                {"daj spokój", Arrays.asList("daj", "spokój", "daj spokój")},
                {"", Collections.emptyList()},
                {"daj spokój", Arrays.asList("daj", null, "spokój", "", "daj spokój")},
                {"", null},
        });
    }
}