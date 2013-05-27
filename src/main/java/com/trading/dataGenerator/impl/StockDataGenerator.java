package com.trading.dataGenerator.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.beanutils.BeanUtils;

import com.trading.dataGenerator.DataGenerator;
import com.trading.dataGenerator.dao.StockDAO;
import com.trading.dataGenerator.domain.StockProfile;
import com.trading.dataGenerator.simulator.StockPriceSimulator;
import com.trading.dataGenerator.util.GeneratorUtils;

public abstract class StockDataGenerator implements DataGenerator {

	private StockDAO dao = new StockDAO();
	
	
	public void generate() {
		try {
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("stockPortfolio.xml");
			StringBuilder sb = GeneratorUtils.readXMLFileIntoString(in);
			List list = GeneratorUtils.XMLStockPaser(sb.toString());
			for(Object obj:list) {
				StockProfile sp = (StockProfile)obj;
				StockThread t = new StockThread();
				StockProfile profile = dao.queryBySymbol(sp.getSymbol());
				if(profile != null) {
					sp.setPrice(profile.getPrice());
					sp.setTs(profile.getTs());
					sp.setVolume(profile.getVolume());
				}else {
					sp.setId(GeneratorUtils.getId());
					sp.setTime(new Date());
					dao.add(sp);
				}
				BeanUtils.copyProperties(t, sp);
				Thread thread = new Thread(t);
				thread.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public abstract void getStockFeed(String name, double price, String symbol, int ts, String type, int volume);
	
	
	public class StockThread implements Runnable{
		private String name;
		private double price;
		private String symbol;
		private int ts;
		private String type;
		private int volume;
		private int tick;
		private int dividor;
		private int jump;
		private StockPriceSimulator simulator = new StockPriceSimulator();
		private Random random = new Random(System.currentTimeMillis());
		private StockDAO dao = new StockDAO();
		private StockProfile profile = new StockProfile();
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getSymbol() {
			return symbol;
		}
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		public int getTs() {
			return ts;
		}
		public void setTs(int ts) {
			this.ts = ts;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public int getVolume() {
			return volume;
		}
		public void setVolume(int volume) {
			this.volume = volume;
		}
		public int getTick() {
			return tick;
		}
		public void setTick(int tick) {
			this.tick = tick;
		}
		
		public int getDividor() {
			return dividor;
		}
		public void setDividor(int dividor) {
			this.dividor = dividor;
		}
		
		public int getJump() {
			return jump;
		}
		public void setJump(int jump) {
			this.jump = jump;
		}
		
		
		public void run() {
			while(true) {
				try {
					Thread.sleep(random.nextInt(tick));
					price = simulator.simulate(price, dividor, jump);
					getStockFeed(name, price, symbol, ts, type, volume);
					BeanUtils.copyProperties(profile, this);
					profile.setId(GeneratorUtils.getId());
					profile.setTime(new Date());
					dao.update(profile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}

}
