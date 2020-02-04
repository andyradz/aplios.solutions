package com.codigo.aplios.group.database;

import java.text.SimpleDateFormat;

public class DateTimeFormats {

	public static final SimpleDateFormat ISO_DATE = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat ISO_TIME = new SimpleDateFormat("HH:mm:ss");
	public static final SimpleDateFormat ISO_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	public static final SimpleDateFormat ISO_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}

/**
 * dd/MM/rrrr 06/03/2007 dd-MMM-rrrr 06-Mar-2007 MM/dd/rrrr 03/06/2007 MMM dd,
 * rrrr Mar 06, 2007 MMMMM dd, rrrr Marzec 06, 2007 rrrr.MM.dd 2007.06.03
 * rrrr/MM/dd 2007/06/03 rrrr-MM-dd 2007-06-03
 *
 */