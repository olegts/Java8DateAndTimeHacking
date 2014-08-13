package jugua.java8.datetime.hands_on.newAPI;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Calendar.*;

/**
 * DateTimeHacking
 *
 * This class represents set of date/time functions to solve small day to day problems
 * using new shiny JSR-310 Java8 API
 *
 * @author Oleg Tsal-Tsalko
 */
public class DateTimeFunctions {

    public static LocalDate aLocalDate(int year, int month, int day) {
        //TODO
        return null;
    }

    public static LocalDate stringToLocalDate(String date, String formatPattern) {
        //TODO
        return null;
    }

    public static LocalDateTime stringToDateWithTime(String dateWithTime, String formatPattern) {
        //TODO
        return null;
    }

    public static LocalTime aNewTime(String time, DateTimeFormatter dateTimeFormatter) throws ParseException {
        //TODO
        return null;
    }

    public static String dateToOutputFormat(LocalDate date, String formatPattern) {
        //TODO
        return null;
    }

    public static LocalDate truncateDateWithTimeToDateOnly(LocalDateTime dateWithTime) {
        //TODO
        return null;
    }

    public static LocalDateTime setTimeToDate(LocalDate date, String time, DateTimeFormatter timeFormatter) {
        //TODO
        return null;
    }

    public static int daysDiff(LocalDate date1, LocalDate date2){
        //TODO
        return -1;
    }

    public static int yearOf(LocalDate date){
        //TODO
        return -1;
    }

    public static int monthOf(LocalDate date){
        //TODO
        return -1;
    }

    public static int dayOf(LocalDate date){
        //TODO
        return -1;
    }

    public static boolean isWorkingDay(LocalDate date){
        //TODO
        return false;
    }

    public static LocalDate addDaysToGivenLocalDate(LocalDate date, int numberOfDays){
        //TODO
        return null;
    }

    public static LocalDate addMonthsToGivenLocalDate(LocalDate date, int numberOfMonths){
        //TODO
        return null;
    }

    public static LocalDate addYearsToGivenLocalDate(LocalDate date, int numberOfYears){
        //TODO
        return null;
    }

    public static LocalTime addMinutesToGivenLocalTime(LocalTime time, int numberOfMinutes){
        //TODO
        return null;
    }

    public static LocalTime addSecondsToGivenLocalTime(LocalTime time, int numberOfSeconds){
        //TODO
        return null;
    }

    public static ZonedDateTime aNewZonedDateTime(String dateTime, String dateTimeFormat, ZoneId timeZone) throws ParseException {
        //TODO
        return null;
    }

    public static ZonedDateTime transformToUTC(ZonedDateTime dateTime) throws ParseException {
        //TODO
        return null;
    }

    public static ZonedDateTime transformToNewTimeZone(ZonedDateTime dateTime, String timeZone) throws ParseException {
        //TODO
        return null;
    }

    public static boolean isLeapYear(LocalDate date){
        //TODO
        return false;
    }

    public static int lengthOfMonth(LocalDate date){
        //TODO
        return -1;
    }

    public static LocalDate adjustDateToLastDayOfAMonth(LocalDate date){
        //TODO
        return null;
    }

    public static LocalDate adjustDateToNextTuesday(LocalDate date){
        //TODO
        return null;
    }

    public static LocalDate adjustWorkingDays(LocalDate date, int workingDays){
        //TODO
        return null;
    }

    public static TemporalAdjuster workingDaysAhead(int workingDays){
        //TODO
        return null;
    }
}
