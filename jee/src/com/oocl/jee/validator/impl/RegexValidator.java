package com.oocl.jee.validator.impl;

import com.oocl.jee.validator.Validator;

public class RegexValidator implements Validator {

	private String regex;

	public RegexValidator(String regex) {
		this.regex = regex;
	}

	@Override
	public boolean validate(String string) {
		if (string == null) {
			return false;
		}
		return string.matches(regex);
	}

}
