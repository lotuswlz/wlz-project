package com.cathywu.report.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeRangeUtils {
	
	public final static String DATE_PATTERN = "yyyy-MM-dd";

	/**
	 * You can use this method to generate a key for storage source data in a map.
	 * e.g.: 2010-12-01-2010-12-08
	 * @param from
	 * @param to
	 * @return
	 */
	public static String timeRangeDesc(Date from, Date to) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		return sdf.format(from) + "-" + sdf.format(to);
	}
	
	public static void main(String[] args) {
		String desc = timeRangeDesc(new Date(new Date().getTime() - 3600 * 1000 * 48), new Date());
		System.out.println(desc);
		System.out.println("--------------------");
		Date d = new Date(new Date().getTime() - 3600 * 1000 * 24 * 2);
		
		String[] range = latestRangeDesc(d);
		System.out.print("Current: " + timeDesc(d) + "\t");
		System.out.println(range[0] + "-" + range[1]);
		System.gc();
		
		System.out.println(timeLengthDesc(222L));
	}
	
	/**
	 * Format date to be "yyyy-MM-dd".
	 * e.g.: 2010-12-01
	 * @param d
	 * @return
	 */
	public static String timeDesc(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		return sdf.format(d);
	}
	
	public static String[] latestRangeDesc() {
		Date d = new Date();
		return latestRangeDesc(d);
	}
	
	public static String[] latestRangeDesc(Date date) {
		String[] range = new String[2];
		Calendar today = Calendar.getInstance();
		today.setTime(date);
		today.setFirstDayOfWeek(Calendar.THURSDAY);		
		int weekIndex = today.getFirstDayOfWeek() - today.get(Calendar.DAY_OF_WEEK);
		if (weekIndex > 0) {
			weekIndex = 7 - weekIndex;
		}
		today.add(Calendar.DATE, -1 * (Math.abs(weekIndex) + 1));
		range[1] = timeDesc(today.getTime());
		today.add(Calendar.DATE, -6);
		range[0] = timeDesc(today.getTime());
		
		System.gc();
		return range;
	}
	
	public static String timeLengthDesc(long seconds) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.format(new Date(seconds*1000));
	}
}
