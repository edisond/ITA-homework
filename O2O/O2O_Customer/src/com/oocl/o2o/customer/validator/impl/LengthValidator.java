package com.oocl.o2o.customer.validator.impl;

import com.oocl.o2o.customer.validator.Validator;

public class LengthValidator implements Validator {

	private int minLength;
	private int maxLength;

	public LengthValidator(int minLength, int maxLength) {
		this.minLength = minLength > 0 ? minLength : 0;
		this.maxLength = maxLength > 0 ? maxLength : 0;
	}

	public LengthValidator(int minLength) {
		this.minLength = minLength > 0 ? minLength : 0;
		this.maxLength = -1;
	}

	@Override
	public boolean validate(String string) {
		if (string == null) {
			return false;
		}
		if (maxLength == -1) {
			return string.length() >= minLength;
		} else {
			return string.length() >= minLength && string.length() <= maxLength;
		}
	}

}
