package com.codigo.aplios.sdk.core.metrics;

/**
 * Hello world! https://en.wikipedia.org/wiki/International_System_of_Units
 */

enum SIDecimalPrefixs {

	KILO(SIPrefiks.KILO, 10E03, 1_000E1, 1_024),
	MEGA(SIPrefiks.MEGA, 10E06, 1_000E2, 1_048_576),
	GIGA(SIPrefiks.GIGA, 10E09, 1_000E3, 1_073_741_824),
	TERA(SIPrefiks.TERA, 10E12, 1_000E4, 1),
	PETA(SIPrefiks.PETA, 10E15, 1_000E5, 1024 * 4),
	EKSA(SIPrefiks.EKSA, 10E18, 1_000E6, 1024 * 5),
	ZETTA(SIPrefiks.ZETTA, 10E21, 1_000E7, 1024 * 6),
	YOTTA(SIPrefiks.YOTTA, 10E24, 1_000E8, 1024 * 7);

	private SIPrefiks prefix;
	private double factorBase;
	private double sizeInThousand;

	private SIDecimalPrefixs(final SIPrefiks prefix, final double factorBase, final double sizeInThousand,
			final double binaryApproximation) {

		this.prefix = prefix;
		this.factorBase = factorBase;
		this.sizeInThousand = sizeInThousand;
	}

	public SIPrefiks getPrefix() {

		return this.prefix;
	}

	public double getFactorBase() {

		return factorBase;
	}

	public double getsizeInThousand() {

		return this.sizeInThousand;
	}
}