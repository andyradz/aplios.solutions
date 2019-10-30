//package com.codigo.aplios.group.timeline.common.interval;
//
//import java.time.LocalDateTime;
//import java.util.Objects;
//
//import com.codigo.aplios.group.timeline.common.IDurationProvider;
//import com.codigo.aplios.group.timeline.common.ITimeFormatter;
//import com.codigo.aplios.group.timeline.common.ITimePeriodComparer;
//import com.codigo.aplios.group.timeline.common.IntervalEdge;
//import com.codigo.aplios.group.timeline.common.TimeSpan;
//import com.codigo.aplios.group.timeline.common.TimeSpec;
//import com.codigo.aplios.group.timeline.common.helper.CompareOperator;
//import com.codigo.aplios.group.timeline.common.helper.Property;
//import com.codigo.aplios.group.timeline.common.period.ITimePeriod;
//import com.codigo.aplios.group.timeline.common.period.PeriodRelation;
//
//public class TimeInterval implements ITimeInterval {
//
//	public static final TimeInterval Anytime = new TimeInterval(TimeSpec.MINPERIODDATE, TimeSpec.MAXPERIODDATE,
//			IntervalEdge.CLOSED, IntervalEdge.CLOSED, false, true);
//
//	private final Property<Boolean> isReadOnly;
//	private Property<Boolean> isIntervalEnabled;
//	private LocalDateTime startInterval;
//	private LocalDateTime endInterval;
//	private IntervalEdge startEdge;
//	private IntervalEdge endEdge;
//
//	public TimeInterval() {
//
//		this(TimeSpec.MINPERIODDATE, TimeSpec.MAXPERIODDATE);
//	}
//
//	public TimeInterval(final LocalDateTime moment, final IntervalEdge startEdge, IntervalEdge endEdge,
//			boolean isIntervalEnabled, boolean isReadOnly) {
//
//		this(moment, moment, startEdge, endEdge, isIntervalEnabled, isReadOnly);
//	}
//
//	public TimeInterval(final LocalDateTime startInterval, final LocalDateTime endInterval,
//			final IntervalEdge startEdge, IntervalEdge endEdge, boolean isIntervalEnabled, boolean isReadOnly) {
//
//		this.isReadOnly = Property.from(false);
//		this.isIntervalEnabled = Property.from(true);
//
//		if (CompareOperator.EQUALSLESSTHEN.compare(startInterval, endInterval)) {
//			this.startInterval = startInterval;
//			this.endInterval = endInterval;
//
//		} else {
//			this.endInterval = startInterval;
//			this.startInterval = endInterval;
//		}
//
//		this.startEdge = startEdge;
//		this.endEdge = endEdge;
//
//		this.isIntervalEnabled.set(isIntervalEnabled);
//		this.isReadOnly.set(isReadOnly);
//	}
//
//	public TimeInterval(final ITimePeriod copy) {
//
//		this.isReadOnly = Property.from(false);
//		this.isIntervalEnabled = Property.from(true);
//
//		if (Objects.isNull(copy))
//			throw new IllegalAccessException("copy");
//
//		final ITimeInterval timeInterval = ITimeInterval.class.cast(copy);
//
//		if (timeInterval != null) {
//			this.startInterval = timeInterval.startInterval();
//			this.endInterval = timeInterval.endInterval();
//			this.startEdge = timeInterval.startEdge();
//			this.endEdge = timeInterval.endEdge();
//			this.isIntervalEnabled = timeInterval.IsIntervalEnabled;
//		} else {
//			this.startInterval = copy.Start;
//			this.endInterval = copy.End;
//		}
//
//		this.isReadOnly = copy.IsReadOnly;
//	}
//
//	protected TimeInterval(final ITimePeriod copy, final boolean isReadOnly) {
//
//		if (Objects.isNull(copy))
//			throw new IllegalArgumentException("copy");
//
//		final ITimeInterval timeInterval = ITimeInterval.class.cast(copy);
//
//		if (timeInterval != null) {
//
//			this.startInterval = timeInterval.startInterval();
//			this.endInterval = timeInterval.endInterval();
//			this.startEdge = timeInterval.startEdge();
//			this.endEdge = timeInterval.endEdge;
//			this.isIntervalEnabled = timeInterval.isIntervalEnabled();
//		} else {
//			this.startInterval = copy.start();
//			this.endInterval = copy.end;
//		}
//
//		this.isReadOnly = isReadOnly;
//	}
//
//	@Override
//	public boolean isReadOnly() {
//
//		return this.isReadOnly;
//	}
//
//	@Override
//	public boolean isAnytime() {
//
//		return !hasStart && !hasEnd;
//	}
//
//	@Override
//	public boolean isMoment() {
//
//		return this.startInterval.equals(this.endInterval);
//	}
//
//	@Override
//	public boolean isStartOpen() {
//
//		return this.startEdge == IntervalEdge.OPEN;
//	}
//
//	public boolean isEndOpen() {
//
//		return this.endEdge == IntervalEdge.OPEN;
//	}
//
//	public boolean IsOpen() {
//
//		return isStartOpen() && isEndOpen();
//	}
//
//	public boolean isStartClosed() {
//
//		return this.startEdge == IntervalEdge.CLOSED;
//	}
//
//	public boolean isEndClosed() {
//
//		return this.endEdge == IntervalEdge.CLOSED;
//	}
//
//	public boolean isClosed() {
//
//		return this.isStartClosed() && this.isEndClosed();
//	}
//
//	public boolean isEmpty() {
//
//		return isMoment() && !this.isClosed();
//	}
//
//	public boolean isDegenerate() {
//
//		return isMoment() && this.isClosed();
//	}
//
//	public boolean isIntervalEnabled()
//	{
//   			return this.isIntervalEnabled
//   			set
//   			{
//   				checkModification();
//   				this.isIntervalEnabled = value;
//   			}
//   		} // IsIntervalEnabled
//
//	@Override
//	public boolean hasStart() {
//
//		return !((this.startInterval == TimeSpec.MINPERIODDATE) && (this.startEdge == IntervalEdge.CLOSED));
//	}
//
//	@Override
//	public LocalDateTime startInterval()
//	{
//   			get { return this.startInterval; }
//   			set
//   			{
//   				CheckModification();
//   				if ( value > endInterval )
//   				{
//   					throw new ArgumentOutOfRangeException( "value" );
//   				}
//   				startInterval = value;
//   			}
//   		} // StartInterval
//
//	@Override
//	public LocalDateTime start() {
//
//		 isIntervalEnabled && (startEdge == IntervalEdge.OPEN) ?
//			startInterval.addTicks(1):startInterval;
//	}
//
//	public IntervalEdge startEdge()
//	{
//		return this.startEdge; }set
//
//	{
//		CheckModification();
//		startEdge = value;
//	}} // StartEdge
//
//	// ----------------------------------------------------------------------
//	public bool HasEnd{get
//	{
//		return !(endInterval == TimeSpec.MaxPeriodDate && endEdge == IntervalEdge.Closed);
//	}} // HasEnd
//
//	// ----------------------------------------------------------------------
//	public DateTime EndInterval{get
//	{ return this.endInterval; }set
//	{
//		CheckModification();
//		if (value < startInterval) {
//			throw new ArgumentOutOfRangeException("value");
//		}
//		endInterval = value;
//	}} // EndInterval
//
//	// ----------------------------------------------------------------------
//	public DateTime End{get
//	{
//		if (isIntervalEnabled && endEdge == IntervalEdge.Open) {
//			return endInterval.AddTicks(-1);
//		}
//		return endInterval;
//	}} // End
//
//	// ----------------------------------------------------------------------
//	public IntervalEdge EndEdge{get
//	{ return this.endEdge; }set
//	{
//		CheckModification();
//		endEdge = value;
//	}} // EndEdge
//
//	// ----------------------------------------------------------------------
//	public TimeSpan getDuration() {
//
//		return this.endInterval.subtract(this.startInterval);
//	}
//
//	public String durationDescription() {
//
//		return TimeFormatter.Instance.GetDuration(Duration, DurationFormatType.Detailed);
//	}
//
//	@Override
//	public TimeSpan getDuration(final IDurationProvider provider) {
//
//		if (Objects.IsNull(provider))
//			throw new ArgumentNullException("provider");
//
//		return provider.GetDuration(Start, this.End);
//	}
//
//	@Override
//	public void setup(final LocalDateTime newStartInterval, final LocalDateTime newEndInterval) {
//
//		checkModification();
//
//		if (newStartInterval <= newEndInterval) {
//			this.startInterval = newStartInterval;
//			this.endInterval = newEndInterval;
//
//		} else {
//			this.endInterval = newStartInterval;
//			this.startInterval = newEndInterval;
//		}
//	}
//
//	@Override
//	public boolean isSamePeriod(final ITimePeriod test) {
//
//		if (test == null)
//
//			throw new IllegalAccessException("test");
//
//		return (Start == test.start) && (end == test.end);
//	}
//
//	@Override
//	public boolean hasInside(final LocalDateTime test) {
//
//		return TimePeriodCalc.hasInside(this, test);
//	}
//
//	@Override
//	public boolean hasInside(final ITimePeriod test) {
//
//		if (test == null)
//			throw new ArgumentNullException("test");
//
//		return TimePeriodCalc.HasInside(this, test);
//	}
//
//	public ITimeInterval copy() {
//
//		return this.copy(TimeSpan.Zero);
//	}
//
//	@Override
//	public ITimeInterval copy(final TimeSpan offset) {
//
//		return new TimeInterval(this.startInterval.add(offset), this.endInterval.add(offset), this.startEdge,
//				this.endEdge, this.isIntervalEnabled, this.isReadOnly);
//	}
//
//	@Override
//	public void move(final TimeSpan offset) {
//
//		CheckModification();
//
//		if (offset == TimeSpan.Zero)
//			return;
//
//		this.startInterval = this.startInterval.add(offset);
//		this.endInterval = this.endInterval.add(offset);
//	}
//
//	@Override
//	public void expandStartTo(final LocalDateTime moment) {
//
//		checkModification();
//
//		if (this.startInterval > moment)
//			this.startInterval = moment;
//	}
//
//	public void expandEndTo(final DateTime moment) {
//
//		checkModification();
//
//		if (this.endInterval < moment)
//			this.endInterval = moment;
//
//	}
//
//	public void expandTo(final DateTime moment) {
//
//		this.expandStartTo(moment);
//		this.expandEndTo(moment);
//	}
//
//	@Override
//	public void expandTo(final ITimePeriod period) {
//
//		if (period == null)
//			throw new ArgumentNullException("period");
//
//		final ITimeInterval timeInterval = period instanceof ITimeInterval;
//
//		if (timeInterval != null) {
//			this.expandStartTo(timeInterval.startInterval);
//			this.expandEndTo(timeInterval.endInterval);
//		} else {
//			ExpandStartTo(period.start);
//			this.expandEndTo(period.end);
//		}
//	}
//
//	@Override
//	public void shrinkStartTo(final LocalDateTime moment) {
//
//		checkModification();
//		if (this.hasInside(moment) && (this.startInterval < moment))
//			this.startInterval = moment;
//	}
//
//	@Override
//	public void shrinkEndTo(final LocalDateTime moment) {
//
//		checkModification();
//
//		if (this.hasInside(moment) && (this.endInterval > moment))
//			this.endInterval = moment;
//	}
//
//	@Override
//	public void shrinkTo(final ITimePeriod period) {
//		if(Objects.isNull(period))
//		throw new ArgumentNullException("period")
//
//		final ITimeInterval timeInterval=period instanceof ITimeInterval;
//
//
//		if(timeInterval!=null){ShrinkStartTo(timeInterval.StartInterval);ShrinkEndTo(timeInterval.EndInterval);}else{ShrinkStartTo(period.Start);ShrinkEndTo(period.End);}ShrinkStartTo(period.Start);
//	}
//
//	@Override
//	public boolean intersectsWith(final ITimePeriod test) {
//
//		if (Objects.isNull(test))
//			throw new ArgumentNullException("test");
//
//		return TimePeriodCalc.IntersectsWith(this, test);
//	}
//
//	/*
//	 * public virtual ITimeInterval
//	 *
//	 * GetIntersection( ITimePeriod period ) { if ( period == null ) { throw new
//	 * ArgumentNullException( "period" ); } if ( !IntersectsWith( period ) ) {
//	 * return null; } DateTime start = Start; DateTime end = End; DateTime
//	 * periodStart = period.Start; DateTime periodEnd = period.End; return new
//	 * TimeInterval( periodStart > start ? periodStart : start, periodEnd < end ?
//	 * periodEnd : end, IntervalEdge.Closed, IntervalEdge.Closed, IsIntervalEnabled,
//	 * IsReadOnly ); } // GetIntersection
//	 *
//	 * // ----------------------------------------------------------------------
//	 * public virtual bool
//	 *
//	 * OverlapsWith( ITimePeriod test ) { if ( test == null ) { throw new
//	 * ArgumentNullException( "test" ); } return TimePeriodCalc.OverlapsWith( this,
//	 * test ); } // OverlapsWith
//	 *
//	 * // ----------------------------------------------------------------------
//	 * public virtual PeriodRelation
//	 *
//	 * GetRelation( ITimePeriod test ) { if ( test == null ) { throw new
//	 * ArgumentNullException( "test" ); } return TimePeriodCalc.GetRelation( this,
//	 * test ); } // GetRelation
//	 *
//	 * // ----------------------------------------------------------------------
//	 * public virtual int CompareTo( ITimePeriod other, ITimePeriodComparer comparer
//	 * ) { if ( other == null ) { throw new ArgumentNullException( "other" ); } if (
//	 * comparer == null ) { throw new ArgumentNullException( "comparer" ); } return
//	 * comparer.Compare( this, other ); } // CompareTo
//	 *
//	 * // ----------------------------------------------------------------------
//	 * public virtual void Reset() { CheckModification(); isIntervalEnabled = true;
//	 * startInterval = TimeSpec.MinPeriodDate; endInterval = TimeSpec.MaxPeriodDate;
//	 * startEdge = IntervalEdge.Closed; endEdge = IntervalEdge.Closed; } // Reset
//	 *
//	 * // ----------------------------------------------------------------------
//	 * public string GetDescription( ITimeFormatter formatter = null ) { return
//	 * Format( formatter ?? TimeFormatter.Instance ); } // GetDescription
//	 *
//	 * // ----------------------------------------------------------------------
//	 * protected virtual string
//	 *
//	 * Format( ITimeFormatter formatter ) { return formatter.GetInterval(
//	 * startInterval, endInterval, startEdge, endEdge, Duration ); } // Format
//	 *
//	 * // ----------------------------------------------------------------------
//	 * public override string
//	 *
//	 * ToString() { return GetDescription(); } // ToString
//	 *
//	 * // ----------------------------------------------------------------------
//	 * public sealed override
//	 *
//	 * bool Equals(object obj) { if (obj == this) { return true; } if (obj == null
//	 * || GetType() != obj.GetType()) { return false; } return IsEqual(obj); } //
//	 * Equals
//	 *
//	 * // ----------------------------------------------------------------------
//	 */
//	protected boolean isEqual(Object obj) {
//
//		return hasSameData(TimeInterval.class.cast(obj));
//	}
//
//	private boolean hasSameData(TimeInterval comp) {
//
//		return isReadOnly == comp.isReadOnly && isIntervalEnabled == comp.isIntervalEnabled
//				&& startInterval == comp.startInterval
//				&& endInterval == comp.endInterval
//				&& startEdge == comp.startEdge
//				&& endEdge == comp.endEdge;
//	}
//
//	public int hashCode() {
//
//		return Objects.hash(isReadOnly, isIntervalEnabled, startInterval, endInterval, startEdge, endEdge);
//	}
//
//	protected void checkModification() {
//
//		if (isReadOnly)
//			throw new NotSupportedException("TimeInterval is read-only");
//	}
//
//}
