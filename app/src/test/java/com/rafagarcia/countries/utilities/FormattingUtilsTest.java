package com.rafagarcia.countries.utilities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Rafa on 14/03/2018.
 */
public class FormattingUtilsTest {

    @Test
    public void formatPopulation() throws Exception {
        String expectedResult = FormattingUtils.formatPopulation("0");
        assertEquals(expectedResult, "uninhabited");

        expectedResult = FormattingUtils.formatPopulation("1");
        assertEquals(expectedResult, "1");

        expectedResult = FormattingUtils.formatPopulation("9999");
        assertEquals(expectedResult, "9999");

        expectedResult = FormattingUtils.formatPopulation("10000");
        assertEquals(expectedResult, "10K");

        expectedResult = FormattingUtils.formatPopulation("14530");
        assertEquals(expectedResult, "14.5K");

        expectedResult = FormattingUtils.formatPopulation("999999");
        assertEquals(expectedResult, "999.9K");

        expectedResult = FormattingUtils.formatPopulation("1000000");
        assertEquals(expectedResult, "1M");

        expectedResult = FormattingUtils.formatPopulation("1423263");
        assertEquals(expectedResult, "1.4M");
    }

}