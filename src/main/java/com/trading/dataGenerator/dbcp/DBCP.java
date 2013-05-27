package com.trading.dataGenerator.dbcp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCP {

	private static BasicDataSource bds = null;

	static {

		try {
			Properties properties = new Properties();
			properties.load(DBCP.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));
			BasicDataSourceFactory bsf = new BasicDataSourceFactory();
			bds = (BasicDataSource) bsf.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return bds.getConnection();
	}

	public static void releaseConnection(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			stmt = null;
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn = null;
		}

	}

}
