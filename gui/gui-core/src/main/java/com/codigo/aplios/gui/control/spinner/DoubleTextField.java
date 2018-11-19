package com.codigo.aplios.gui.control.spinner;

import javafx.scene.control.TextField;

public class DoubleTextField extends TextField {

	public DoubleTextField() {

		super();
	}

	// http://utilitymill.com/utility/Regex_For_Range
	String numberRegEx = "\\b([0-9]{1,2}|[1-6][0-9]{2}|7[0-3][0-9]|74[0-4])\\b";

	@Override
	public void replaceText(final int start, final int end, final String text) {

		final String oldValue = getText();
		if ((validate(text))) {
			super.replaceText(start, end, text);
			final String newText = super.getText();
			if (!validate(newText))
				super.setText(oldValue);
		}
	}

	@Override
	public void replaceSelection(final String text) {

		final String oldValue = getText();
		if (validate(text)) {
			super.replaceSelection(text);
			final String newText = super.getText();
			if (!validate(newText))
				super.setText(oldValue);
		}
	}

	private boolean validate(final String text) {

		return ("".equals(text) || text.matches(this.numberRegEx));
	}
}
