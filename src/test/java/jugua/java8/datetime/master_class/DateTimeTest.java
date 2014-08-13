package jugua.java8.datetime.master_class;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static java.time.format.DateTimeFormatter.ofPattern;
import static jugua.java8.datetime.master_class.DateTimeFunctions.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * DateTimeHacking
 *
 * Each test represents some small date/time related task to be done using
 * both old < Java8 API and new shiny JSR-310 Java8 API
 * to feel the difference and get familiar with new Date&Time API
 *
 * @author Oleg Tsal-Tsalko
 */
public class DateTimeTest {
    @Test
    public void shouldShowCurrentDateInUserFriendlyFormat() throws Exception {
        //Old style
        Date currentDate = new Date();
        System.out.println("Default date format: "+currentDate);
        System.out.println("Simple date format using formatter: "+
                new SimpleDateFormat("yyyy-MM-dd").format(currentDate));
        //New style
        System.out.println("Default LocalDate format: "+LocalDate.now());
    }

    @Test
    public void shouldShowCurrentTimeInUserFriendlyFormat() throws Exception {
        //Old style
        Date currentDate = new Date();
        System.out.println("Default date format: "+currentDate);
        System.out.println("Simple time format using formatter: "+
                new SimpleDateFormat("hh:mm:ss").format(currentDate));
        //New style
        System.out.println("Default LocalTime format: "+ LocalTime.now());
    }

    @Test
    public void shouldCreateParticularDateObject() throws Exception {
        Date oldDateObject = aDate(1987, 6, 10);
        LocalDate newDateObject = aLocalDate(1987, 6, 10);

        assertThat(oldDateObject, is(new Date(87, 5, 10)));
        assertThat(newDateObject.toString(), is("1987-06-10"));
    }

    @Test
    public void shouldCreateDateObjectFromGivenString() throws Exception {
        String givenDate = "10 Jun 1987";

        Date oldDateObject = stringToDate(givenDate, "dd MMM yyyy");
        LocalDate newDateObject = stringToLocalDate(givenDate, "dd MMM yyyy");

        assertThat(oldDateObject, is(aDate(1987, 6, 10)));
        assertThat(newDateObject, is(aLocalDate(1987, 6, 10)));
    }

    @Test
    public void shouldCreateObjectRepresentingParticularTime() throws Exception {
        //No representation of time only before Java 8!
        Date oldDateTimeObject = anOldDateTime("13:30", new SimpleDateFormat("HH:mm"));
        LocalTime newTimeObject = aNewTime("13:30", ofPattern("HH:mm"));

        System.out.println("Date object representing time: "+oldDateTimeObject);
        System.out.println("LocalTime representation: "+newTimeObject);

        assertThat(oldDateTimeObject.getHours(), is(13));
        assertThat(oldDateTimeObject.getMinutes(), is(30));
        assertThat(newTimeObject.toString(), is("13:30"));
    }

    @Test
    public void shouldOutputDateInGivenFormat() throws Exception {
        Date givenDate = aDate(1987, 6, 10);
        LocalDate givenLocalDate = aLocalDate(1987, 6, 10);
        String givenDesiredFormat = "dd MMM yyyy";

        assertThat(dateToOutputFormat(givenDate, givenDesiredFormat), is("10 Jun 1987"));
        assertThat(dateToOutputFormat(givenLocalDate, givenDesiredFormat), is("10 Jun 1987"));
    }

    @Test
    public void shouldTruncateDateAndTimeObjectToSimpleDate() throws Exception {
        //No representation of just date before Java 8!
        Date givenOldDateWithTimeObject = stringToDate("10/06/1987 12:00:01", "dd/MM/yyyy HH:mm:ss");
        LocalDateTime givenNewDateWithTimeObject = stringToDateWithTime("10/06/1987 12:00:01", "dd/MM/yyyy HH:mm:ss");

        Date truncatedDate = truncateDateWithTimeToDateOnly(givenOldDateWithTimeObject);
        LocalDate truncatedLocalDate = truncateDateWithTimeToDateOnly(givenNewDateWithTimeObject);

        assertThat(truncatedDate, is(aDate(1987, 6, 10)));
        assertThat(truncatedLocalDate, is(aLocalDate(1987, 6, 10)));
    }

