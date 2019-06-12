package com.matrangola.demo;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;

public class TimeDemo {
	
	private static final ZoneId HERE = ZoneId.of("America/New_York");


	public void demo() {
		ZonedDateTime zdt = ZonedDateTime.now();
		System.out.println(zdt);
//		String dtf = DateTimeFormatterBuilder.getLocalizedDateTimePattern(dateStyle , timeStyle , zdt , null);
		DateTimeFormatter fullMonth = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm:ss");
		DateTimeFormatter threeMonth = DateTimeFormatter.ofPattern("MMM d, yyyy hh:mm:ss");
		DateTimeFormatter numericZeroMonth = DateTimeFormatter.ofPattern("MM/d/yyyy hh:mm:ss");
		DateTimeFormatter numericMonth = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss VV");

		System.out.println(fullMonth.format(zdt));
		
		ZonedDateTime later = ZonedDateTime.of(2020, 01, 28, 13, 0, 0, 0, HERE);
		System.out.println("fullMonth = " + fullMonth.format(later));
		System.out.println("threeMonth = " + threeMonth.format(later));
		System.out.println("numericZeroMonth = " + numericZeroMonth.format(later));
		System.out.println("numericMonth = " + numericMonth.format(later));
		
		
		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime here = ZonedDateTime.now();
		ZonedDateTime denver = here.withZoneSameInstant(ZoneId.of("America/Denver"));
		System.out.println("denver = " + numericMonth.format(denver));
		System.out.println("here = " + numericMonth.format(here));
		
		Duration zoneDifference = Duration.between(here, denver);
		System.out.println("difference = " + zoneDifference.toHours());
		
		Duration localDifference = Duration.between(now, denver);
		System.out.println("localDifference = " + localDifference.toHours());
		
		long hours = ChronoUnit.HOURS.between(here, denver);
		long weeks = ChronoUnit.WEEKS.between(here, later);
		
		System.out.println("weeks apart " + weeks);
		
		
		System.out.println("half day later " + fullMonth.format(here.plus(1, ChronoUnit.HALF_DAYS)) );
		
		ZonedDateTime thisFriday = here.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		System.out.println("this friday " + fullMonth.format(thisFriday));
		System.out.println("friday after that " + fullMonth.format(thisFriday.with(TemporalAdjusters.next(DayOfWeek.FRIDAY))));
		
		ZonedDateTime dec = ZonedDateTime.of(2020, 12, 1, 0, 0, 0, 0, HERE);
		ZonedDateTime firstMonday = dec.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
		
		System.out.println("first mon in dec " + fullMonth.format(firstMonday));
		
		// Leap Year
		ZonedDateTime firstDay = ZonedDateTime.of(2020, 1, 1, 0, 0, 0, 0, HERE);
		ZonedDateTime lastDay = ZonedDateTime.of(2021, 1, 1, 0, 0, 0, 0, HERE);
	
		long days = ChronoUnit.DAYS.between(firstDay, lastDay); // inclusive and exclusive days.
		System.out.println("days: " + days);
	}
	
	public void notes() {
		DateTimeFormatter fullMonth = DateTimeFormatter.ofPattern("MMMM d, yyyy hh:mm:ss");
		ZonedDateTime here = ZonedDateTime.now();

		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime later = ZonedDateTime.of(2020, 01, 28, 13, 0, 0, 0, HERE);
		Period period = Period.between(now.toLocalDate(), later.toLocalDate());
		System.out.println("period = " + period);
		System.out.println("period days = " + period.getDays() + " months = " + period.getMonths() + " years = " + period.getYears());
		
		long weeks = ChronoUnit.WEEKS.between(now, later);
		System.out.println("weeks = " + weeks);
		
		ZonedDateTime halfDayLater = later.plus(1, ChronoUnit.HALF_DAYS);
		System.out.println(ChronoUnit.SECONDS.between(later, halfDayLater) + " " + fullMonth.format(halfDayLater));
		
		ZonedDateTime nextFirday = here.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		System.out.println("next Friday: " + fullMonth.format(nextFirday));
		System.out.println("firday after that: " + fullMonth.format(nextFirday.with(TemporalAdjusters.next(DayOfWeek.FRIDAY))));
	}
	
	
	public void instantDemo() {
		DateTimeFormatter fullMonth = DateTimeFormatter.ofPattern("MMMM yyyy hh:mm:ss");
		Instant now = Instant.now();
		System.out.println("instant = " + now + " " + fullMonth.format(ZonedDateTime.ofInstant(now, HERE)));
		Instant later = Instant.now();
		Duration diff = Duration.between(later, now);
		System.out.println("duration = " + diff.getNano());
		
	}
	
	public static void main(String[] args) {
		TimeDemo td = new TimeDemo();
		td.demo();
//		td.instantDemo();
//		td.prep();
	}

}
