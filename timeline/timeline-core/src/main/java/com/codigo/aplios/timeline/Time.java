package com.codigo.aplios.timeline;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import com.codigo.aplios.sdk.core.compare.CompareOperator;
import com.codigo.aplios.sdk.core.compare.IComparable;
import com.codigo.aplios.sdk.core.period.TimeSpan;
import com.codigo.aplios.sdk.core.period.TimeSpec;
import com.codigo.aplios.sdk.core.period.TimeSpec.TimeRelations;
import com.codigo.aplios.sdk.core.value.Valuable;

public class Time implements IComparable {

    public static void main(String[] args) {
        Time tm1 = new Time(LocalDateTime.now()), tm2 = new Time(LocalDateTime.now()
                .minusSeconds(2));

        System.out.println(new Time(tm1.duration));

        System.out.println(new Time(tm2.duration));

        // System.out.println(tm1);
        // System.out.println(tm2);
        var minus = Time.operator.minus(tm1, tm2);

        System.out.println(minus);
    }

    private final TimeSpan duration;

    public Time(final LocalDateTime dateTime) {

        final var zz = TimeSpan.ZERO.days();
        TimeSpan.fromTicks(1);

        final LocalTime midnight = LocalTime.MIDNIGHT;
        final LocalDate today = LocalDate.now(ZoneId.systemDefault());
        final LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        final LocalDateTime todayNow = dateTime;

        final Duration duration1 = Duration.between(todayMidnight, todayNow);

        this.duration = TimeSpan.fromMilliseconds(duration1.toMillis());
    }

    public Time(final TimeSpan duration) {

        this(Math.abs(duration.hours()
                .get()), Math.abs(
                        duration.minutes()
                                .get()),
                Math.abs(duration.seconds()
                        .get()),
                Math.abs(duration.milliseconds()
                        .get()));
    }

    public Time(final int hour, final int minute, final int second, final int millisecond) {

        if ((hour < 0) || (hour > TimeRelations.HOURSPERDAY.get())) {
            throw new IllegalArgumentException("hour");
        }

        if (hour == TimeRelations.HOURSPERDAY.get()) {

            if (minute > 0) {
                throw new IllegalArgumentException("minute");
            }

            if (second > 0) {
                throw new IllegalArgumentException("second");
            }

            if (millisecond > 0) {
                throw new IllegalArgumentException("millisecond");
            }
        }

        if ((minute < 0) || (minute >= TimeRelations.MINUTESPERHOUR.get())) {
            throw new IllegalArgumentException("minute");
        }

        if ((second < 0) || (second >= TimeRelations.SECONDSPERMINUTE.get())) {
            throw new IllegalArgumentException("second");
        }

        if ((millisecond < 0) || (millisecond >= TimeRelations.MILLISECONDSPERSECOND.get())) {
            throw new IllegalArgumentException("millisecond");
        }

        duration = new TimeSpan(0, hour, minute, second, millisecond);
    }

    public Valuable<Integer> getHour() {

        return duration.hours();
    }

    public Valuable<Integer> getMinute() {

        return duration.minutes();
    }

    public Valuable<Integer> getSecond() {

        return duration.seconds();
    }

    public Valuable<Integer> getMillisecond() {

        return duration.milliseconds();
    }

    public TimeSpan getDuration() {

        return this.duration;
    }

    public Valuable<Boolean> isZero() {

        final var result = duration.equals(TimeSpan.ZERO);

        return Valuable.from(result);
    }

    public Valuable<Boolean> isFullDay() {

        final boolean result = duration.totalHours()
                .get() == TimeSpec.TimeRelations.HOURSPERDAY.get();

        return Valuable.from(result);
    }

    // ----------------------------------------------------------------------
    public Valuable<Boolean> isFullDayOrZero() {

        {
            final var result = this.isFullDay()
                    .get()
                    || this.isZero()
                            .get();
            return Valuable.from(result);
        }
    }

    public Valuable<Long> ticks() {

        {
            return duration.ticks();
        }
    }

    public Valuable<Double> totalHours() {

        return duration.totalHours();
    }

    public Valuable<Double> totalMinutes() {

        return duration.totalMinutes();
    }

    public Valuable<Double> totalSeconds() {

        return duration.totalSeconds();
    }

    public Valuable<Double> totalMilliseconds() {

        return duration.totalMilliseconds();
    }

