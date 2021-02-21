package com.codigo.smartstore.core.money;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.function.IntFunction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Testy wyznaczania kwartału z liczby całkowitej")
class QuaterTests {

	private final IntFunction<Integer> calcQuater = value -> {

		final var calculation = ((value - 1) / 3) + 1;

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

}
