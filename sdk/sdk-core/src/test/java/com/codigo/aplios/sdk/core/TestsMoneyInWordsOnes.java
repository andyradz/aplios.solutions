package com.codigo.aplios.sdk.core;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.codigo.aplios.sdk.core.hamcrest.IsBetween;

public class TestsMoneyInWordsOnes {

	private static Logger log = Logger.getLogger(TestsMoneyInWordsOnes.class);

	/**
	 * Operator wyznacznia jednostek z wartości liczby
	 */
	private static IntUnaryOperator hasOnes = item -> ((item / 10) % 10) == 1 ? 0 : item % 10;

	private static IntStream sequence(final int min, final int max, final int grow) {

		return IntStream.iterate(min, i -> i + grow).limit(max);
	}

	@Test
	public void test1100() {

		sequence(1, 9, 1).forEach(item -> {
			assertThat(item, is(hasOnes.applyAsInt(item)));
		});
	}

	@Test
	public void shouldReturnZeroUnits_From910() {

		final var value = 910;

		sequence(1, 9, 1).forEach(log::info);

		assertThat(0, is(hasOnes.applyAsInt(value)));
	}

	@Test
	public void shouldReturnZeroUnits_From10() {

		final var value = 10;

		assertThat(0, is(hasOnes.applyAsInt(value)));
	}

	@Test
	public void shouldReturnZeroUnits_From919() {

		final var value = 919;

		assertThat(0, is(hasOnes.applyAsInt(value)));
	}

	@Test
	public void shouldReturnZeroUnits_From12() {

		final var value = 12;

		assertThat(0, is(hasOnes.applyAsInt(value)));
	}

	@Test
	public void shouldReturnTwoUnits_From22() {

		final var value = 22;

		assertThat(2, is(hasOnes.applyAsInt(value)));
	}

	@Test
	public void shouldReturnTwoUnits_From2() {

		final var value = 2;

		assertThat(2, is(hasOnes.applyAsInt(value)));
	}

	@Test
	public void shouldReturnTwoUnits_From102() {

		final var value = 102;

		assertThat(2, is(hasOnes.applyAsInt(value)));
	}

	@Test
	public void shouldReturnZeroUnits_From112() {

		final var value = 112;

		assertThat(0, is(hasOnes.applyAsInt(value)));
	}

	// @Test
	// @ParameterizedTest(name = "{index} => number={0}")
	// @MethodSource("getData")
	// @ArgumentsSource(CustomArgumentProvider.class)
	public void shouldPrintAllUnits_FromRange0To999(final int number) {

		final String format = "Ones count - %03d::%03d";
		assertThat(number, IsBetween.between(1, 9));

		final IntConsumer checker = value -> {

			final int result = hasOnes.applyAsInt(value);

			if (result != 0) {
				assertThat(result, IsBetween.between(1, 9));
				TestsMoneyInWordsOnes.log.info((String.format(format, value, result)));
			}
		};

		IntStream.rangeClosed(1, 999).forEach(checker);
	}

//	static class CustomArgumentProvider implements ArgumentsProvider {
//
//		@Override
//		public Stream<? extends Arguments> provideArguments(final ExtensionContext context) throws Exception {
//
//			return Stream.of(Arguments.of(1));
//
//		}
//	}
//
//	private static Stream<Arguments> getData() {
//
//		return Stream.of(Arguments.of(IntStream.rangeClosed(1, 1)));
//	}
}
