package com.codigo.aplios.metric.core;

enum MetricBinaryPrefixs {
	
	YOTTA(MetricPrefiks.YOTTA, 1000E8),
	ZETTA(MetricPrefiks.ZETTA, 1000E7),
	EXA(MetricPrefiks.EKSA, 1000E6),
	PETA(MetricPrefiks.PETA, 1000E5),
	TERA(MetricPrefiks.TERA, 1000E4),
	GIGA(MetricPrefiks.GIGA, 1000E3),
	MEGA(MetricPrefiks.MEGA, 1000E2),
	KILO(MetricPrefiks.KILO, 1000E1);
	
	private MetricPrefiks prefix;
	private double factorBase;
	
	private MetricBinaryPrefixs(final MetricPrefiks prefix, final double factorBase) {
		
		this.prefix = prefix;
		this.factorBase = factorBase;
	}
}