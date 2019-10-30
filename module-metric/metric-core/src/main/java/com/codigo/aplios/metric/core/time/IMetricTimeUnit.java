package com.codigo.aplios.metric.core.time;

import com.codigo.aplios.metric.core.IMetricDimension;
import com.codigo.aplios.metric.core.IMetricUnit;

public interface IMetricTimeUnit extends IMetricUnit {
	
	public enum MetricTimeMultiples{
		
	}
	
	public enum MetricTimeSubmultiples{
		
	}
	
	@Override
	public default IMetricDimension getMetricUnit() {
		
		return new IMetricDimension() {
			
			@Override
			public String getUnitName() {
				return "second";
			}
			
			@Override
			public String getUnitSymbol() {
				return "s";
			}
			
			@Override
			public String getQuantityName() {
				return "time";
			}
			
			@Override
			public String getDimensionSymbol() {				
				return "T";
			}
		};
	}
	
	@Override
	public double getMetricValue();
}
