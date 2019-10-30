package com.codigo.aplios.sdk.core;

import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import com.codigo.aplios.sdk.core.hamcrest.IsBetween;

@Testable
public class TestsMoneyInWordsTeens {

	/// deklaracje elementów statycznych
	private static Logger log = Logger.getLogger(TestsMoneyInWordsTeens.class);

	/**
	 * Oprator wyznacznia nastek z wartości liczby
	 */
	private static IntUnaryOperator hasTeens = in -> (((in % 100) / 10) == 1) && (((in % 100) % 10) != 0) ? in % 100
			: 0;

	@Test
	@DisplayName("Test wyznaczania nastek dla wartości 0 - oczekiwana ilość nastek 0")
	public void shouldNotBeTeensUnit_ForValue0() {

		final var value = 0;

		MatcherAssert.assertThat(0, CoreMatchers.is(TestsMoneyInWordsTeens.hasTeens.applyAsInt(value)));
	}

	@Test
	@DisplayName("Test wyznaczania nastek dla wartości 112 - oczekiwana ilość nastek 12")
	public void shouldBeTeensUnit_ForValue112() {

		final var value = 112;

		MatcherAssert.assertThat(12, CoreMatchers.is(TestsMoneyInWordsTeens.hasTeens.applyAsInt(value)));
	}

	@Test
	@DisplayName("Test wyznaczania nastek dla wartości 19 - oczekiwana ilość nastek 19")
	public void shouldBeTeensUnit_ForValue19() {

		final var value = 19;

		MatcherAssert.assertThat(19, CoreMatchers.is(TestsMoneyInWordsTeens.hasTeens.applyAsInt(value)));
	}

	@Test
	@DisplayName("Test wyznaczania nastek dla wartości 1 - oczekiwana ilość nastek 0")
	public void shouldNotBeTeensUnit_ForValue1() {

		final var value = 1;

		MatcherAssert.assertThat(0, CoreMatchers.is(TestsMoneyInWordsTeens.hasTeens.applyAsInt(value)));
	}

	@Test
	@DisplayName("Test wyznaczania nastek dla wartości 10 - oczekiwana ilość nastek 0")
	public void shouldNotBeTeensUnit_ForValue10() {

		final var value = 10;

		MatcherAssert.assertThat(0, CoreMatchers.is(TestsMoneyInWordsTeens.hasTeens.applyAsInt(value)));
	}

	@Test
	@DisplayName("Test wyznaczania nastek dla wartości 100 - oczekiwana ilość nastek 0")
	public void shouldNotBeTeensUnit_ForValue110() {

		final var value = 110;

		MatcherAssert.assertThat(0, CoreMatchers.is(TestsMoneyInWordsTeens.hasTeens.applyAsInt(value)));
	}

	@Test
	@DisplayName("Test wyznaczania nastek dla wartości 120 - oczekiwana ilość nastek 0")
	public void shouldNotBeTeensUnit_ForValue120() {

		final var value = 120;

		MatcherAssert.assertThat(0, CoreMatchers.is(TestsMoneyInWordsTeens.hasTeens.applyAsInt(value)));
	}

	@Test
	@DisplayName("Test wyznaczania nastek dla wartości 190 - oczekiwana ilość nastek 0")
	public void shouldNotBeTeensUnit_ForValue190() {

		final var value = 190;

		MatcherAssert.assertThat(0, CoreMatchers.is(TestsMoneyInWordsTeens.hasTeens.applyAsInt(value)));
	}

	@Test
	@DisplayName("Test wyznaczania nastek dla wartości 819 - oczekiwana ilość nastek 19")
	public void shouldBeTeensUnit_ForValue819() {

		final var value = 819;

		MatcherAssert.assertThat(19, CoreMatchers.is(TestsMoneyInWordsTeens.hasTeens.applyAsInt(value)));
	}

	@Test
	public void testUnit1_9() {

		final String format = "Teens count %03d - %03d";

		final IntConsumer checker = e -> {

			final int result = TestsMoneyInWordsTeens.hasTeens.applyAsInt(e);

			if (result != 0)
				MatcherAssert.assertThat(result, IsBetween.between(11, 19));
			log.info(String.format(format, e, result));
		};

		IntStream.rangeClosed(1, 999).forEach(checker);
	}

}
