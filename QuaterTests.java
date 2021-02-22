package com.codigo.smartstore.core.money;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Testy wyznaczania kwartału z liczby całkowitej")
class QuaterTests {

	private final IntFunction<Integer> calcQuater = value -> {

		final var calculation = ((value - 1) / 3) + 1;

		return calculation;
	};

	private final Function<LocalDate, Integer> calcQuater1 = date -> {

		final var month = date.getMonthValue();
		final var calculation = ((month - 1) / 3) + 1;

		return calculation;
	};

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3 })
	void testQuater1(final int input) {

		final var quater = this.calcQuater.apply(input);

		assertThat(1, equalTo(quater));
	}

	@ParameterizedTest
	@ValueSource(ints = { 4, 5, 6 })
	void testQuater2(final int input) {

		final var quater = this.calcQuater.apply(input);
		assertThat(2, equalTo(quater));
	}

	@ParameterizedTest
	@ValueSource(ints = { 7, 8, 9 })
	void testQuater3(final int input) {

		final var quater = this.calcQuater.apply(input);
		assertThat(3, equalTo(quater));
	}

	@ParameterizedTest
	@ValueSource(ints = { 10, 11, 12 })
	void testQuater4(final int input) {

		final var quater = this.calcQuater.apply(input);
		assertThat(4, equalTo(quater));
	}

	@ParameterizedTest
	@ValueSource(ints = { 10, 11, 12, 13 })
	void testNotQuater1(final int input) {

		final var quater = this.calcQuater.apply(input);

		assertThat(1, not(equalTo(quater)));
	}

	@ParameterizedTest
	@ValueSource(ints = { 10, 11, 12, 13 })
	void testNotQuater2(final int input) {

		final var quater = this.calcQuater.apply(input);
		assertThat(2, not(equalTo(quater)));
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3, 13 })
	void testNotQuater3(final int input) {

		final var quater = this.calcQuater.apply(input);
		assertThat(3, not(equalTo(quater)));

	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 3, 13 })
	void testNotQuater4(final int input) {

		final var quater = this.calcQuater.apply(input);
		assertThat(4, not(equalTo(quater)));
	}

	@ParameterizedTest
	@MethodSource("provideQuater1Dates")
	void testQuater1_ByMethod(final LocalDate input) {

		final var quater = this.calcQuater1.apply(input);
		assertThat(1, equalTo(quater));
	}

	/**
	 * Procedura generuje kolekcję danych testowych jako strumień dat
	 * @return strumień dat
	 */
	private static Stream<Arguments> provideQuater1Dates() {

		return Stream.of(
			Arguments.of(LocalDate.of(2021, 1, 1)),
			Arguments.of(LocalDate.now()
					.plusDays(1)),
			Arguments.of(LocalDate.now()
					.plusDays(2)),
			Arguments.of(LocalDate.now()
					.plusDays(3)),
			Arguments.of(LocalDate.now()
					.plusDays(4)),
			Arguments.of(LocalDate.now()
					.plusDays(5)),
			Arguments.of(LocalDate.now()
					.plusDays(37))
		);
	}

}
