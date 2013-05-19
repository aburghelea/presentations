package ro.teamnet.presentation.workingdays;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.Date;

/**
 * @author Alexandru Burghelea
 * @since 9/21/12
 */

@SuppressWarnings("deprecation")
@RunWith(BlockJUnit4ClassRunner.class)
public abstract class GenericWorkingDaysCounterTest {
    WorkingDaysCounter daysCounter;

    @Test
    public void testCountDaysBetweenSameInstant() throws Exception {
        Date from = new Date(Date.parse("Fr, 21 Sep 2012 00:00:00"));
        Date to = new Date(Date.parse("Fr, 21 Sep 2012 00:00:00"));

        int actualCout = daysCounter.countWorkingDaysBetween(from, to);
        int expected = 0;

        Assert.assertEquals("Numarul de zile numarat nu corespunde, pentru acceasi instanta de timp", expected, actualCout);
    }

    @Test
    public void testCountDaysBetweenSameDayFromPreviousToTo() throws Exception {
        Date from = new Date(Date.parse("Fr, 21 Sep 2012 00:00:00"));
        Date to = new Date(Date.parse("Fr, 21 Sep 2012 21:00:00"));

        int actualCout = daysCounter.countWorkingDaysBetween(from, to);
        int expected = 0;

        Assert.assertEquals("Numarul de zile numarat nu corespunde, pentru acceasi zi", expected, actualCout);
    }

    @Test
    public void testCountDaysBetweenToPreviousToFrom() throws Exception {
        Date from = new Date(Date.parse("Fr, 21 Sep 2012 21:00:00"));
        Date to = new Date(Date.parse("We, 20 Sep 2012 00:00:00"));

        int actualCout = daysCounter.countWorkingDaysBetween(from, to);
        int expected = 1;

        Assert.assertEquals("Numarul de zile numarat nu corespunde, pentru zile diferite (from > to)", expected, actualCout);
    }

    @Test
    public void testCountDaysInConsecutiveDates() throws Exception {
        Date from = new Date(Date.parse("Fr, 21 Sep 2012 00:00:00"));
        Date to = new Date(Date.parse("Fr, 22 Sep 2012 00:00:00"));

        int actualCout = daysCounter.countWorkingDaysBetween(from, to);
        int expected = 1;

        Assert.assertEquals("Numarul de zile numarat nu corespunde, pentru acceasi instanta de timp", expected, actualCout);
    }

    @Test
    public void testCountDatesInConsecutiveWeeks() throws Exception {
        Date from = new Date(Date.parse("Mon, 3 Sep 2012 00:00:00"));
        Date to = new Date(Date.parse("Mon, 10 Sep 2012 00:00:00"));

        int actualCout = daysCounter.countWorkingDaysBetween(from, to);
        int expected = 5;

        Assert.assertEquals("Numarul de zile numarat nu corespunde, saptamani consecutive", expected, actualCout);
    }

    @Test
    public void testCountDaysBetweenDifferentMonths() throws Exception {
        Date from = new Date(Date.parse("Th, 27 Sep 2012 00:00:00"));
        Date to = new Date(Date.parse("Fr, 5 Oct 2012 00:00:00"));

        int actualCout = daysCounter.countWorkingDaysBetween(from, to);
        int expected = 6;

        Assert.assertEquals("Numarul de zile numarat nu corespunde, pentru luni consecutive", expected, actualCout);
    }

    @Test
    public void testCountDaysBetweenYears() throws Exception {
        Date from = new Date(Date.parse("Tu, 27 Dec 2011 00:00:00"));
        Date to = new Date(Date.parse("Th, 5 Jan 2012 00:00:00"));

        int actualCout = daysCounter.countWorkingDaysBetween(from, to);
        int expected = 7;

        Assert.assertEquals("Numarul de zile numarat nu corespunde, pentru ani consecutivi", expected, actualCout);
    }

    @Test
    public void testCountDaysAtEndOfFebruaryInLeapYear() throws Exception {
        Date from = new Date(Date.parse("Tu, 21 Feb 2012 00:00:00"));
        Date to = new Date(Date.parse("Fr, 2 Mar 2012 00:00:00"));

        int actualCout = daysCounter.countWorkingDaysBetween(from, to);
        int expected = 8;

        Assert.assertEquals("Numarul de zile numarat nu corespunde, pentru an bisesc(sf feb)", expected, actualCout);
    }

    @Test
    public void testCountDaysAtEndOfFebruaryInNonLeapYear() throws Exception {
        Date from = new Date(Date.parse("Mo, 21 Feb 2011 00:00:00"));
        Date to = new Date(Date.parse("We, 2 Mar 2011 00:00:00"));

        int actualCout = daysCounter.countWorkingDaysBetween(from, to);
        int expected = 7;

        Assert.assertEquals("Numarul de zile numarat nu corespunde, pentru an nebisec(sf feb)", expected, actualCout);
    }

    @Test
    public void testCountDaysInSameSaturday() throws Exception {
        Date from = new Date(Date.parse("Sa, 1 Sept 2011 00:00:00"));
        Date to = new Date(Date.parse("Sa, 1 Sept 2011 00:00:00"));

        int actualCout = daysCounter.countWorkingDaysBetween(from, to);
        int expected = 0;

        Assert.assertEquals("Numarul de zile numarat nu corespunde, pentru aceasi sambata", expected, actualCout);
    }

    @Test
    public void testCountDaysInSameWeekend() throws Exception {
        Date from = new Date(Date.parse("Sa, 1 Sept 2012 00:00:00"));
        Date to = new Date(Date.parse("Su, 2 Sept 2012 00:00:00"));

        int actualCout = daysCounter.countWorkingDaysBetween(from, to);
        int expected = 0;

        Assert.assertEquals("Numarul de zile numarat nu corespunde, pentru acealasi weekend", expected, actualCout);
    }

    @Test
    public void testCountDaysBetweenConsecutiveWeekends() throws Exception {
        Date from = new Date(Date.parse("Sa, 1 Sept 2012 00:00:00"));
        Date to = new Date(Date.parse("Su, 9 Sept 2012 00:00:00"));

        int actualCout = daysCounter.countWorkingDaysBetween(from, to);
        int expected = 5;

        Assert.assertEquals("Numarul de zile numarat nu corespunde, pentru weekenduri consecutive", expected, actualCout);
    }
}
