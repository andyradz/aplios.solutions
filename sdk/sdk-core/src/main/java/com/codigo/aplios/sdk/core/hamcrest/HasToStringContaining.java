package com.codigo.aplios.sdk.core.hamcrest;

/**
 * Is the value a number between two numbers?
 *
 * @since version 0.7.0 for JDK7
 */
public class HasToStringContaining {

	private HasToStringContaining() {
	}

	/**
	 * Creates a matcher that matches any examined object whose
	 * <code>toString</code> method returns a value that contains all the substrings
	 * given by the <code>toString()</code> method called on the given list of
	 * items, considering the order of their appearance. For example:
	 *
	 * <pre>
	 * assertThat("AClass{a_field=a value, another_field=another value}", ContainingInOrder("a value", "another value"))
	 * </pre>
	 */
//	public static <T> Matcher<T> hasToStringContainingInOrder(final Object... items) {
//
//		final List<String> strings = new ArrayList<>();
//
//		for (final Object item : items)
//			strings.add(item.toString());
//
//		return false;
//
//		//return hasToString(stringContainsInOrder(strings));
//	}

}