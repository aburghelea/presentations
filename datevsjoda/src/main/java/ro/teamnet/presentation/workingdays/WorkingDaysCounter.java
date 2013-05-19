package ro.teamnet.presentation.workingdays;

import java.util.Date;

/**
 * @author Alexandru Burghelea
 * @since 9/21/12
 */
public interface WorkingDaysCounter {

    /**
     * Counts days between two dates (regardless of timezone/ and time).
     * If from is after to, the dates are reversed.
     *
     * @param from start date for counting (inclusive count)
     * @param to   end date for counting (exclusive)
     * @return number of working days between the dates
     */
    int countWorkingDaysBetween(Date from, Date to);
}