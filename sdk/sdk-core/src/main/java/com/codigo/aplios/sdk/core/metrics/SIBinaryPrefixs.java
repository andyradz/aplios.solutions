package com.codigo.aplios.sdk.core.metrics;

enum SIBinaryPrefixs {

	YOTTA(SIPrefiks.YOTTA, 1000E8), ZETTA(SIPrefiks.ZETTA, 1000E7), EXA(SIPrefiks.EKSA, 1000E6),
	PETA(SIPrefiks.PETA, 1000E5), TERA(SIPrefiks.TERA, 1000E4), GIGA(SIPrefiks.GIGA, 1000E3),
	MEGA(SIPrefiks.MEGA, 1000E2), KILO(SIPrefiks.KILO, 1000E1);

	private SIPrefiks prefix;
	private double factorBase;

	private SIBinaryPrefixs(final SIPrefiks prefix, final double factorBase) {

		this.prefix = prefix;
		this.factorBase = factorBase;
	}
}