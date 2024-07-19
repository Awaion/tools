package com.awaion.demo008.handler.impl;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.awaion.demo008.handler.IResultSetHandler;

public class BeanListHandler<T> implements IResultSetHandler<List<T>> {
	
	private Class<T> clz;  //字节码对象
	public BeanListHandler(Class<T> clz){
		this.clz = clz;
	}
	@Override
	public List<T> handle(ResultSet rs) throws Exception {
		
		List<T> list = new ArrayList<>();
		PropertyDescriptor[] pds = Introspector.getBeanInfo(clz, Object.class).getPropertyDescriptors();  
		//获取Clz字节码对应的属性描述器数组---用来获取这个类的所有属性.
		while(rs.next()) {
			T obj = clz.newInstance();  //使用字节码对象 创建新的对象.
			for (PropertyDescriptor pd : pds) {
				String columnName = pd.getName(); //获得类对应的属性名,也就是数据库中列名
				Object columnValue = rs.getObject(columnName);  //根据类名获取对应的值
				
				pd.getWriteMethod().invoke(obj, columnValue); //给这个obj对象的对应属性设值---调用setter方法
			}
			list.add(obj);
			
		}
		return list;
	}

}
