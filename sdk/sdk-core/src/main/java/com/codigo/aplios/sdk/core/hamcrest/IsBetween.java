package com.codigo.aplios.sdk.core.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Is the value a number between two numbers?
 *
 * @since version 0.1 for JDK7
 */
public class IsBetween<T extends Comparable<T>> extends TypeSafeMatcher<T> {

	private final T from;
	private final T to;

	/**
	 * Creates and instance of the matcher. Observe that <code>from</code> and
	 * <code>to</code> cannot be null and <code>
	 * from.compareTo(to)</code> must be negative.
	 */
	public IsBetween(final T from, final T to) {
		// checkNotNull(from);
		// checkNotNull(to);
		// checkArgument(from.compareTo(to) < 0, "from must be before to");

		this.from = from;
		this.to = to;
	}

	/**
	 * Creates a matcher for {@code T}s that matches when the
	 * <code>compareTo()</code> method returns a value between <code>from</code> and
	 * <code>to</code>, both excluded.
	 * <p>
	 * <p>
	 * <p>
	 * <p>
	 * <p>
	 * For example:
	 * <p>
	 * <p>
	 * <p>
	 * <p>
	 *
	 * <pre>
	 * assertThat(10, between(10, 11))
	 * </pre>
	 *
	 * will return false.
	 */
	public static <T extends Comparable<T>> Matcher<T> between(final T from, final T to) {

		return new IsBetween<>(from, to);
	}

	@Override
	protected boolean matchesSafely(final T t) {

		return (t.compareTo(this.from) >= 0) && (t.compareTo(this.to) <= 0);
	}

	@Override
	protected void describeMismatchSafely(final T item, final Description mismatchDescription) {

		mismatchDescription.appendValue(item).appendText(" is not between ").appendValue(this.from).appendText(" and ")
				.appendValue(this.to).appendText(", both excluded");
	}

	@Override
	public void describeTo(final Description description) {

		description.appendText("a value between ").appendValue(this.from).appendText(" and ").appendValue(this.to)
				.appendText(", both excluded");
	}
}
