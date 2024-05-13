package com.awaion.demo008.handler;

import java.sql.ResultSet;

public interface IResultSetHandler<T> {
	
	T handle(ResultSet rs) throws Exception;  //处理结果集的handle方法

}
