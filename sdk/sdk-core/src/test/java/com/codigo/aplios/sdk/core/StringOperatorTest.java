package com.codigo.aplios.sdk.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static com.codigo.aplios.sdk.core.helpers.StringOperator.isNullOrEmpty;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Klasa wykonuje testy operatora dla klasy <code>String</code>
 *
 * @author andrzej.radziszewski
 *
 */
@DisplayName("Implementacja testów operatora StringOperator")
public class StringOperatorTest {

	@Test
	@DisplayName("Metoda wykonuje test wartość null - oczekuje TRUE")
	public void testNullString_sholudRetrunTrue() {

		// arrange
		final String chain = null;

		// assert
		assertTrue(isNullOrEmpty(chain));
	}

	@Test
	@DisplayName("Metoda wykonuje test wartość \"01234  56789\" - oczekuje FALSE")
	public void testNotNullString_RetrunFalse() {

		// arrange
		final String chain = "01234  56789";

		// assert
		assertFalse(isNullOrEmpty(chain));
	}

	@Test
	@DisplayName("Metoda wykonuje test wartość \"\" - oczekuje TRUE")
	public void testEmptyString_sholudRetrunFalse() {

		// arrange
		final String chain = "";

		// assert
		assertTrue(isNullOrEmpty(chain));
	}

	@Test
	@DisplayName("Metoda wykonuje test wartość \"     \" - oczekuje TRUE")
	public void testBlankString_sholudRetrunFalse() {

		// arrange
		final String chain = "     ";

		// assert
		assertTrue(isNullOrEmpty(chain));
	}

	@Test
	@DisplayName("Metoda wykonuje test wartość \" ------\" - oczekuje TRUE")
	public void testLeadingString_sholudRetrunFalse() {

		// arrange
		final String chain = " ------";

		// assert
		assertFalse(isNullOrEmpty(chain));
	}

	@Test
	@DisplayName("Metoda wykonuje test wartość \" ------\" - oczekuje TRUE")
	public void testDoingString_sholudRetrunFalse() {

		// arrange
		final String chain = "------ ";

		// assert
		assertFalse(isNullOrEmpty(chain));
	}

}
