package testDate;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class TestDate {

    public static void testDate(Date date1, Date date2) {
        long n = date2.getTime() - date1.getTime();
        System.out.println(n);
        System.out.println(n/1000 + "seconds");
        System.out.println(n/1000/60 + "minutes");
        System.out.println(n/1000/3600 + " hours");
        System.out.println(n/1000/3600/24 + " days");
    }
    
    public static void testTimeStamp(java.sql.Timestamp time1, java.sql.Timestamp time2) {
        long n = time2.getTime() - time1.getTime();
        System.out.println(n);
        System.out.println(n/1000/3600 + " hours");
        System.out.println(n/1000/3600/24 + " days");
    }
    
    public static void main(String[] args) {
        try {
            testDate(parseDate("2009-06-12 16:15","yyyy-MM-dd HH:mm"), new Date(Calendar.getInstance().getTimeInMillis()));
            testTimeStamp(new Timestamp(parseDate("2008-12-01","yyyy-MM-dd").getTime()), new Timestamp(Calendar.getInstance().getTimeInMillis()));
            testFormatDate();
            Date[] dates = getTwoDate(TimeUtil.parse("2009-01-01 15:00", "yyyy-MM-dd HH:mm", "Australia/Sydney"), TimeRange.THIS_WEEK);
            System.out.println(getDurationDescHtml(dates[0], dates[1], TimeRange.THIS_WEEK));
            
            System.out.println(new Date());
            testDay();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    
    public static Date parseDate(String str, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(str);
    }
    
    public static void testFormatDate() {
        Date startTime = new Date();
        TimeZone tz = TimeZone.getTimeZone("UTC");
        String pattern = "ss mm HH dd MM ? yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, new Locale("en",
                "AU"));
        sdf.setTimeZone(tz);
        String cronExpression = sdf.format(startTime);
        System.out.println(cronExpression);
    }
    
    public static Date[] getTwoDate(Date pointTime, TimeRange dateType) {
        final String timeZoneId = "Australia/Sydney";
        final String pattern = "yyyyMMdd";
        Date[] ds = new Date[2];
        Calendar c = Calendar.getInstance();
        c.setTime(pointTime);
        
        int monthIndex = 0;
        int quarterIndex = 0;
        switch (dateType) {
        case BY_DAY:
            c.add(Calendar.DATE, -1);
            ds[0] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId, pattern), pattern, timeZoneId);
            ds[1] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId,
                    pattern)
                    + "235959", pattern + "HHmmss", timeZoneId);
            break;
        case LAST_7_DAYS:
            c.add(Calendar.DATE, -1);
            ds[1] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId,
                    pattern)
                    + "235959", pattern + "HHmmss", timeZoneId);
            c.add(Calendar.DATE, -6);
            ds[0] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId, pattern), pattern, timeZoneId);
            break;
        case LAST_30_DAYS:
            c.add(Calendar.DATE, -1);
            ds[1] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId,
                    pattern)
                    + "235959", pattern + "HHmmss", timeZoneId);
            c.add(Calendar.DATE, -29);
            ds[0] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId, pattern), pattern, timeZoneId);
            break;
        case LAST_WEEK:
            c.set(Calendar.DAY_OF_WEEK, 1);
            ds[1] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId,
                    pattern)
                    + "235959", pattern + "HHmmss", timeZoneId);
            c.add(Calendar.DATE, -1);
            c.set(Calendar.DAY_OF_WEEK, 2);
            ds[0] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId, pattern), pattern, timeZoneId);
            break;
        case LAST_MONTH:
            c.set(Calendar.DAY_OF_MONTH, 1);
            c.add(Calendar.DATE, -1);
            ds[1] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId,
                    pattern)
                    + "235959", pattern + "HHmmss", timeZoneId);
            c.set(Calendar.DAY_OF_MONTH, 1);
            ds[0] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId, pattern), pattern, timeZoneId);
            break;
        case LAST_QUARTER:
            monthIndex = c.get(Calendar.MONTH);
            quarterIndex = monthIndex / 3 - 1;
            int temp = 0;
            if (quarterIndex > 0) {
                temp = quarterIndex * 3;
            } else {
                c.add(Calendar.YEAR, -1);
                temp = 12 + quarterIndex * 3;
            }
            c.set(Calendar.MONTH, temp);
            c.set(Calendar.DAY_OF_MONTH, 1);
            ds[0] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId, pattern), pattern, timeZoneId);
            c.add(Calendar.MONTH, 3);
            c.add(Calendar.DATE, -1);            
            ds[1] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId,
                    pattern)
                    + "235959", pattern + "HHmmss", timeZoneId);
            break;
        case LAST_YEAR:
            c.add(Calendar.YEAR, -1);
            c.set(Calendar.MONTH, 0);
            c.set(Calendar.DATE, 1);
            ds[0] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId, pattern), pattern, timeZoneId);
            c.add(Calendar.YEAR, 1);
            c.add(Calendar.DATE, -1);
            ds[1] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId,
                    pattern)
                    + "235959", pattern + "HHmmss", timeZoneId);
            break;
        case THIS_WEEK:
            c.set(Calendar.DAY_OF_WEEK, 2);
            ds[0] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId, pattern), pattern, timeZoneId);
            c.add(Calendar.DATE, 6);
            ds[1] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId,
                    pattern)
                    + "235959", pattern + "HHmmss", timeZoneId);
            break;
        case THIS_MONTH:
            c.set(Calendar.DAY_OF_MONTH, 1);
            ds[0] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId, pattern), pattern, timeZoneId);
            c.add(Calendar.MONTH, 1);
            c.add(Calendar.DATE, -1);
            ds[1] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId,
                    pattern)
                    + "235959", pattern + "HHmmss", timeZoneId);
            break;
        case THIS_QUARTER:
            // / 3 * 3, not wrong, must be this
            monthIndex = c.get(Calendar.MONTH) / 3 * 3;
            c.set(Calendar.MONTH, monthIndex);
            c.set(Calendar.DAY_OF_MONTH, 1);
            ds[0] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId, pattern), pattern, timeZoneId);
            c.add(Calendar.MONTH, 3);
            c.add(Calendar.DATE, -1);
            ds[1] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId,
                    pattern)
                    + "235959", pattern + "HHmmss", timeZoneId);
            break;
        case THIS_YEAR:
            c.set(Calendar.DAY_OF_YEAR, 1);
            ds[0] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId, pattern), pattern, timeZoneId);
            c.add(Calendar.YEAR, 1);
            c.add(Calendar.DATE, -1);
            ds[1] = TimeUtil.parse(TimeUtil.formatDate(c.getTime(), timeZoneId,
                    pattern)
                    + "235959", pattern + "HHmmss", timeZoneId);
            break;
        default:
            ds[0] = null;
            ds[1] = null;
            break;
        }
        System.out.println("begin date: "
                + TimeUtil.formatDate(ds[0], timeZoneId, "yyyy-MM-dd HH:mm:ss") + "; end date: "
                + TimeUtil.formatDate(ds[1], timeZoneId, "yyyy-MM-dd HH:mm:ss"));
        return ds;
    }
    
    public static String getDurationDescHtml(Date fromDate, Date toDate, TimeRange timeRange) {
        final String timeZoneId = "Australia/Sydney";
        String str = null;
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone(timeZoneId));
        c.setTime(fromDate);
        int monthIndex = c.get(Calendar.MONTH);
        
        switch (timeRange) {
        case BY_DAY:
            str = TimeUtil.formatDate(fromDate, timeZoneId, "dd-MMM-yyyy");
            break;
        case LAST_7_DAYS:
            str = TimeUtil.formatDate(fromDate, timeZoneId, "dd-MMM-yyyy")
                    + " ~ "
                    + TimeUtil.formatDate(toDate, timeZoneId,
                            "dd-MMM-yyyy");
            break;
        case LAST_30_DAYS:
            str = TimeUtil.formatDate(fromDate, timeZoneId, "dd-MMM-yyyy")
                    + " ~ "
                    + TimeUtil.formatDate(toDate, timeZoneId,
                            "dd-MMM-yyyy");
            break;
        case LAST_WEEK:
            str = "Week No." + c.get(Calendar.WEEK_OF_YEAR);
            break;
        case LAST_MONTH:
            str = TimeUtil.formatDate(fromDate, timeZoneId, "MMM-yyyy");
            break;
        case LAST_QUARTER:
            monthIndex = monthIndex / 3 + 1;
            str = "Q" + monthIndex + ", " + c.get(Calendar.YEAR);
            break;
        case LAST_YEAR:
            str = String.valueOf(c.get(Calendar.YEAR));
            break;
        case THIS_WEEK:
            str = "Week No." + c.get(Calendar.WEEK_OF_YEAR);
            break;
        case THIS_MONTH:
            str = TimeUtil.formatDate(fromDate, timeZoneId, "MMM-yyyy");
            break;
        case THIS_QUARTER:
            monthIndex = monthIndex / 3 + 1;
            str = "Q" + monthIndex + ", " + c.get(Calendar.YEAR);
            break;
        case THIS_YEAR:
            str = String.valueOf(c.get(Calendar.YEAR));
            break;
        default:
            str = "";
            break;
        }

        return str;
    }
    
    public enum TimeRange {
        BY_DAY(0),
        LAST_7_DAYS(1),
        LAST_30_DAYS(2),
        LAST_WEEK(3),
        LAST_MONTH(4),
        LAST_QUARTER(5),
        LAST_YEAR(6),
        THIS_WEEK(7),
        THIS_MONTH(8),
        THIS_QUARTER(9),
        THIS_YEAR(10);
        
        private int type;
        
        private TimeRange(int type) {
            this.type = type;
        }
    
        public int getValue() {
            return this.type;
        }
        
        private static final Map<Integer, TimeRange> LOOKUP = new HashMap<Integer, TimeRange>();

        static {
            /** iterator enum */
            for (TimeRange s : EnumSet.allOf(TimeRange.class)) {
                LOOKUP.put(s.getValue(), s);
            }
        }
        public static TimeRange fromValue(int type) {
            return LOOKUP.get(type);
        }
    }
    
    public static void testDay() {
        System.out.println(Calendar.SUNDAY);
        System.out.println(Calendar.TUESDAY);
        System.out.println(Calendar.SATURDAY);
    }
}
