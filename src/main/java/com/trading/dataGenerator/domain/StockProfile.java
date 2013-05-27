package com.trading.dataGenerator.domain;

import java.util.Date;

public class StockProfile {
	
	private String id;
	private Date time;
	private String name;
	private double price;
	private String symbol;
	private int ts;
	private String type;
	private int volume;
	private int tick;
	private int dividor;
	private int jump;

	public StockProfile() {

	}

	public StockProfile(String name, double price, String symbol, int ts, String type, int volume, int tick, int dividor, int jump) {
		super();
		this.name = name;
		this.price = price;
		this.symbol = symbol;
		this.ts = ts;
		this.type = type;
		this.volume = volume;
		this.tick = tick;
		this.dividor = dividor;
		this.jump = jump;
	}
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
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof StockProfile))
			return false;
		StockProfile sq = (StockProfile) obj;
		if (name.equals(sq.getName()) && price == sq.getPrice() && symbol.equals(sq.getSymbol()) && ts == sq.getTs()
				&& type.equals(sq.getType()) && volume == sq.getVolume() && tick == sq.getTick() && dividor == sq.getDividor() && jump == sq.getJump()) {
			return true;
		}
		return false;
	}

}