    @Test
    public void shouldAddTimeToDateObject() throws Exception {
        Date givenDateObject = aDate(1987, 6, 10);
        LocalDate givenLocalDateObject = aLocalDate(1987, 6, 10);

        Date dateWithTime = setTimeToDate(givenDateObject, "13:30", new SimpleDateFormat("HH:mm"));
        LocalDateTime localDateWithTime = setTimeToDate(givenLocalDateObject, "13:30", ofPattern("HH:mm"));

        assertThat(dateWithTime, is(stringToDate("10/06/1987 13:30", "dd/MM/yyyy HH:mm")));
        assertThat(localDateWithTime, is(stringToDateWithTime("10/06/1987 13:30", "dd/MM/yyyy HH:mm")));
    }

    @Test
    public void shouldCalculateDaysDiffBetweenDates() throws Exception {
        assertThat(daysDiff(aDate(2014, 5, 30), aDate(2014, 6, 10)), is(11));
        assertThat(daysDiff(aDate(2014, 5, 10), aDate(2014, 6, 30)), is(51));
        assertThat(daysDiff(aLocalDate(2014, 5, 30), aLocalDate(2014, 6, 10)), is(11));
        assertThat(daysDiff(aLocalDate(2014, 5, 10), aLocalDate(2014, 6, 30)), is(51));
    }

    @Test
    public void shouldGetYearOfDate() throws Exception {
        Date givenDateObject = aDate(1987, 6, 10);
        LocalDate givenLocalDateObject = aLocalDate(1987, 6, 10);

        assertThat(yearOf(givenDateObject), is(1987));
        assertThat(yearOf(givenLocalDateObject), is(1987));
    }

    @Test
    public void shouldGetMonthOfDate() throws Exception {
        Date givenDateObject = aDate(1987, 6, 10);
        LocalDate givenLocalDateObject = aLocalDate(1987, 6, 10);

        assertThat(monthOf(givenDateObject), is(6));
        assertThat(monthOf(givenLocalDateObject), is(6));
    }

    @Test
    public void shouldGetDayOfDate() throws Exception {
        Date givenDateObject = aDate(1987, 6, 10);
        LocalDate givenLocalDateObject = aLocalDate(1987, 6, 10);

        assertThat(dayOf(givenDateObject), is(10));
        assertThat(dayOf(givenLocalDateObject), is(10));
    }

    @Test
    public void shouldAddParticularNumberOfDaysToGivenDate() throws Exception {
        Date givenDateObject = aDate(1987, 5, 30);
        LocalDate givenLocalDateObject = aLocalDate(1987, 5, 30);

        assertThat(addDaysToGivenDate(givenDateObject, 2), is(aDate(1987, 6, 1)));
        assertThat(addDaysToGivenLocalDate(givenLocalDateObject, 2), is(aLocalDate(1987, 6, 1)));
    }

    @Test
    public void shouldAddParticularNumberOfMonthsToGivenDate() throws Exception {
        Date givenDateObject = aDate(1987, 5, 31);
        LocalDate givenLocalDateObject = aLocalDate(1987, 5, 31);

        assertThat(addMonthsToGivenDate(givenDateObject, 1), is(aDate(1987, 6, 30)));
        assertThat(addMonthsToGivenLocalDate(givenLocalDateObject, 1), is(aLocalDate(1987, 6, 30)));
    }

