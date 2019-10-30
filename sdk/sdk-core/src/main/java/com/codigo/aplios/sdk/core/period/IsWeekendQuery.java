package com.codigo.aplios.sdk.core.period;

import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import java.time.temporal.UnsupportedTemporalTypeException;

public class IsWeekendQuery implements TemporalQuery<Boolean> {

	@Override
	public Boolean queryFrom(final TemporalAccessor temporal) {

		if (!(temporal.isSupported(ChronoField.DAY_OF_WEEK)))
			throw new UnsupportedTemporalTypeException(
					"UnsupportedTemporalTypeException indicates that a ChronoField or ChronoUnit is not supported for a Temporal class");

		final int dayOfWeek = temporal.get(ChronoField.DAY_OF_WEEK);

		return (dayOfWeek >= 5) && (dayOfWeek <= 7);
	}
}
