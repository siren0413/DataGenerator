package com.trading.dataGenerator.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.trading.dataGenerator.dbcp.DBCP;
import com.trading.dataGenerator.domain.StockProfile;
import com.trading.dataGenerator.util.GeneratorUtils;

public class StockDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	public void add(StockProfile profile) {
		try {
			conn = DBCP.getConnection();
			String sql = "insert into Stock values(?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, profile.getId());
			stmt.setTimestamp(2, new Timestamp(profile.getTime().getTime()));
			stmt.setString(3, profile.getName());
			stmt.setDouble(4, profile.getPrice());
			stmt.setString(5, profile.getSymbol());
			stmt.setInt(6, profile.getTs());
			stmt.setInt(7, profile.getVolume());
			stmt.setString(8, profile.getType());
			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCP.releaseConnection(rs, stmt, conn);
		}

	}

	public void update(StockProfile profile) {
		try {
			conn = DBCP.getConnection();
			String sql = "update Stock set tick_time=?, price=?, ts=?, volume=? where symbol=?";
			stmt = conn.prepareStatement(sql);
			stmt.setTimestamp(1, new Timestamp(profile.getTime().getTime()));
			stmt.setDouble(2, profile.getPrice());
			stmt.setInt(3, profile.getTs());
			stmt.setInt(4, profile.getVolume());
			stmt.setString(5, profile.getSymbol());
			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCP.releaseConnection(rs, stmt, conn);
		}
	}

	public void delete(String symbol) {

	}

	public StockProfile queryBySymbol(String symbol) {
		StockProfile profile = null;

		try {
			conn = DBCP.getConnection();
			String sql = "select * from stock where symbol = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, symbol);
			rs = stmt.executeQuery();
			while (rs.next()) {
				profile = new StockProfile();
				
				profile.setId(rs.getString("record_id"));
				profile.setTime(rs.getDate("tick_time"));
				profile.setName(rs.getString("name"));
				profile.setPrice(rs.getDouble("price"));
				profile.setSymbol(rs.getString("symbol"));
				profile.setTs(rs.getInt("ts"));
				profile.setVolume(rs.getInt("volume"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBCP.releaseConnection(rs, stmt, conn);
		}

		return profile;
	}
}
