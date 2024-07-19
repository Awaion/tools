package com.awaion.demo008.uitl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCUtil { // 这个工具类有两大功能:获取连接对象 +　关闭资源 (使用druid的连接池)

	private JDBCUtil() {
	} // 私有化构造器,防止外部创建对象--->强迫外部直接使用类名调用方法

	// 获取连接池
	private static DataSource ds;

	static {
		try {
			Properties p = new Properties();
			InputStream in = JDBCUtil.class.getClassLoader()
					.getResourceAsStream("druid.properties");
			p.load(in);
			

			ds = DruidDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取连接对象-->方法
	public static Connection getConn() throws Exception {
		return ds.getConnection();
	}

	// 关闭所有的资源-->方法  (释放资源)
	public static void close(Connection conn, Statement st, ResultSet rs) {

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

	}

}
