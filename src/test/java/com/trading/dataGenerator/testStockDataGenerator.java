package com.trading.dataGenerator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.trading.dataGenerator.impl.StockDataGenerator;

public class testStockDataGenerator {

	private StockDataGenerator sdg = new StockDataGenerator() {

		@Override
		public void getStockFeed(String name, double price, String symbol, int ts, String type, int volume) {
			System.out.println("---------------------------------------");
			System.out.println(name + "-" + price);
			System.out.println("---------------------------------------");

		}
	};

	@Test
	public void testGenerate() {
		sdg.generate();
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
