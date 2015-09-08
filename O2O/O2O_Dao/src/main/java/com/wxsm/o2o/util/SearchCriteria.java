package com.wxsm.o2o.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Aquariuslt
 * @version 15-08-05
 */
@SuppressWarnings("all")
public class SearchCriteria {

	// for page number
	private Integer start;
	private Integer length;

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	// for sorting
	private String order;
	private String seq;

	// for query criteria
	private List<Criteria> criterias;

	public SearchCriteria() {
		this.criterias = new LinkedList<Criteria>();
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public List<Criteria> getCriteria() {
		return criterias;
	}

	public String buildSQL(String tableName) {
		String sql = "SELECT * FROM " + tableName + " WHERE 1=1";
		for (Criteria c : criterias) {
			sql += " AND " + c.getKey() + c.getOperation() + c.getValue();
		}
		if (order != null) {
			sql += " ORDER BY " + order + " ";
			if (seq != null) {
				sql += seq;
			}
		}
		if (start != null && length != null) {
			sql += " LIMIT " + start + " , " + length + " ";
		}
		System.out.println("Build Criteria SQL:" + sql);
		return sql;
	}

	public String buildSQLWhere() {
		String sql = " WHERE 1=1";
		for (Criteria c : criterias) {
			sql += " AND " + c.getKey() + c.getOperation() + c.getValue();
		}
		if (order != null) {
			sql += " ORDER BY " + order + " ";
			if (seq != null) {
				sql += seq;
			}
		}
		if (start != null && length != null) {
			sql += " LIMIT " + start + " , " + length + " ";
		}
		System.out.println("Build Criteria SQL:" + sql);
		return sql;
	}
}
