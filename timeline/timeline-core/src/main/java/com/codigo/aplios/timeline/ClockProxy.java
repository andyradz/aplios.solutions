package com.codigo.aplios.timeline;

import java.time.Clock;

public class ClockProxy {

	private static final Object mutex;
	private static volatile Clock clock;

	static {
		mutex = new Object();
	}

	public static final Clock get() {

		if (clock == null)
			synchronized (mutex) {
				if (clock == null)
					return Clock.systemDefaultZone();
			}
		return ClockProxy.clock;
	}

	public static final void set(final Clock value) {

		synchronized (mutex) {
			clock = value;
		}
	}

	private ClockProxy() {

	}
}

//get
//{
//	if ( clock == null )
//	{
//		lock ( mutex )
//		{
//			if ( clock == null )
//				clock = new SystemClock();
//		}
//	}
//	return clock;
//}
//set
//{
//	lock ( mutex )
//	{
//		clock = value;
//	}
//}
