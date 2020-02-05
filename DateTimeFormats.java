package com.codigo.aplios.group.database;

import java.text.SimpleDateFormat;

//https://stackoverflow.com/questions/45344057/how-to-resolve-the-make-field-an-instance-variable-issue
public class DateTimeFormats {

	public static final SimpleDateFormat ISO_DATE;
	public static final SimpleDateFormat ISO_TIME;
	public static final SimpleDateFormat ISO_TIMESTAMP;
	public static final SimpleDateFormat ISO_DATETIME;

	static {
		ISO_DATE = new SimpleDateFormat("yyyy-MM-dd");
		ISO_TIME = new SimpleDateFormat("HH:mm:ss");
		ISO_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		ISO_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * Prywatny domyślny konstruktor obiektu klasy <code>DateTimeFormats</code>
	 * Definicja blokuje możliwość tworzenia instancji klasy
	 * <code>DateTimeFormats</code>
	 */
	private DateTimeFormats() {

	}
}

/**
 * dd/MM/rrrr 06/03/2007 dd-MMM-rrrr 06-Mar-2007 MM/dd/rrrr 03/06/2007 MMM dd,
 * rrrr Mar 06, 2007 MMMMM dd, rrrr Marzec 06, 2007 rrrr.MM.dd 2007.06.03
 * rrrr/MM/dd 2007/06/03 rrrr-MM-dd 2007-06-03
 *
 */