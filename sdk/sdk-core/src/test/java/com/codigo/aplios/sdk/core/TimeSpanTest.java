package com.codigo.aplios.sdk.core;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.codigo.aplios.sdk.core.period.TimeSpan;

//https://docs.microsoft.com/en-us/dotnet/api/system.timespan.ticks?redirectedfrom=MSDN&view=netframework-4.7.2#System_TimeSpan_Ticks
//The example displays the following output if the current culture is en-US:
//
//
public class TimeSpanTest {
	
	private final static Logger log = Logger.getLogger(TimeSpanTest.class);
	
	@Test
	public void testTicksPerMillisecond_Has_To_Be_10000() {
		
		assertThat(10000l, is(TimeSpan.TICKSPERMILLISECOND));
	}
	
	@Test
	public void testTicksPerSecond_Has_To_Be_10000000() {
		
		assertThat(10000000l, is(TimeSpan.TICKSPERSECOND));
	}
	
	@Test
	public void testTicksPerMinute_Has_To_Be_600000000() {
		
		assertThat(600000000l, is(TimeSpan.TICKSPERMINUTE));
	}
	
	@Test
	public void testTicksPerHour_Has_To_Be_36000000000() {
		
		assertThat(36000000000l, is(TimeSpan.TICKSPERHOUR));
	}
	
	@Test
	public void testTicksPerDay_Has_To_Be_864000000000() {
		
		assertThat(864000000000l, is(TimeSpan.TICKSPERDAY));
	}
	
	@Test
	public void testConstructor_Passing_Ticks() {
		
		final TimeSpan span = new TimeSpan(864000000000l);
		
		assertThat(864000000000l, is(span.ticks()
			.get()));
	}
	
	// TimeSpan( 1 ) 00:00:00.0000001
	// Days 0 TotalDays 0.000
	// Hours 0 TotalHours 0.000
	// Minutes 0 TotalMinutes 0.000
	// Seconds 0 TotalSeconds 0.000
	// Milliseconds 0 TotalMilliseconds 0.000
	// Ticks 1
	
