package com.trading.dataGenerator.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.trading.dataGenerator.dbcp.DBCP;
import com.trading.dataGenerator.domain.StockProfile;

public class StockDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	public void add(StockProfile profile) {
		try {
			conn = DBCP.getConnection();
			String sql = "insert into Stock values(?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, profile.getId());
			stmt.setDate(2, new Date(profile.getTime().getTime()));
			stmt.setString(3, profile.getName());
			stmt.setDouble(4, profile.getPrice());
			stmt.setString(5, profile.getSymbol());
			stmt.setInt(6, profile.getTs());
			stmt.setString(7, profile.getType());
			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCP.releaseConnection(rs, stmt, conn);
		}

	}

	public void update(StockProfile profile) {

	}

	public void delete(String symbol) {

	}

	public StockProfile queryByUpdated() {
		return null;
	}

}
