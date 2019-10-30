package com.codigo.aplios.sdk.core.period;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.UnsupportedTemporalTypeException;

public class NextWorkingDayAdjuster implements TemporalAdjuster {

	public static void main(final String[] args) {

		LocalDate localDate = LocalDate.now();
		System.out.println("current date : " + localDate);

		final LocalDate with = localDate.with(TemporalAdjusters.firstDayOfMonth());
		System.out.println("firstDayOfMonth : " + with);

		final LocalDate with1 = localDate.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println("lastDayOfMonth : " + with1);

		LocalDate with2 = localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
		System.out.println("next monday : " + with2);

		with2 = localDate.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
		System.out.println("next tuesday : " + with2);

		with2 = localDate.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
		System.out.println("next wedneday : " + with2);

		final LocalDate with3 = localDate.with(TemporalAdjusters.firstDayOfNextMonth());
		System.out.println("firstDayOfNextMonth : " + with3);

		localDate = LocalDate.of(2018, 1, 6);
		final NextWorkingDayAdjuster temporalAdjuster = new NextWorkingDayAdjuster();
		LocalDate nextWorkingDay = localDate.with(temporalAdjuster);
		System.out.println(nextWorkingDay);

		final NextChristmasAdjuster tt = new NextChristmasAdjuster();
		nextWorkingDay = localDate.with(tt);
		System.out.println("next working day : " + nextWorkingDay);
	}

	@Override
	public Temporal adjustInto(final Temporal temporal) {

		if (!(temporal.isSupported(ChronoField.DAY_OF_WEEK)))
			throw new UnsupportedTemporalTypeException(
					"UnsupportedTemporalTypeException indicates that a ChronoField or ChronoUnit is not supported for a Temporal class");

		final DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

		int daysToAdd = 0;
		if (dayOfWeek == DayOfWeek.FRIDAY)
			daysToAdd = 3;

		else if (dayOfWeek == DayOfWeek.SATURDAY)
			daysToAdd = 2;

		else
			daysToAdd = 1;

		return temporal.plus(daysToAdd, ChronoUnit.DAYS);
	}
}
