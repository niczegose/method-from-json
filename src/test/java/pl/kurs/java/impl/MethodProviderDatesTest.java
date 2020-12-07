package pl.kurs.java.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.kurs.java.model.Methoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class MethodProviderDatesTest {
    private final List<String> expected;
    private final List<String> sourceStrings;
    private final String pattern;

    public MethodProviderDatesTest(List<String> expected, List<String> sourceStrings, String pattern) {
        this.expected = expected;
        this.sourceStrings = sourceStrings;
        this.pattern = pattern;
    }

    @Test
    public void shouldReturnListOfDates() {
        assertEquals(expected, MethodProvider.dates(sourceStrings,pattern));
    }

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList("1990-10-12", "1854-12-16"), Arrays.asList("1990-10-12", "elo", "1854-12-16", "no i git", "10/01/2002"), "yyyy-MM-dd"},
                {Arrays.asList("10/01/2002"), Arrays.asList("1990-10-12", "elo", "1854-12-16", "no i git", "10/01/2002"), "dd/MM/yyyy"},
                {Collections.emptyList(), Arrays.asList("1990-10-12", "elo", "1854-12-16", "no i git", "10/01/2002"), "ddMMyyyy"},
                {Collections.emptyList(), Arrays.asList("1990-10-12", "elo", "1854-12-16", "no i git", "10/01/2002"), "hakunaMatata"},
                {Collections.emptyList(), Collections.emptyList(), "hakunaMatata"},
                {Collections.emptyList(), Collections.emptyList(), ""},
                {Collections.emptyList(), Collections.emptyList(), null},
                {Collections.emptyList(), null, null},
                {Collections.emptyList(), null, "hakuna Matata"},
                {Collections.emptyList(), null, "yyyyMMdd"}
        });
    }
}