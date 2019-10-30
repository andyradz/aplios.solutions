package com.codigo.aplios.metric.core;

/**
 * Hello world! https://en.wikipedia.org/wiki/International_System_of_Units
 */

//https://en.wikipedia.org/wiki/Radian konwersia kątów

enum MetricDecimalPrefixs {
	
	YOTTA(MetricPrefiks.YOTTA, 10E24, 1_000E8, 1024 * 7D),
	ZETTA(MetricPrefiks.ZETTA, 10E21, 1_000E7, 1024 * 6D),
	EKSA(MetricPrefiks.EKSA, 10E18, 1_000E6, 1024 * 5D),
	PETA(MetricPrefiks.PETA, 10E15, 1_000E5, 1024 * 4D),
	TERA(MetricPrefiks.TERA, 10E12, 1_000E4, 1),
	GIGA(MetricPrefiks.GIGA, 10E09, 1_000E3, 1_073_741_824),
	MEGA(MetricPrefiks.MEGA, 10E06, 1_000E2, 1_048_576),
	KILO(MetricPrefiks.KILO, 10E03, 1_000E1, 1_024);
	
	private MetricPrefiks prefix;
	private double factorBase;
	private double sizeInThousand;
	
	private MetricDecimalPrefixs(final MetricPrefiks prefix, final double factorBase, final double sizeInThousand,
			final double binaryApproximation) {
		
		this.prefix = prefix;
		this.factorBase = factorBase;
		this.sizeInThousand = sizeInThousand;
	}
	
	public MetricPrefiks getPrefix() {
		
		return this.prefix;
	}
	
	public double getFactorBase() {
		
		return factorBase;
	}
	
	public double getsizeInThousand() {
		
		return this.sizeInThousand;
	}
}