package com.codigo.aplios.timeline.adjust;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public final class CustomTemporalAdjuster implements TemporalAdjuster {
	
	@Override
	public Temporal adjustInto(final Temporal temporal) {
		
		final LocalTime midnight = LocalTime.MIDNIGHT;
		final LocalDate today = LocalDate.from(temporal);
		
		final LocalDateTime todayMidnight = LocalDateTime.of(today, midnight), todayNow = LocalDateTime.now();
		
		final Duration duration = Duration.between(todayMidnight, todayNow);
		
		temporal.plus(duration.toNanos(), ChronoUnit.NANOS);
		
		return LocalDateTime.from(temporal);
	}
}
