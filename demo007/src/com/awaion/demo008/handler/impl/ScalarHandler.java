package com.awaion.demo008.handler.impl;

import java.sql.ResultSet;

import com.awaion.demo008.handler.IResultSetHandler;

public class ScalarHandler implements IResultSetHandler<Long> {

	@Override
	public Long handle(ResultSet rs) throws Exception {

		Long value = 0L;
		if (rs.next()) {
			value = rs.getLong(1);
		}
		return value;
	}

}
