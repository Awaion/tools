package com.awaion.demo008.uitl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.awaion.demo008.handler.IResultSetHandler;

public class JDBCTemplate { // 这个工具类两大功能 : 处理DML 操作的语句 + 处理DQL操作的语句

	private JDBCTemplate() {
	} // 私有化构造器

	public static void update(String sql, Object... params) {

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtil.getConn();
			ps = conn.prepareStatement(sql);

			// 给sql语句中的占位符设值参数
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			// 执行语句
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 释放资源
			JDBCUtil.close(conn, ps, null);
		}
	}

	public static <T> T query(String sql, IResultSetHandler<T> rsh,
			Object...params) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil.getConn();
			ps = conn.prepareStatement(sql);
			// 给sql语句中的占位符设值参数
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			// 执行语句
			rs = ps.executeQuery();
			return rsh.handle(rs); // 返回 结果集被处理后的结果

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
