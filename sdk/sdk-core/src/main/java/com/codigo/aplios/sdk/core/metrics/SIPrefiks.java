package com.codigo.aplios.sdk.core.metrics;

import java.util.EnumSet;
import java.util.Set;

/**
 * Definicje prefiksów miar układu SI
 *
 * @author dp0470
 *
 */
enum SIPrefiks {

	YOTTA("Y", "OKTO"), ZETTA("Z", "SEPTEM"), EKSA("E", "HEKS"), PETA("P", "PENTE"), TERA("T", "TERAS"),
	GIGA("G", "GIGAS"), MEGA("M", "MEGAS"), KILO("k", "CHILIOI");

	private static EnumSet<SIPrefiks> prefixSet = EnumSet.allOf(SIPrefiks.class);

	private SIPrefiks(final String symbol, final String name) {

	}

	public static Set<SIPrefiks> getPrefiks() {

		return SIPrefiks.prefixSet;
	}

}