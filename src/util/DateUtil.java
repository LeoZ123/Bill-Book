package util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static long millisecondsOfOneDay = 1000 * 60 * 60 * 24L;

	public static java.sql.Date util2sql(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static Date getToday() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());

		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTime();
	}

	public static Date getMonthBegin() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DATE, 1);

		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTime();

	}

	public static Date getMonthEnd() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());

		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		c.set(Calendar.DATE, 1);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DATE, -1);

		return c.getTime();
	}

	public static int thisMonthTotalDay() {
		long lastDayMillisecs = getMonthEnd().getTime();
		long firstDayMillisecs = getMonthBegin().getTime();
		return (int) ((lastDayMillisecs - firstDayMillisecs) / millisecondsOfOneDay) + 1;
	}

	public static int thisMonthLeftDay() {
		long lastDayMilliSeconds = getMonthEnd().getTime();
		long todayMilliSeconds = getToday().getTime();
		return (int) ((lastDayMilliSeconds - todayMilliSeconds) / millisecondsOfOneDay);
	}

	public static int thisMonthSpentDay() {
		long firstDayMillisecs = getMonthBegin().getTime();
		long todayMilliSecs = getToday().getTime();

		return (int) ((todayMilliSecs - firstDayMillisecs) / millisecondsOfOneDay) + 1;
	}

}
