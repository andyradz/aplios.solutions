package com.codigo.aplios.sdk.core.period;

import java.util.Comparator;

public class TimeSpanOperator implements Comparator<TimeSpan> {

	@Override
	public int compare(final TimeSpan m1, final TimeSpan m2) {

		return m1.days().compareTo(m2.days());
	}
}
