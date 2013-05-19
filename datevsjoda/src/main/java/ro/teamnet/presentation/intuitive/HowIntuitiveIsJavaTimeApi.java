package ro.teamnet.presentation.intuitive;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author Alexandru Burghelea
 * @since 9/21/12
 */
public class HowIntuitiveIsJavaTimeApi {

    /**
     * It doesn't even compile
     *
     * @return A suposed formated date (it won't, that's why it's commented out
     */

    /*
     * public String getIncorectSpecificTimeInBruxelles() {
     * Date secondOfFebruary = new Date(2012, 9, 21, 16, 49);
     *
     * TimeZone zone = TimeZone.getTimeZone("Europe/Bruxelles");
     * Calendar calendar = new GregorianCalendar(secondOfFebruary, zone);
     * DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyy HH:mm");
     *
     * return dateFormat.format(calendar);
     * }
     */
    public String getSpecificTimeInBruxelles() {
        /* The year is relative to 1900 */
        int currentYear = 2012 - 1900;
        /* Months are indexed from 0 ( = JANUARY ) */
        int september = 8; //Calendar.SEPTEMBER;
        int today = 21;
        @SuppressWarnings("deprecation") Date secondOfFebruary = new Date(currentYear, september, today, 16, 49);

        /*
         * Bruxelles is the capital of Belgium, the timezone is
         * Europe/Brussels.
         * Java (silently) set GMT if it doesn't recognize the
         * timezone
         */
        TimeZone zone = TimeZone.getTimeZone("Europe/Brussels");

        /*
         * None of the constructors of calendar accepts a date as a
         * parameter
         */
        Calendar calendar = new GregorianCalendar(zone);
        calendar.setTime(secondOfFebruary);

        /* SimpleDateFormat is not thread-safe */
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyy HH:mm");

        /*
         * Runtime Exception, because formate accepts instances of date not
         * callendar. If you get the Date from Calendar you lose the timezone
         * and you have to set it manually on the dateformatter
         */
        dateFormat.setTimeZone(zone);
        return dateFormat.format(calendar.getTime());
    }
}