    public final int compareTo(final Time other) {

        return duration.compareTo(other.duration);
    }

    public final int compareTo(final Object obj) {

        return duration.compareTo(((Time) obj).duration);
    }

    public final boolean equals(final Time other) {

        return duration.equals(other.duration);
    }

    @Override
    public final String toString() {

        return String.format("hh:%02d mm:%02d ss:%02d ms:%06d", this.getHour()
                .get(),
                this.getMinute()
                        .get(),
                this.getSecond()
                        .get(),
                this.getMillisecond()
                        .get());
    }

    @Override
    public final boolean equals(final Object obj) {

        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }

        return equals(Time.class.cast(obj));
    }

    @Override
    public int hashCode() {

        // return HashTool.ComputeHashCode( GetType().GetHashCode(), duration );
        return 11;
    }

    public LocalDateTime toDateTime(final LocalDate date) {

        return toDateTime(date.atStartOfDay());
    }

    public LocalDateTime toDateTime(final LocalDateTime dateTime) {

        return toDateTime(dateTime, this);
    }

    public static LocalDateTime toDateTime(final LocalDate date, final Time time) {

        return toDateTime(date.atStartOfDay(), time);
    }

    public static LocalDateTime toDateTime(final LocalDateTime dateTime, final Time time) {

        return dateTime.plusSeconds(time.getDuration()
                .seconds()
                .get());
    }

    @Override
    public boolean compare(final Comparable<?> leftOperand, final Comparable<?> rightOperand) {

        return false;
    }

    public boolean Equals(final Time other) {

        return duration.equals(other.duration);
    } // Equals

    public static class operator {

        private operator() {

        }

        public static Time minus(final Time time1, final Time time2) {

            return minus(time1, time2.getDuration());
        }

        public static final Time minus(final Time time, final TimeSpan duration) {

            if (CompareOperator.EQUALS.compare(duration, TimeSpan.ZERO)) {
                return time;
            }

            // var result = CompareOperator.GREATERTHEN.compare(duration, TimeSpan.ZERO);
            // LocalDate day = CompareOperator.GREATERTHEN.compare(duration, TimeSpan.ZERO)
            // ? LocalDate.MAX
            // : LocalDate.MAX;
            // LocalTime tm = LocalTime.MIDNIGHT;
//			Duration sub = Duration.between(,timeSpan);
//			
//			var tmp = LocalDateTime.of(day, tm);
//				
//					
//			LocalDateTime timeSpan = time.toDateTime(day);
//			
            //return new Time(TimeSpan.fromMilliseconds(sub.toMillisPart()));
            return new Time(TimeSpan.ZERO);
        }

//
//		// ----------------------------------------------------------------------
//		public static TimeSpan operator+(
//		Time time1, Time time2)
//		{
//			return (time1 + time2.duration).duration;
//		} // operator +
//
//		// ----------------------------------------------------------------------
//		public static Time operator+(
//		Time time, TimeSpan duration)
//		{
//			if (Equals(duration, TimeSpan.Zero))
//				return time;
//			final DateTime day = duration > TimeSpan.Zero
//					? DateTime.MinValue
//					: DateTime.MaxValue;
//			return new Time(time.ToDateTime(day)
//				.Add(duration));
//		} // operator +
//
//		// ----------------------------------------------------------------------
//		public static bool operator<(
//		Time time1, Time time2)
//		{
//			return time1.duration < time2.duration;
//		} // operator <
//
//		// ----------------------------------------------------------------------
//		public static bool operator<=(
//		Time time1, Time time2)
//		{
//			return time1.duration <= time2.duration;
//		} // operator <=
//
//		// ----------------------------------------------------------------------
//		public static bool operator==(
//		Time left, Time right)
//		{
//			return Equals(left, right);
//		} // operator ==
//
//		// ----------------------------------------------------------------------
//		public static bool operator!=(
//		Time left, Time right)
//		{
//			return !Equals(left, right);
//		} // operator !=
//
//		// ----------------------------------------------------------------------
//		public static bool operator>(
//		Time time1, Time time2)
//		{
//			return time1.duration > time2.duration;
//		} // operator >
//
//		// ----------------------------------------------------------------------
//		public static bool operator>=(
//		Time time1, Time time2)
//		{
//			return time1.duration >= time2.duration;
//		} // operator >=
    }

}