    @Test
    public void shouldAddOneYearToGivenTime() throws Exception {
        Date givenDateObject = aDate(2012, 2, 29);
        LocalDate givenLocalDateObject = aLocalDate(2012, 2, 29);

        assertThat(addYearsToGivenDate(givenDateObject, 1), is(aDate(2013, 2, 28)));
        assertThat(addYearsToGivenLocalDate(givenLocalDateObject, 1), is(aLocalDate(2013, 2, 28)));
    }

    @Test
    public void shouldAddOneMinuteToGivenTime() throws Exception {
        //No representation of time only before Java 8!
        Date oldDateTimeObject = anOldDateTime("13:55:10", new SimpleDateFormat("HH:mm:ss"));
        LocalTime newTimeObject = aNewTime("13:55:10", ofPattern("HH:mm:ss"));

        Date expectedDateTimeObject = anOldDateTime("14:05:10", new SimpleDateFormat("HH:mm:ss"));
        LocalTime expectedTimeObject = aNewTime("14:05:10", ofPattern("HH:mm:ss"));

        assertThat(addMinutesToGivenTime(oldDateTimeObject, 10), is(expectedDateTimeObject));
        assertThat(addMinutesToGivenLocalTime(newTimeObject, 10), is(expectedTimeObject));
    }

    @Test
    public void shouldAddOneSecondToGivenTime() throws Exception {
        //No representation of time only before Java 8!
        Date oldDateTimeObject = anOldDateTime("13:59:55", new SimpleDateFormat("HH:mm:ss"));
        LocalTime newTimeObject = aNewTime("13:59:55", ofPattern("HH:mm:ss"));

        Date expectedDateTimeObject = anOldDateTime("14:00:05", new SimpleDateFormat("HH:mm:ss"));
        LocalTime expectedTimeObject = aNewTime("14:00:05", ofPattern("HH:mm:ss"));

        assertThat(addSecondsToGivenTime(oldDateTimeObject, 10), is(expectedDateTimeObject));
        assertThat(addSecondsToGivenLocalTime(newTimeObject, 10), is(expectedTimeObject));
    }

    @Test
    public void shouldCreateDateTimeObjectInParticularTimeZone() throws Exception {
        Date dateTime = anOldDateTimeInTimeZone("10/06/1987 13:00", "dd/MM/yyyy HH:mm", TimeZone.getTimeZone("Europe/London"));
        System.out.println("Old Date object representing 1pm London time: "+dateTime);
        System.out.println("1pm London time Date#getHours() = "+dateTime.getHours());
        Calendar calendar = anOldCalendarInTimeZone("10/06/1987 13:00", "dd/MM/yyyy HH:mm", TimeZone.getTimeZone("Europe/London"));
        System.out.println("Calendar object at least doesn't do any stupid normalizations and returns expected time in hours: "+calendar.get(Calendar.HOUR_OF_DAY));
        assertThat(calendar.get(Calendar.HOUR_OF_DAY), is(13));

        ZonedDateTime zonedDateTime = aNewZonedDateTime("10/06/1987 13:00", "dd/MM/yyyy HH:mm", ZoneId.of("Europe/London"));
        System.out.println("New ZonedDateTime object representing 1pm London time: " + zonedDateTime);
        assertThat(zonedDateTime.getHour(), is(13));
    }

