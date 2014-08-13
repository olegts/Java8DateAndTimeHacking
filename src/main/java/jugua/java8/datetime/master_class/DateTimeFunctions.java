package jugua.java8.datetime.master_class;

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
 * using both old < Java8 API and new shiny JSR-310 Java8 API to be able to compare
 * two approaches
 *
 * @author Oleg Tsal-Tsalko
 */
public class DateTimeFunctions {

    public @interface PriorJava8 {}
    public @interface Java8 {}

    @PriorJava8
    public static Date aDate(int year, int month, int day) {
        return new GregorianCalendar(year, month-1, day).getTime();
    }

    @Java8
    public static LocalDate aLocalDate(int year, int month, int day) {
        return LocalDate.of(year, month, day);
    }

    @PriorJava8
    public static Date stringToDate(String date, String formatPattern) throws ParseException {
        return new SimpleDateFormat(formatPattern).parse(date);
    }

    @Java8
    public static LocalDate stringToLocalDate(String date, String formatPattern) {
        return LocalDate.parse(date, ofPattern(formatPattern));
    }

    @Java8
    public static LocalDateTime stringToDateWithTime(String dateWithTime, String formatPattern) {
        return LocalDateTime.parse(dateWithTime, ofPattern(formatPattern));
    }

    @PriorJava8
    public static Date anOldDateTime(String time, DateFormat dateFormat) throws ParseException {
        return dateFormat.parse(time);
    }

    @Java8
    public static LocalTime aNewTime(String time, DateTimeFormatter dateTimeFormatter) throws ParseException {
        return LocalTime.parse(time, dateTimeFormatter);
    }

    @PriorJava8
    public static String dateToOutputFormat(Date date, String formatPattern) {
        return new SimpleDateFormat(formatPattern).format(date);
    }

    @Java8
    public static String dateToOutputFormat(LocalDate date, String formatPattern) {
        return date.format(ofPattern(formatPattern));
    }

