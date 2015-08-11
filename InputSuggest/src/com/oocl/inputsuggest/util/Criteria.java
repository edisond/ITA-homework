package com.oocl.inputsuggest.util;

public class Criteria {
	public static final String EQUAL = "=";
	public static final String NOT_EQUAL = "!=";
	private String key;
	private Object value;
	private String operation;

	public String getKey() {
		return key;
	}

	public Criteria(String key, Object value, String operation) {
		super();
		this.key = key;
		this.value = value;
		this.operation = operation;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
