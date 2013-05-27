package com.trading.dataGenerator.dbcp;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class TestDBCP {

	@Test
	public void testGetConnection() {
		try {
			Connection conn = DBCP.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReleaseConnection() {
		
	}

}
