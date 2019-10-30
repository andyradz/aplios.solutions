package com.codigo.aplios.sdk.core;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.codigo.aplios.sdk.core.hamcrest.IsBetween;
import com.codigo.aplios.sdk.core.hamcrest.MoneyInWordsModel;

public class TestsMoneyInWordsTens {

	private static Logger log = Logger.getLogger(TestsMoneyInWordsOnes.class);

	/**
	 * Znacznik wartości dodatniej liczby rzeczywistej
	 */
	private static final double POSITIVE_SIGN = 1.;

	/**
	 * Znacznik wartości ujemnej liczby rzeczywistej
	 */
	private static final double NEGATIVE_SIGN = -1.;

	/**
	 * Oprator wyznacznia jednostek z wartości liczby
	 */
	private static IntUnaryOperator hasOnes = in -> ((in / 10) % 10) == 1 ? 0 : in % 10;

	/**
	 * Oprator wyznacznia dziesiątek z wartości liczby
	 */
	private static IntUnaryOperator hasTens = in -> ((in % 100) % 10) == 0 ? in % 100 : 0;

	/**
	 * Oprator wyznacznia setek z wartości liczby
	 */
	private static IntUnaryOperator hasHunds = in -> ((in % 1000) % 100) == 0 ? in % 1000 : 0;

	private final Integer[] array3 = IntStream.iterate(10, i -> i + 10).limit(9).boxed().toArray(Integer[]::new);

	@Test
	public void test1100() {

		for (final Integer item : this.array3)
			assertThat(item, is(hasTens.applyAsInt(item)));
	}

	@Test
	public void shouldReturnZeroUnits_From910() {

		final var value = 910;

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

	@Test
	public void shouldPrintAllUnits_FromRange0To999() {

		final String format = "Tens count - %03d::%03d";

		final IntConsumer checker = value -> {

			final int result = hasOnes.applyAsInt(value);

			if (result != 0) {
				assertThat(result, IsBetween.between(1, 9));
				log.info((String.format(format, value, result)));
			}
		};

		IntStream.rangeClosed(1, 999).forEach(checker);
	}

	// testowanie dziesiątek, zero nie jest przekazywane
	// -----------------------------------------------------------------------------------------------------------------

	@Test
	public void testUnit2_0() {

		final String format = "Tens count %03d - %03d";

		final IntConsumer checker = e -> {

			final int result = hasTens.applyAsInt(e);

			if (result != 0) {

				assertThat(result, IsBetween.between(10, 90));
				log.info(String.format(format, e, result));
			}
		};

		IntStream.rangeClosed(1, 999).forEach(checker);
	}

	// testowanie setek, zero nie jest przekazywane
	// -----------------------------------------------------------------------------------------------------------------

	@Test
	public void testUnit3_0() {

		final String format = "Hunds count %03d - %03d";

		final IntConsumer checker = e -> {

			final int result = TestsMoneyInWordsTens.hasHunds.applyAsInt(e);

			if (result != 0) {

				assertThat(result, IsBetween.between(100, 900));
				log.info(String.format(format, e, result));
			}
		};

		IntStream.rangeClosed(1, 999).forEach(checker);
	}

	@Disabled
	public void testLoadProperties() {

		final Properties properties = new Properties();

		final Map<String, String> map = new HashMap<>();
		try {
			final InputStream input = TestsMoneyInWordsOnes.class.getClassLoader()
					.getResourceAsStream("serpconfig.properties");
			final InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8);

			properties.load(reader);

			map.putAll(properties.entrySet().stream()
					.collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString())));

			final BiConsumer<Object, Object> biConsumer = (key, value) -> log.info("Key:" + key + " Value:" + value);

			properties.forEach(biConsumer);

		} catch (final IOException e) {
			log.error(e);
		}

	}

	@Test
	@DisplayName("Test znaku wartość liczby dodatniej rzeczywistej")
	public void sholudBePositiveNumber() {

		final double value = .099;

		final double sign = Math.signum(value);

		assertThat(TestsMoneyInWordsTens.POSITIVE_SIGN, is(sign));
	}

	@Test
	@DisplayName("Test znaku wartość liczby ujemnej rzeczywistej")
	public void sholudBeNegativeNumber() {

		final double value = -.099;

		final double sign = Math.signum(value);

		assertThat(TestsMoneyInWordsTens.NEGATIVE_SIGN, is(sign));
	}

	Function<Long, Long> calc;

	@Test
	public void TestDivideNumber() {

		final StringBuilder inwords = new StringBuilder();
		inwords.setLength(0);

		this.calc = val -> {

			if (val != 0L) {
				this.calc.apply(val / 1_000L);
				inwords.append(String.format("%03d_", val % 1_000L));
				MoneyInWordsModel.builder(1).assignUnitCount(0).assignTeenCount(1).assignTenCount(2).assignHundCount(1)
						.build();
				return val / 1_000L;
			}
			return 0L;
		};

		final long value = 8_512_200_324_199_201_316L;

		this.calc.apply(value);
		log.info(inwords);

//		Money.init(Currency.getInstance("PLN"), RoundingMode.HALF_UP);
//		final Money money = new Money(BigDecimal.valueOf(-82_512_200_324_199.99), Currency.getInstance("PLN"));
//		money.isMinus();
//		log.info(money);

	}
}
