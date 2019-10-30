package com.codigo.aplios.metric.core;

import java.util.ArrayList;
import java.util.List;

//https://en.wikipedia.org/wiki/International_System_of_Units

public interface IMetricUnit {
	
	IMetricDimension getMetricUnit();
	
	double getMetricValue();
	
	// Base units
	
//		@Override
//		public List<ISubmultiples> getSubmultiples() {
//
////			Submultiples		Multiples
////			Value	SI symbol	Name	Value	SI symbol	Name	Human-readable
////			10−1 s	ds	decisecond	101 s	das	decasecond	10 seconds
////			10−2 s	cs	centisecond	102 s	hs	hectosecond	1 minute 40 seconds
////			10−3 s	ms	millisecond	103 s	ks	kilosecond	16 minutes 40 seconds
////			10−6 s	µs	microsecond	106 s	Ms	megasecond	11.6 days
////			10−9 s	ns	nanosecond	109 s	Gs	gigasecond	31.7 years
////			10−12 s	ps	picosecond	1012 s	Ts	terasecond	31,700 years
////			10−15 s	fs	femtosecond	1015 s	Ps	petasecond	31.7 million years
////			10−18 s	as	attosecond	1018 s	Es	exasecond	31.7 billion years
////			10−21 s	zs	zeptosecond	1021 s	Zs	zettasecond	31.7 trillion years
////			10−24 s	ys	yoctosecond	1024 s	Ys	yottasecond	31.7 quadrillion years
//			final List<ISubmultiples> data = new ArrayList<>();
//			data.add(new ISubmultiples() {
//
//				@Override
//				public String grtSymbol() {
//
//					return "ds";
//				}
//
//				@Override
//				public double getValue() {
//
//					return 10e-1;
//				}
//
//				@Override
//				public String getName() {
//
//					return "decisecond";
//				}
//			});
//
//			return data;
//		}
	
}

interface ISubmultiples {
	
	double getValue();
	
	String grtSymbol();
	
	String getName();
}

interface IMultiples {
	
	double getValue();
	
	String grtSymbol();
	
	String getName();
}