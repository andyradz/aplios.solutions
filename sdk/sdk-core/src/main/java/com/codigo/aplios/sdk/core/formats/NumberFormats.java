package com.codigo.aplios.sdk.core.formats;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Klasa zawiera maski formatowania liczb rzeczywistych
 *
 * @author andrzej.radziszewski
 * @since 2018
 * @version 1.0.0.0
 * @category specyfication
 *
 */
public class NumberFormats {

	private static final DecimalFormatSymbols rr;

	static {
		rr = DecimalFormatSymbols.getInstance();
		rr.setGroupingSeparator('.');
	}

	public static final DecimalFormat NSIGN_AFTER = new DecimalFormat("#+,##0.00;#-,##0.00", rr);

	public static final DecimalFormat NSIGN_BEFORE = new DecimalFormat("+#,##0.00;-#,##0.00", rr);

	public static final DecimalFormat DOT_SEPARATOR = new DecimalFormat("+#,##0.00;-#,##0.00", rr);

	private NumberFormats() {

		super();
	}

}
