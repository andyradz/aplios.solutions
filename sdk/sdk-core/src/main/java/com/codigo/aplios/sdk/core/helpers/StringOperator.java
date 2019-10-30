package com.codigo.aplios.sdk.core.helpers;

import java.util.Objects;

/**
 * Klasa realizuje wspólne operacje na klasie <code>String</code>
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2019
 * @category operator
 */
public final class StringOperator {

	/**
	 * Podstawowy konstruktor obiektu klasy <code>StringOperator</code>
	 */
	private StringOperator() {

	}

	/**
	 * Metoda sprawdza czy zawartość stringa jest nieokreślona, jest pusta lub
	 * zawiera same spacje
	 *
	 * @param string Ciąg znaków poddany analizie
	 * @return Wartość logiczna
	 *
	 */
	public static boolean isNullOrEmpty(final String string) {

		return (Objects.isNull(string) || string.isEmpty() || string.isBlank());
	}
}
