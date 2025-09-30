/**
 * Unit tests for {@link FormattingUtils} and related classes.
 * <p>
 * These tests ensure that date, time, and currency formatting
 * utilities behave consistently and are not affected by the
 * default system locale.
 * </p>
 */
package com.vaadin.starter.bakery.ui.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.vaadin.starter.bakery.test.FormattingTest;
import com.vaadin.starter.bakery.ui.utils.converters.LocalDateTimeConverter;

public class FormattingUtilsTest extends FormattingTest {

    /**
     * Verifies that {@link FormattingUtils#formatAsCurrency(double)}
     * produces a locale-independent currency string using US formatting
     * (e.g., "$9,876,543.45").
     */
    @Test
    public void formatAsCurrencyShouldBeLocaleIndependent() {
        String result = FormattingUtils.formatAsCurrency(987654345);
        Assertions.assertEquals("$9,876,543.45", result);
    }

    /**
     * Verifies that {@link FormattingUtils#getUiPriceFormatter()} always
     * formats numbers consistently with two decimal places, regardless
     * of locale.
     */
    @Test
    public void getUiPriceFormatterShouldBeLocaleIndependent() {
        String result = FormattingUtils.getUiPriceFormatter().format(9876543);
        Assertions.assertEquals("9876543.00", result);
    }

    /**
     * Ensures that {@link FormattingUtils#SHORT_DAY_FORMATTER} produces
     * a short weekday name and day number in a locale-independent way.
     * Example: "Mon 13".
     */
    @Test
    public void shortDayFormatterShouldBeLocaleIndependent() {
        String result = FormattingUtils.SHORT_DAY_FORMATTER.format(LocalDate.of(2017, 11, 13));
        Assertions.assertEquals("Mon 13", result);
    }

    /**
     * Ensures that {@link FormattingUtils#WEEKDAY_FULLNAME_FORMATTER}
     * outputs the full weekday name consistently across locales.
     * Example: "Friday".
     */
    @Test
    public void weekdayFullDayFormatterShouldBeLocaleIndependent() {
        String result = FormattingUtils.WEEKDAY_FULLNAME_FORMATTER.format(LocalDate.of(2017, 10, 13));
        Assertions.assertEquals("Friday", result);
    }

    /**
     * Ensures that {@link FormattingUtils#MONTH_AND_DAY_FORMATTER}
     * formats month abbreviations and day numbers consistently.
     * Example: "Jun 26".
     */
    @Test
    public void monthAndDayFormatterShouldBeLocaleIndependent() {
        String result = FormattingUtils.MONTH_AND_DAY_FORMATTER.format(LocalDate.of(2015, 6, 26));
        Assertions.assertEquals("Jun 26", result);
    }

    /**
     * Verifies that week numbers calculated via
     * {@link FormattingUtils#WEEK_OF_YEAR_FIELD} are consistent
     * across different locales and date boundaries.
     */
    @Test
    public void weekNumberShouldBeLocaleIndependent() {
        Assertions.assertEquals(getWeek(2017, 9, 3), getWeek(2017, 9, 9));
        Assertions.assertNotEquals(getWeek(2017, 9, 2), getWeek(2017, 9, 4));
        Assertions.assertNotEquals(getWeek(2017, 9, 8), getWeek(2017, 9, 10));
    }

    /**
     * Ensures that {@link FormattingUtils#FULL_DATE_FORMATTER} outputs
     * dates in a locale-independent European format (dd.MM.yyyy).
     * Example: "27.11.2016".
     */
    @Test
    public void fullDateformatterShouldBeLocaleIndependent() {
        String result = FormattingUtils.FULL_DATE_FORMATTER.format(LocalDateTime.of(2016, 11, 27, 22, 15, 33));
        Assertions.assertEquals("27.11.2016", result);
    }

    /**
     * Ensures that {@link FormattingUtils#getFullMonthName(LocalDate)}
     * always returns the English full month name.
     * Example: "August".
     */
    @Test
    public void getFullMonthNameShouldBeLocaleIndependent() {
        String result = FormattingUtils.getFullMonthName(LocalDate.of(2003, 8, 22));
        Assertions.assertEquals("August", result);
    }

    /**
     * Verifies that {@link LocalDateTimeConverter} encodes a
     * {@link LocalDateTime} with both date and time using AM/PM format.
     * Example: "27.11.2016 10:15 PM".
     */
    @Test
    public void timeConverterShouldFormatDateWithAmPm() {
        LocalDateTimeConverter lt = new LocalDateTimeConverter();
        String result = lt.encode(LocalDateTime.of(2016, 11, 27, 22, 15, 33));
        Assertions.assertEquals("27.11.2016 10:15 PM", result);
    }

    /**
     * Helper method to obtain the week number for a given date
     * using {@link FormattingUtils#WEEK_OF_YEAR_FIELD}.
     *
     * @param year  the year of the date
     * @param month the month of the date (1-12)
     * @param day   the day of the month
     * @return the ISO week number of the given date
     */
    private int getWeek(int year, int month, int day) {
        return LocalDate.of(year, month, day).get(FormattingUtils.WEEK_OF_YEAR_FIELD);
    }
}
