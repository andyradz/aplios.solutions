package com.codigo.aplios.sdk.core.helpers.color;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testy konwersji wartości barwy zapisanej w RGB na HSL")
public class TestRgbToHslColor {

	@Test
	@DisplayName("Test konwersji wartości barwy białej HEX na RGB")
	public void shouldBeHslWhiteColor() {

		final var red = 255;
		final var green = 255;
		final var blue = 255;

		final var converter = ColorConverter.ofRgbToHsl(red, green, blue);

		assertThat(converter.getHue(), is(.0));
		assertThat(converter.getSaturation(), is(.0));
		assertThat(converter.getLightness(), is(100.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy czarnej HEX na RGB")
	public void shouldBeHslBlackColor() {

		final var red = 0;
		final var green = 0;
		final var blue = 0;

		final var converter = ColorConverter.ofRgbToHsl(red, green, blue);

		assertThat(converter.getHue(), is(.0));
		assertThat(converter.getSaturation(), is(.0));
		assertThat(converter.getLightness(), is(.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy zielonej HEX na RGB")
	public void shouldBeHslGreenColor() {

		final var red = 0;
		final var green = 255;
		final var blue = 0;

		final var converter = ColorConverter.ofRgbToHsl(red, green, blue);

		assertThat(converter.getHue(), is(120.0));
		assertThat(converter.getSaturation(), is(100.0));
		assertThat(converter.getLightness(), is(50.0));
	}

	@Test
	@DisplayName("Test konwersji wartości barwy niebieskiej HEX na RGB")
	public void shouldBeHslBlueColor() {

		final var red = 0;
		final var green = 0;
		final var blue = 255;

		final var converter = ColorConverter.ofRgbToHsl(red, green, blue);

		assertThat(converter.getHue(), is(240.0));
		assertThat(converter.getSaturation(), is(100.0));
		assertThat(converter.getLightness(), is(50.0));

	}

	@Test
	@DisplayName("Test konwersji wartości barwy czerwonej HEX na RGB")
	public void shouldBeHslRedColor() {

		final var red = 255;
		final var green = 0;
		final var blue = 0;

		final var converter = ColorConverter.ofRgbToHsl(red, green, blue);

		assertThat(converter.getHue(), is(.0));
		assertThat(converter.getSaturation(), is(100.0));
		assertThat(converter.getLightness(), is(50.0));
	}
}