    @Test
    public void showGreatAmbiguityOfTimeZoneRules() throws Exception {

        Date kievTime = anOldDateTimeInTimeZone("10/06/1987 13:00", "dd/MM/yyyy HH:mm", TimeZone.getTimeZone("Europe/Kiev"));
        System.out.println("1pm Kiev time: "+kievTime);
        Date londonTime = anOldDateTimeInTimeZone("10/06/1987 13:00", "dd/MM/yyyy HH:mm", TimeZone.getTimeZone("Europe/London"));
        System.out.println("1pm London time: "+londonTime);

        /*System.out.println("---------------------------------------------------------------------------");

        Date kievTimeNow = anOldDateTimeInTimeZone("10/06/2014 13:00", "dd/MM/yyyy HH:mm", TimeZone.getTimeZone("Europe/Kiev"));
        System.out.println("1pm Kiev time: "+kievTimeNow);
        Date londonTimeNow = anOldDateTimeInTimeZone("10/06/2014 13:00", "dd/MM/yyyy HH:mm", TimeZone.getTimeZone("Europe/London"));
        System.out.println("1pm London time: "+londonTimeNow);

        System.out.println("---------------------------------------------------------------------------");

        ZonedDateTime kievTimeIn1987 = aNewZonedDateTime("10/06/1987 13:00", "dd/MM/yyyy HH:mm", ZoneId.of("Europe/Kiev"));
        System.out.println("1pm Kiev time in 1987: " + kievTimeIn1987);
        ZonedDateTime kievTimeIn2014 = aNewZonedDateTime("10/06/2014 13:00", "dd/MM/yyyy HH:mm", ZoneId.of("Europe/Kiev"));
        System.out.println("1pm Kiev time in 2014: " + kievTimeIn2014);*/
    }

    @Test
    public void shouldTransformDateAndTimeInParticularTimeZoneToUTC() throws Exception {
        Calendar calendar = anOldCalendarInTimeZone("10/06/1987 13:00", "dd/MM/yyyy HH:mm", TimeZone.getTimeZone("Europe/London"));
        ZonedDateTime zonedDateTime = aNewZonedDateTime("10/06/1987 13:00", "dd/MM/yyyy HH:mm", ZoneId.of("Europe/London"));

        assertThat(transformToUTC(zonedDateTime).getHour(), is(12));
        assertThat(transformCalendarToUTC(calendar).get(Calendar.HOUR_OF_DAY), is(12));
    }

    @Test
    public void showGreatFragilityOfCalendarImplementation() throws Exception{
        //1pm London time
        Calendar calendar = anOldCalendarInTimeZone("10/06/1987 13:00", "dd/MM/yyyy HH:mm", TimeZone.getTimeZone("Europe/London"));
        System.out.println("Current time in London time zone: "+calendar.get(Calendar.HOUR_OF_DAY));
        /*System.out.println("Lets reset time to same value as it was before - 13pm. Nothing special. It shouldn't affect us, right?");
        calendar.set(Calendar.HOUR_OF_DAY, 13);*/
        //System.out.println("Current time in London time zone: "+calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("Time after transforming to UTC: "+transformCalendarToUTC(calendar).get(Calendar.HOUR_OF_DAY));
    }

    @Test
    public void shouldTransformOneTimeZoneDateAndTimeToAnother() throws Exception {
        Calendar calendar = anOldCalendarInTimeZone("10/06/2014 13:00", "dd/MM/yyyy HH:mm", TimeZone.getTimeZone("Europe/London"));
        ZonedDateTime zonedDateTime = aNewZonedDateTime("10/06/2014 13:00", "dd/MM/yyyy HH:mm", ZoneId.of("Europe/London"));

        assertThat(transformToNewTimeZone(zonedDateTime, "Europe/Kiev").getHour(), is(15));
        assertThat(transformCalendarToNewTimeZone(calendar, "Europe/Kiev").get(Calendar.HOUR_OF_DAY), is(15));
    }

    @Test
    public void shouldIdentifyLeapYear() throws Exception {
        Date notLeapYearDate = aDate(2014, 6, 10);
        Date leapYearDate = aDate(2012, 6, 10);

        LocalDate notLeapYearLocalDate = aLocalDate(2014, 6, 10);
        LocalDate leapYearLocalDate = aLocalDate(2012, 6, 10);

        assertTrue(isLeapYear(leapYearDate));
        assertFalse(isLeapYear(notLeapYearDate));
        assertTrue(isLeapYear(leapYearLocalDate));
        assertFalse(isLeapYear(notLeapYearLocalDate));
    }

