package com.rafagarcia.countries.utilities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void formatArea() throws Exception {
        String expectedResult = FormattingUtils.formatArea("-10");
        assertEquals(expectedResult, "0 m²");

        expectedResult = FormattingUtils.formatArea("0");
        assertEquals(expectedResult, "0 m²");

        expectedResult = FormattingUtils.formatArea("999");
        assertEquals(expectedResult, "999 m²");

        expectedResult = FormattingUtils.formatArea("1000");
        assertEquals(expectedResult, "1 km²");

        expectedResult = FormattingUtils.formatArea("14530");
        assertEquals(expectedResult, "14.5 km²");

        expectedResult = FormattingUtils.formatArea("999999");
        assertEquals(expectedResult, "999.9 km²");

        expectedResult = FormattingUtils.formatArea("1000000");
        assertEquals(expectedResult, "1M km²");

        expectedResult = FormattingUtils.formatArea("1423263");
        assertEquals(expectedResult, "1.4M km²");
    }

}