    @PriorJava8
    public static Calendar toCalendar(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    @PriorJava8
    public static Date truncateDateWithTimeToDateOnly(Date dateWithTime) {
        Calendar calendar = toCalendar(dateWithTime);
        calendar.set(HOUR_OF_DAY, 0);
        calendar.clear(MINUTE);
        calendar.clear(SECOND);
        calendar.clear(MILLISECOND);
        return calendar.getTime();
    }

    @Java8
    public static LocalDate truncateDateWithTimeToDateOnly(LocalDateTime dateWithTime) {
        return dateWithTime.toLocalDate();
    }

    @PriorJava8
    public static Date setTimeToDate(Date date, String time, DateFormat timeFormat) throws ParseException {
        Calendar timeCalendar = toCalendar(timeFormat.parse(time));
        Calendar dateTimeCalendar = toCalendar(date);
        dateTimeCalendar.set(HOUR_OF_DAY, timeCalendar.get(HOUR_OF_DAY));
        dateTimeCalendar.set(MINUTE, timeCalendar.get(MINUTE));
        dateTimeCalendar.set(SECOND, timeCalendar.get(SECOND));
        dateTimeCalendar.set(MILLISECOND, timeCalendar.get(MILLISECOND));
        return dateTimeCalendar.getTime();
    }

    @Java8
    public static LocalDateTime setTimeToDate(LocalDate date, String time, DateTimeFormatter timeFormatter) {
        return date.atTime(LocalTime.parse(time, timeFormatter));
    }

    //It works only for UTC dates. It doesn't take into account daylight savings.
    @PriorJava8
    public static int daysDiff(Date date1, Date date2){
        Date truncatedDate1 = truncateDateWithTimeToDateOnly(date1);
        Date truncatedDate2 = truncateDateWithTimeToDateOnly(date2);
        long diffInMillies = truncatedDate2.getTime() - truncatedDate1.getTime();
        return (int)TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }

    @Java8
    public static int daysDiff(LocalDate date1, LocalDate date2){
        //return Period.between(date2, date1).getDays(); //Incorrect solution
        return (int)(date2.toEpochDay()-date1.toEpochDay());
    }

    @PriorJava8
    public static int yearOf(Date date){
        Calendar calendar = toCalendar(date);
        return calendar.get(YEAR);
    }

    @Java8
    public static int yearOf(LocalDate date){
        return date.getYear();
    }

    @PriorJava8
    public static int monthOf(Date date){
        Calendar calendar = toCalendar(date);
        return calendar.get(MONTH)+1;
    }

    @Java8
    public static int monthOf(LocalDate date){
        return date.getMonthValue();
    }

    @PriorJava8
    public static int dayOf(Date date){
        Calendar calendar = toCalendar(date);
        return calendar.get(DAY_OF_MONTH);
    }

    @Java8
    public static int dayOf(LocalDate date){
        return date.getDayOfMonth();
    }

    @PriorJava8
    public static boolean isWorkingDay(Date date){
        Calendar calendar = toCalendar(date);
        return calendar.get(DAY_OF_WEEK) != SUNDAY && calendar.get(DAY_OF_WEEK) != SATURDAY;
    }

    @Java8
    public static boolean isWorkingDay(LocalDate date){
        return date.getDayOfWeek() != DayOfWeek.SUNDAY && date.getDayOfWeek() != DayOfWeek.SATURDAY;
    }

    @PriorJava8
    public static Date addDaysToGivenDate(Date date, int numberOfDays){
        Calendar calendar = toCalendar(date);
        calendar.add(DAY_OF_MONTH, numberOfDays);
        return calendar.getTime();
    }

    @Java8
    public static LocalDate addDaysToGivenLocalDate(LocalDate date, int numberOfDays){
        return date.plusDays(numberOfDays);
    }

    @PriorJava8
    public static Date addMonthsToGivenDate(Date date, int numberOfMonths){
        Calendar calendar = toCalendar(date);
        calendar.add(MONTH, numberOfMonths);
        return calendar.getTime();
    }

    @Java8
    public static LocalDate addMonthsToGivenLocalDate(LocalDate date, int numberOfMonths){
        return date.plusMonths(numberOfMonths);
    }

    @PriorJava8
    public static Date addYearsToGivenDate(Date date, int numberOfYears){
        Calendar calendar = toCalendar(date);
        calendar.add(YEAR, numberOfYears);
        return calendar.getTime();
    }

    @Java8
    public static LocalDate addYearsToGivenLocalDate(LocalDate date, int numberOfYears){
        return date.plusYears(numberOfYears);
    }

    @PriorJava8
    public static Date addMinutesToGivenTime(Date time, int numberOfMinutes){
        Calendar calendar = toCalendar(time);
        calendar.add(MINUTE, numberOfMinutes);
        return calendar.getTime();
    }

    @Java8
    public static LocalTime addMinutesToGivenLocalTime(LocalTime time, int numberOfMinutes){
        return time.plusMinutes(numberOfMinutes);
    }

    @PriorJava8
    public static Date addSecondsToGivenTime(Date time, int numberOfSeconds){
        Calendar calendar = toCalendar(time);
        calendar.add(SECOND, numberOfSeconds);
        return calendar.getTime();
    }

    @Java8
    public static LocalTime addSecondsToGivenLocalTime(LocalTime time, int numberOfSeconds){
        return time.plusSeconds(numberOfSeconds);
    }

    @PriorJava8
    public static Date anOldDateTimeInTimeZone(String dateTime, String dateTimeFormat, TimeZone timeZone) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);//Formatter is aware of time zone!
        dateFormat.setTimeZone(timeZone);
        return dateFormat.parse(dateTime);
    }

    @PriorJava8
    public static Calendar anOldCalendarInTimeZone(String dateTime, String dateTimeFormat, TimeZone timeZone) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);
        dateFormat.setTimeZone(timeZone);//Formatter is aware of time zone!
        Date dateTimeObject = dateFormat.parse(dateTime);
        Calendar calendar = new GregorianCalendar(timeZone);//Second time setUp zone!
        calendar.setTime(dateTimeObject);
        return calendar;
    }

    @Java8
    public static ZonedDateTime aNewZonedDateTime(String dateTime, String dateTimeFormat, ZoneId timeZone) throws ParseException {
        //return ZonedDateTime.parse(dateTime, ofPattern(dateTimeFormat).withZone(timeZone));
        return ZonedDateTime.of(LocalDateTime.parse(dateTime, ofPattern(dateTimeFormat)), timeZone);
    }

    @PriorJava8
    public static Calendar transformCalendarToUTC(Calendar calendar) throws ParseException {
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return calendar;
    }

    @Java8
    public static ZonedDateTime transformToUTC(ZonedDateTime dateTime) throws ParseException {
        return dateTime.withZoneSameInstant(ZoneId.of("UTC"));
    }

    @PriorJava8
    public static Calendar transformCalendarToNewTimeZone(Calendar calendar, String timeZone) throws ParseException {
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
        return calendar;
    }

    @Java8
    public static ZonedDateTime transformToNewTimeZone(ZonedDateTime dateTime, String timeZone) throws ParseException {
        return dateTime.withZoneSameInstant(ZoneId.of(timeZone));
    }

    @PriorJava8
    public static boolean isLeapYear(Date date){
        GregorianCalendar calendar = (GregorianCalendar)toCalendar(date);
        return calendar.isLeapYear(calendar.get(YEAR));//Why it's not static method? And better don't look inside.
    }

    @Java8
    public static boolean isLeapYear(LocalDate date){
        return date.isLeapYear();
    }

    @PriorJava8
    public static int lengthOfMonth(Date date){
        Calendar calendar = toCalendar(date);
        return calendar.getActualMaximum(DAY_OF_MONTH);
    }

    @Java8
    public static int lengthOfMonth(LocalDate date){
        return date.lengthOfMonth();
    }

    @PriorJava8
    public static Calendar adjustCalendarToLastDayOfAMonth(Calendar calendar){
        calendar.set(DAY_OF_MONTH, lengthOfMonth(calendar.getTime()));
        return calendar;
    }

    @Java8
    public static LocalDate adjustDateToLastDayOfAMonth(LocalDate date){
        return date.with(TemporalAdjusters.lastDayOfMonth());
    }

    @PriorJava8
    public static Calendar adjustCalendarToNextTuesday(Calendar calendar){
        calendar.add(WEEK_OF_MONTH, 1);
        calendar.set(DAY_OF_WEEK, Calendar.TUESDAY);
        return calendar;
    }

    @Java8
    public static LocalDate adjustDateToNextTuesday(LocalDate date){
        return date.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
    }

    @Java8
    public static LocalDate adjustWorkingDays(LocalDate date, int workingDays){
        return date.with(workingDaysAhead(workingDays));
    }

    public static TemporalAdjuster workingDaysAhead(int workingDays){
        if (workingDays<=0) throw new UnsupportedOperationException("Working days should be positive number");
        return (temporal) -> {
            int daysShift = workingDays;
            int workingDaysLeft = workingDays;
            int workingDaysLeftInCurrentWeek = DayOfWeek.FRIDAY.getValue() - temporal.get(ChronoField.DAY_OF_WEEK);
            if (workingDaysLeftInCurrentWeek < workingDaysLeft){
                if (workingDaysLeftInCurrentWeek > 0) {
                    workingDaysLeft -= workingDaysLeftInCurrentWeek;
                }else {
                    daysShift += DayOfWeek.SUNDAY.getValue()-temporal.get(ChronoField.DAY_OF_WEEK)-2;
                }
                daysShift += (int)Math.floor(workingDaysLeft/5*2);
                if (workingDaysLeft % 5 > 0){
                    daysShift += 2;
                }
            }
            return temporal.plus(daysShift, ChronoUnit.DAYS);
        };
    }
}