    @Test
    public void shouldIdentifyWeekendDay() throws Exception {
        Date weekendDay = aDate(2014, 6, 8);
        Date workingDay = aDate(2014, 6, 9);

        LocalDate weekendLocalDay = aLocalDate(2014, 6, 8);
        LocalDate workingLocalDay = aLocalDate(2014, 6, 9);

        assertTrue(isWorkingDay(workingDay));
        assertFalse(isWorkingDay(weekendDay));
        assertTrue(isWorkingDay(workingLocalDay));
        assertFalse(isWorkingDay(weekendLocalDay));
    }

    @Test
    public void shouldCalculateLengthOfCurrentMonth() throws Exception {
        Date notLeapYearDate = aDate(2014, 2, 10);
        Date leapYearDate = aDate(2012, 2, 10);

        LocalDate notLeapYearLocalDate = aLocalDate(2014, 2, 10);
        LocalDate leapYearLocalDate = aLocalDate(2012, 2, 10);

        assertThat(lengthOfMonth(notLeapYearDate), is(28));
        assertThat(lengthOfMonth(leapYearDate), is(29));
        assertThat(lengthOfMonth(notLeapYearLocalDate), is(28));
        assertThat(lengthOfMonth(leapYearLocalDate), is(29));
    }

    @Test
    public void shouldAdjustDateToLastDayOfAMonth() throws Exception {
        Calendar notLeapYearDate = toCalendar(aDate(2014, 2, 10));
        Calendar leapYearDate = toCalendar(aDate(2012, 2, 10));

        LocalDate notLeapYearLocalDate = aLocalDate(2014, 2, 10);
        LocalDate leapYearLocalDate = aLocalDate(2012, 2, 10);

        assertThat(adjustCalendarToLastDayOfAMonth(notLeapYearDate).get(Calendar.DAY_OF_MONTH), is(28));
        assertThat(adjustCalendarToLastDayOfAMonth(leapYearDate).get(Calendar.DAY_OF_MONTH), is(29));
        assertThat(adjustDateToLastDayOfAMonth(notLeapYearLocalDate).getDayOfMonth(), is(28));
        assertThat(adjustDateToLastDayOfAMonth(leapYearLocalDate).getDayOfMonth(), is(29));
    }

    @Test
    public void shouldAdjustDateToNextTuesday() throws Exception {
        Calendar calendar = toCalendar(aDate(2014, 12, 29));
        LocalDate localDate = aLocalDate(2014, 6, 24);

        Calendar adjustedCalendar = adjustCalendarToNextTuesday(calendar);
        LocalDate adjustedLocalDate = adjustDateToNextTuesday(localDate);

        assertThat(adjustedCalendar.get(Calendar.DAY_OF_MONTH), is(6));
        assertThat(adjustedCalendar.get(Calendar.MONTH), is(0));
        assertThat(adjustedLocalDate.getDayOfMonth(), is(1));
        assertThat(adjustedLocalDate.getMonth(), is(Month.JULY));
    }

    //No analog of Duration class in old API
    @Test
    public void shouldShiftTimeOnSpecifiedDuration() throws Exception {
        Duration duration = Duration.ofMinutes(30);
        LocalTime localTime = aNewTime("13:30", ofPattern("HH:mm"));
        LocalTime shiftedTime = localTime.plus(duration);
        assertThat(shiftedTime.toString(), is("14:00"));
    }

    //No analog of Period class in old API
    @Test
    public void shouldShiftDateOnSpecifiedPeriod() throws Exception {
        Period period = Period.ofDays(2);
        LocalDate localDate = aLocalDate(1987, 5, 30);
        assertThat(localDate.plus(period), is(aLocalDate(1987, 6, 1)));
    }

    //Shows custom TemporalAdjuster usage
    @Test
    public void shouldAddParticularNumberOfWorkingDaysToGivenDate() throws Exception {
        LocalDate localDate = aLocalDate(2014, 8, 10);
        assertThat(adjustWorkingDays(localDate, 10), is(aLocalDate(2014, 8, 22)));
    }
}
