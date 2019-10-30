package com.codigo.aplios.timeline;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalQuery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WeekendDayTest {

	@Test
	@DisplayName("Test sprawdza czy wskazana data jest dniem przypdającym na weekend")
	public void testDayInWeekRequireSunday() {

		final LocalDateTime localDate = LocalDateTime.of(2018, 11, 23, 0, 0);
		localDate.toInstant(ZoneOffset.UTC);

		final TemporalQuery<Boolean> IS_WEEKEND_QUERY = t -> t.get(ChronoField.DAY_OF_WEEK) >= 5;
		final boolean isWeekandDay = localDate.query(IS_WEEKEND_QUERY);

		assertThat(true, is(isWeekandDay));
	}

	@Test
	@DisplayName("Test sprawdza czy wskazana data jest dniem przypdającym na weekend")
	public void testDayInWeekRequireSaturday() {

		final LocalDateTime localDate = LocalDateTime.of(2019, 8, 2, 0, 0);
		localDate.toInstant(ZoneOffset.UTC);

		final TemporalQuery<Boolean> IS_WEEKEND_QUERY = t -> t.get(ChronoField.DAY_OF_WEEK) >= 5;
		final boolean isWeekandDay = localDate.query(IS_WEEKEND_QUERY);

		assertThat(true, is(isWeekandDay));
	}
}
