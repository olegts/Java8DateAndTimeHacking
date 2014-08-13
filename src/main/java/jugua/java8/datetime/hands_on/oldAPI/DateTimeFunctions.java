package jugua.java8.datetime.hands_on.oldAPI;

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
 * using old < Java8 API
 *
 * @author Oleg Tsal-Tsalko
 */
public class DateTimeFunctions {

    public static Date aDate(int year, int month, int day) {
        return new GregorianCalendar(year, month-1, day).getTime();
    }

    public static Date stringToDate(String date, String formatPattern) throws ParseException {
        return new SimpleDateFormat(formatPattern).parse(date);
    }

    public static Date anOldDateTime(String time, DateFormat dateFormat) throws ParseException {
        return dateFormat.parse(time);
    }

    public static String dateToOutputFormat(Date date, String formatPattern) {
        return new SimpleDateFormat(formatPattern).format(date);
    }

    public static Calendar toCalendar(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    public static Date truncateDateWithTimeToDateOnly(Date dateWithTime) {
        Calendar calendar = toCalendar(dateWithTime);
        calendar.set(HOUR_OF_DAY, 0);
        calendar.clear(MINUTE);
        calendar.clear(SECOND);
        calendar.clear(MILLISECOND);
        return calendar.getTime();
    }

    public static Date setTimeToDate(Date date, String time, DateFormat timeFormat) throws ParseException {
        Calendar timeCalendar = toCalendar(timeFormat.parse(time));
        Calendar dateTimeCalendar = toCalendar(date);
        dateTimeCalendar.set(HOUR_OF_DAY, timeCalendar.get(HOUR_OF_DAY));
        dateTimeCalendar.set(MINUTE, timeCalendar.get(MINUTE));
        dateTimeCalendar.set(SECOND, timeCalendar.get(SECOND));
        dateTimeCalendar.set(MILLISECOND, timeCalendar.get(MILLISECOND));
        return dateTimeCalendar.getTime();
    }

    //It works only for UTC dates. It doesn't take into account daylight savings.
    public static int daysDiff(Date date1, Date date2){
        Date truncatedDate1 = truncateDateWithTimeToDateOnly(date1);
        Date truncatedDate2 = truncateDateWithTimeToDateOnly(date2);
        long diffInMillies = truncatedDate2.getTime() - truncatedDate1.getTime();
        return (int)TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }

    public static int yearOf(Date date){
        Calendar calendar = toCalendar(date);
        return calendar.get(YEAR);
    }

    public static int monthOf(Date date){
        Calendar calendar = toCalendar(date);
        return calendar.get(MONTH)+1;
    }

    public static int dayOf(Date date){
        Calendar calendar = toCalendar(date);
        return calendar.get(DAY_OF_MONTH);
    }

    public static boolean isWorkingDay(Date date){
        Calendar calendar = toCalendar(date);
        return calendar.get(DAY_OF_WEEK) != SUNDAY && calendar.get(DAY_OF_WEEK) != SATURDAY;
    }

    public static Date addDaysToGivenDate(Date date, int numberOfDays){
        Calendar calendar = toCalendar(date);
        calendar.add(DAY_OF_MONTH, numberOfDays);
        return calendar.getTime();
    }

    public static Date addMonthsToGivenDate(Date date, int numberOfMonths){
        Calendar calendar = toCalendar(date);
        calendar.add(MONTH, numberOfMonths);
        return calendar.getTime();
    }

    public static Date addYearsToGivenDate(Date date, int numberOfYears){
        Calendar calendar = toCalendar(date);
        calendar.add(YEAR, numberOfYears);
        return calendar.getTime();
    }

    public static Date addMinutesToGivenTime(Date time, int numberOfMinutes){
        Calendar calendar = toCalendar(time);
        calendar.add(MINUTE, numberOfMinutes);
        return calendar.getTime();
    }

    public static Date addSecondsToGivenTime(Date time, int numberOfSeconds){
        Calendar calendar = toCalendar(time);
        calendar.add(MINUTE, numberOfSeconds);
        return calendar.getTime();
    }

    public static Date anOldDateTimeInTimeZone(String dateTime, String dateTimeFormat, TimeZone timeZone) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);//Formatter is aware of time zone!
        dateFormat.setTimeZone(timeZone);
        return dateFormat.parse(dateTime);
    }

    public static Calendar anOldCalendarInTimeZone(String dateTime, String dateTimeFormat, TimeZone timeZone) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);
        dateFormat.setTimeZone(timeZone);//Formatter is aware of time zone!
        Date dateTimeObject = dateFormat.parse(dateTime);
        Calendar calendar = new GregorianCalendar(timeZone);//Second time setUp zone!
        calendar.setTime(dateTimeObject);
        return calendar;
    }

    public static Calendar transformCalendarToUTC(Calendar calendar) throws ParseException {
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return calendar;
    }

    public static Calendar transformCalendarToNewTimeZone(Calendar calendar, String timeZone) throws ParseException {
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        return calendar;
    }

    public static boolean isLeapYear(Date date){
        GregorianCalendar calendar = (GregorianCalendar)toCalendar(date);
        return calendar.isLeapYear(calendar.get(YEAR));//Why it's not static method? And better don't look inside.
    }

    public static int lengthOfMonth(Date date){
        Calendar calendar = toCalendar(date);
        return calendar.getActualMaximum(DAY_OF_MONTH);
    }

    public static Calendar adjustCalendarToLastDayOfAMonth(Calendar calendar){
        calendar.set(DAY_OF_MONTH, lengthOfMonth(calendar.getTime()));
        return calendar;
    }

    public static Calendar adjustCalendarToNextTuesday(Calendar calendar){
        calendar.add(WEEK_OF_MONTH, 1);
        calendar.set(DAY_OF_WEEK, Calendar.TUESDAY);
        return calendar;
    }
}
