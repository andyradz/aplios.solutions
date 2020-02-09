
package com.codigo.aplios.sdk.core.hamcrest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class IsSame extends BaseMatcher<Object> {

	private final Object object;

	public IsSame(final Object object) {
		this.object = object;
	}

	/**
	 * Creates a matcher that matches only when the examined {@linkplain Object} is
	 * the same instance as the provided <code>target</code> {@linkplain Object}.
	 */
	public static Matcher<?> sameInstance(final Object target) {
		return new IsSame(target);
	}

	@Override
	public boolean matches(final Object arg) {
		return arg == this.object;
	}

	@Override
	public void describeTo(final Description description) {
		description.appendText("same instance of ").appendValue(this.object);
	}
}
