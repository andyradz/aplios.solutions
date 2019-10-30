package com.codigo.aplios.sdk.core.log;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ConsoleLogger implements System.Logger {

	@Override
	public String getName() {

		return "ConsoleLogger";
	}

	@Override
	public boolean isLoggable(final Level level) {

		return true;
	}

	@Override
	public void log(final Level level, final ResourceBundle bundle, final String msg, final Throwable thrown) {

		System.out.printf("ConsoleLogger [%s]: %s - %s%n", level, msg, thrown);
	}

	@Override
	public void log(final Level level, final ResourceBundle bundle, final String format, final Object... params) {

		System.out.printf("ConsoleLogger [%s]: %s%n", level, MessageFormat.format(format, params));
	}
}