	@Test
	public void testTimeSpan_From_1() {
		
		var timeSpan = TimeSpan.fromTicks(1);
		
		assertThat(0, is(timeSpan.days()
			.get()));
		
		assertThat(0, is(timeSpan.hours()
			.get()));
		
		assertThat(0, is(timeSpan.minutes()
			.get()));
		
		assertThat(0, is(timeSpan.seconds()
			.get()));
		
		assertThat(0, is(timeSpan.milliseconds()
			.get()));
		
		assertThat(.0d, is(BigDecimal.valueOf(timeSpan.totalDays()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(.0d, is(BigDecimal.valueOf(timeSpan.totalHours()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(.0d, is(BigDecimal.valueOf(timeSpan.totalMinutes()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(.0d, is(BigDecimal.valueOf(timeSpan.totalSeconds()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(.0d, is(BigDecimal.valueOf(timeSpan.totalMilliseconds()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(1L, is(timeSpan.ticks()
			.get()));
		
		log.info(timeSpan);
	}
	
	// TimeSpan( 111222333444555 ) 128.17:30:33.3444555
	// Days 128 TotalDays 128.730
	// Hours 17 TotalHours 3,089.509
	// Minutes 30 TotalMinutes 185,370.556
	// Seconds 33 TotalSeconds 11,122,233.344
	// Milliseconds 344 TotalMilliseconds 11,122,233,344.456
	// Ticks 111,222,333,444,555
	@Test
	public void testTimeSpan_From_111222333444555() {
		
		var timeSpan = TimeSpan.fromTicks(111222333444555L);
		
		assertThat(128, is(timeSpan.days()
			.get()));
		
		assertThat(17, is(timeSpan.hours()
			.get()));
		
		assertThat(30, is(timeSpan.minutes()
			.get()));
		
		assertThat(33, is(timeSpan.seconds()
			.get()));
		
		assertThat(344, is(timeSpan.milliseconds()
			.get()));
		
		assertThat(128.730d, is(BigDecimal.valueOf(timeSpan.totalDays()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(3_089.509d, is(BigDecimal.valueOf(timeSpan.totalHours()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(185370.556d, is(BigDecimal.valueOf(timeSpan.totalMinutes()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(11122233.344d, is(BigDecimal.valueOf(timeSpan.totalSeconds()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(11_122_233_344.456d, is(BigDecimal.valueOf(timeSpan.totalMilliseconds()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(111_222_333_444_555L, is(timeSpan.ticks()
			.get()));
		
		log.info(timeSpan);
	}
	
	// TimeSpan( 10, 20, 30, 40, 50 ) 10.20:30:40.0500000
	// Days 10 TotalDays 10.855
	// Hours 20 TotalHours 260.511
	// Minutes 30 TotalMinutes 15,630.668
	// Seconds 40 TotalSeconds 937,840.050
	// Milliseconds 50 TotalMilliseconds 937,840,050.000
	// Ticks 9,378,400,500,000
	@Test
	public void testTimeSpan_From_10_20_30_40_50() {
		
		var timeSpan = new TimeSpan(10, 20, 30, 40, 50);
		
		assertThat(10, is(timeSpan.days()
			.get()));
		
		assertThat(20, is(timeSpan.hours()
			.get()));
		
		assertThat(30, is(timeSpan.minutes()
			.get()));
		
		assertThat(40, is(timeSpan.seconds()
			.get()));
		
		assertThat(50, is(timeSpan.milliseconds()
			.get()));
		
		assertThat(10.855d, is(BigDecimal.valueOf(timeSpan.totalDays()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(260.511d, is(BigDecimal.valueOf(timeSpan.totalHours()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(15_630.668d, is(BigDecimal.valueOf(timeSpan.totalMinutes()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(937_840.050d, is(BigDecimal.valueOf(timeSpan.totalSeconds()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(937_840_050.000d, is(BigDecimal.valueOf(timeSpan.totalMilliseconds()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(9_378_400_500_000L, is(timeSpan.ticks()
			.get()));
		
		log.info(timeSpan);
	}
	
	//
	// TimeSpan( 1111, 2222, 3333, 4444, 5555 ) 1205.22:47:09.5550000
	// Days 1205 TotalDays 1,205.949
	// Hours 22 TotalHours 28,942.786
	// Minutes 47 TotalMinutes 1,736,567.159
	// Seconds 9 TotalSeconds 104,194,029.555
	// Milliseconds 555 TotalMilliseconds 104,194,029,555.000
	// Ticks 1,041,940,295,550,000
	
	// @Test
	public void testTimeSpan_From_1111_2222_3333_4444_5555() {
		
		var timeSpan = new TimeSpan(1111, 2222, 3333, 4444, 5555);
		
		assertThat(1205, is(timeSpan.days()
			.get()));
		
		assertThat(22, is(timeSpan.hours()
			.get()));
		
		assertThat(47, is(timeSpan.minutes()
			.get()));
		
		assertThat(9, is(timeSpan.seconds()
			.get()));
		
		assertThat(555, is(timeSpan.milliseconds()
			.get()));
		
		assertThat(1_205.949d, is(BigDecimal.valueOf(timeSpan.totalDays()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(28_942.786d, is(BigDecimal.valueOf(timeSpan.totalHours()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(1_736_567.159d, is(BigDecimal.valueOf(timeSpan.totalMinutes()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(104_194_029.555d, is(BigDecimal.valueOf(timeSpan.totalSeconds()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(104_194_029_555.000d, is(BigDecimal.valueOf(timeSpan.totalMilliseconds()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(1_041_940_295_550_000L, is(timeSpan.ticks()
			.get()));
		
		log.info(timeSpan);
	}
	
	// FromDays( 20.84745602 ) 20.20:20:20.2000000
	// Days 20 TotalDays 20.847
	// Hours 20 TotalHours 500.339
	// Minutes 20 TotalMinutes 30,020.337
	// Seconds 20 TotalSeconds 1,801,220.200
	// Milliseconds 200 TotalMilliseconds 1,801,220,200.000
	// Ticks 18,012,202,000,000
	@Test
	public void testTimeSpan_FromDays_2084745602() {
		
		var timeSpan = TimeSpan.fromDays(20.84745602);
		
		assertThat(20, is(timeSpan.days()
			.get()));
		
		assertThat(20, is(timeSpan.hours()
			.get()));
		
		assertThat(20, is(timeSpan.minutes()
			.get()));
		
		assertThat(20, is(timeSpan.seconds()
			.get()));
		
		assertThat(200, is(timeSpan.milliseconds()
			.get()));
		
		assertThat(20.847d, is(BigDecimal.valueOf(timeSpan.totalDays()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(500.339d, is(BigDecimal.valueOf(timeSpan.totalHours()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(30_020.337d, is(BigDecimal.valueOf(timeSpan.totalMinutes()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
		assertThat(1_801_220.200d, is(BigDecimal.valueOf(timeSpan.totalSeconds()
			.get())
			.setScale(3, RoundingMode.HALF_UP)
			.doubleValue()));
		
//		assertThat(1_801_220_200.000d, is(BigDecimal.valueOf(timeSpan.totalMilliseconds()
//			.get())
//			.setScale(3, RoundingMode.HALF_UP)
//			.doubleValue()));
		
//		assertThat(18_012_202_000_000L, is(timeSpan.ticks()
//			.get()));
		
		log.info(timeSpan);
	}
}
