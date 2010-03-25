package testDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author jason
 * @version 1.0
 * @created 15-May-2008 5:22:50 PM
 */
public class TimeUtil {
    // private static boolean devMode =
    // ConfigurationUtil.getProperty("devMode").equals("true");

    /**
     * Formats date using specific pattern, specific timeZone and fix locale
     * "en_AU".
     * 
     * @param date
     * @param timeZone
     * @param pattern
     *            the pattern describing the date and time format, for example
     *            "HH:mm:ss dd-MMM-yyyy".
     * @see java.text.SimpleDateFormat
     */
    public static String formatDate(Date date, String timeZone, String pattern) {
        TimeZone tz = TimeZone.getTimeZone(timeZone);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, new Locale("en", "AU"));
        sdf.setTimeZone(tz);
        return sdf.format(date);
    }

    /**
     * Formats date using pattern "MMM-yyyy", for example "Jan-2008".
     * 
     * @param date
     * @param timeZone
     * @return
     */
    public static String formatMmmYyyy(Date date, String timeZone) {
        String pattern = "MMM-yyyy";
        return formatDate(date, timeZone, pattern);
    }

    /**
     * Formats date using pattern "dd-MMM-yyyy", for example "02-Jan-2008".
     * 
     * @param date
     * @param timeZone
     * @return
     */
    public static String formatDate(Date date, String timeZone) {
        String pattern = "dd-MMM-yyyy";
        return formatDate(date, timeZone, pattern);
    }

    /**
     * Formats date using pattern "dd-MMM-yyyy HH:mm:ss", for example
     * "02-Jan-2008 14:28:26".
     * 
     * @param date
     * @param timeZone
     * @return
     */
    public static String formatDateTime(Date date, String timeZone) {
        String pattern = "dd-MMM-yyyy HH:mm:ss";
        return formatDate(date, timeZone, pattern);
    }

    /**
     * Formats date using pattern "yyyy-mm-dd HH:mm:ss", for example
     * "2008-01-02 14:28:26".
     * 
     * @param date
     * @param timeZone
     * @return
     */
    public static String formatTime(Date date, String timeZone) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return formatDate(date, timeZone, pattern);
    }

    /**
     * Formats date using pattern "yyyy-mm-dd", for example "2008-01-02".
     * 
     * @param date
     * @param timeZone
     * @return
     */
    public static String formatYMD(Date date, String timeZone) {
        String pattern = "yyyy-MM-dd";
        return formatDate(date, timeZone, pattern);
    }

    /**
     * 
     * @param source
     *            for example 20070918
     * @param pattern
     *            for example yyyyMMdd
     * @param timeZone
     *            for example Australia/Sydney
     * @return
     */
    public static Date parse(String source, String pattern, String timeZone) {
        try {
            TimeZone tz = TimeZone.getTimeZone(timeZone);
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            sdf.setTimeZone(tz);
            return sdf.parse(source);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * The pattern of the source is "yyyyMMdd"
     * 
     * @param source
     * @param timeZone
     * @return
     * @see parse(String source,String pattern,String timeZone);
     */
    public static Date parseYyyyMMdd(String source, String timeZone) {
        return parse(source, "yyyyMMdd", timeZone);
    }

    /**
     * Get current app server system date as string.
     * 
     * @return
     */
    public static String getYyyyMmDdHhmmssS() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
        sdf.setLenient(false);
        return sdf.format(new Date());
    }

    /**
     * parse a date to a String
     * 
     * @param d
     * @return
     */
    public static String getYyyyMmDdHhmmssS(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
        sdf.setLenient(false);
        return sdf.format(d);
    }

    /**
     * get date from String
     * 
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date getDateFromStr(String str) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.parse(str);
    }

    public static String getYyyyMmDd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);
        return sdf.format(new Date());
    }

    public static String getYyyyMmDd(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);
        return sdf.format(d);
    }

    /**
     * return the year of the (String)Date
     * 
     * @param str
     * @return
     */
    public static String getYyyyFromStr(String str) {
        return str.substring(0, 4);
    }

    /**
     * return the month of the (String)Date
     * 
     * @param str
     * @return
     */
    public static String getMmFromStr(String str) {
        return str.substring(4, 6);

    }

    /**
     * return the date of the (String)Date
     * 
     * @param str
     * @return
     */
    public static String getDdFromStr(String str) {
        return str.substring(6, 8);
    }

    public static Date getDateFromYyMmDd(String year, String month, String day) throws ParseException {
        StringBuffer sb = new StringBuffer();
        sb.append(year);
        sb.append(month);
        sb.append(day);
        return TimeUtil.getDateFromStr(sb.toString());

    }

    /**
     * Package dd/MM/yyyy into date
     * 
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date getDateFromStrSlash(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        return sdf.parse(str);
    }

    /**
     * get dd/MM/yyyy from date
     * 
     * @param str
     * @return
     * @throws ParseException
     */
    public static String getStrSlashFromDate(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        return sdf.format(date);
    }

    /**
     * after n days now
     * 
     * @param d
     * @param afterDays
     * @return
     */
    public static Date afterDataNow(Date d, int afterDays) {
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        c.add(Calendar.DATE, afterDays);
        return c.getTime();
    }

    public static Date getDate(int year, int month, int day, String timeZoneId) {
        StringBuffer sb = new StringBuffer("" + year);
        if (month < 10)
            sb.append("0");
        sb.append(month);
        if (day < 10)
            sb.append("0");
        sb.append(day);
        return parse(sb.toString(), "yyyyMMdd", timeZoneId);
    }

    /**
     * if d1 > d2 return true;
     */
    public static boolean compareDate(Date d1, Date d2) {
        if (d1 != null && d2 != null) {
            Calendar c1 = new GregorianCalendar();
            c1.setTime(d1);
            Calendar c2 = new GregorianCalendar();
            c2.setTime(d2);
            if (c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR)) {
                return true;
            } else {

            }
        }
        return false;
    }
}
