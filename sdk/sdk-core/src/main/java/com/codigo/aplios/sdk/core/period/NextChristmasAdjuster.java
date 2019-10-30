package com.codigo.aplios.sdk.core.period;

import java.time.Month;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.UnsupportedTemporalTypeException;

public final class NextChristmasAdjuster implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {

		if (!(temporal.isSupported(ChronoField.MONTH_OF_YEAR)))
			throw new UnsupportedTemporalTypeException(
					"UnsupportedTemporalTypeException indicates that a ChronoField or ChronoUnit is not supported for a Temporal class");

		return temporal.with(ChronoField.MONTH_OF_YEAR, Month.DECEMBER.getValue()).with(ChronoField.DAY_OF_MONTH, 25);

	}
}