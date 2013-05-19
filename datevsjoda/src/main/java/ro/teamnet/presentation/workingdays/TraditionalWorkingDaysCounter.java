package ro.teamnet.presentation.workingdays;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Alexandru Burghelea
 * @since 9/21/12
 */
public class TraditionalWorkingDaysCounter implements WorkingDaysCounter {

    /**
     * Counts days between two dates (regardless of timezone/ and time).
     * If from is after to, the dates are reversed.
     *
     * @param from start date for counting (inclusive count)
     * @param to   end date for counting (exclusive)
     * @return number of working days between the dates
     */
    @Override
    public int countWorkingDaysBetween(Date from, Date to) {
        from = resetTime(from);
        to = resetTime(to);

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(from);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(to);

        int workDays = 0;

        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(to);
            endCal.setTime(from);
        }

        while (startCal.getTimeInMillis() < endCal.getTimeInMillis()) {
            if (!isWeekendDay(startCal.get(Calendar.DAY_OF_WEEK))) {
                ++workDays;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        }

        return workDays;
    }

    /**
     * Check if week day index reprezents a weekend day.
     *
     * @param dayOfWeek Weekday index
     * @return <code>true</code> if the day is SATURDAR or SUNDAY,
     *         false otherwise
     */
    private boolean isWeekendDay(int dayOfWeek) {
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }

    /**
     * Creates a date with the time set to 00:00:00.
     *
     * @param date initial date
     * @return the date with the time set to 00:00:00
     */
    private Date resetTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }
}


