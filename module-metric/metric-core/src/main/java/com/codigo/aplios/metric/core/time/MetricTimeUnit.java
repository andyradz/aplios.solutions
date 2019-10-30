package com.codigo.aplios.metric.core.time;

public class MetricTimeUnit implements IMetricTimeUnit {
	
	public MetricTimeUnit() {
		
	}
	
	public static void main(String[] args) {
		IMetricTimeUnit dd = new MetricTimeUnit();
		
		System.out.println(dd.getMetricUnit()
			.getUnitName());
	}
	
	@Override
	public double getMetricValue() {
		
		return 0;
	}
	
}
