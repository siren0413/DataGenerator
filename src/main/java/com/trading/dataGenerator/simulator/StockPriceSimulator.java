package com.trading.dataGenerator.simulator;

import java.util.Random;

public class StockPriceSimulator implements Simulator {

	private Random random = new Random(System.currentTimeMillis());
	private int number = -1;

	public double simulate(double price, int dividor, int jump) {
		number = random.nextInt(jump);

		if (number == 0) {
			price = price + ((random.nextDouble() - 0.5) * (price / dividor)) * 20;
		} else if (number == 0 || number == 1) {
			price = price + ((random.nextDouble() - 0.5) * (price / dividor)) * 15;
		} else if (number == 0 || number == 1 || number == 3) {
			price = price + ((random.nextDouble() - 0.5) * (price / dividor)) * 12;
		} else if (number == 0 || number == 1 || number == 3 || number == 4) {
			price = price + ((random.nextDouble() - 0.5) * (price / dividor)) * 10;
		} else if (number == 0 || number == 1 || number == 3 || number == 4 || number == 5) {
			price = price + ((random.nextDouble() - 0.5) * (price / dividor)) * 8;
		} else if (number == 0 || number == 1 || number == 3 || number == 4 || number == 5 || number == 6) {
			price = price + ((random.nextDouble() - 0.5) * (price / dividor)) * 5;
		} else if (number == 0 || number == 1 || number == 3 || number == 4 || number == 5 || number == 6 || number == 7) {
			price = price + ((random.nextDouble() - 0.5) * (price / dividor)) * 4;
		} else if (number == 0 || number == 1 || number == 3 || number == 4 || number == 5 || number == 6 || number == 7
				|| number == 8) {
			price = price + ((random.nextDouble() - 0.5) * (price / dividor)) * 3;
		} else if (number == 0 || number == 1 || number == 3 || number == 4 || number == 5 || number == 6 || number == 7
				|| number == 8 || number == 9) {
			price = price + ((random.nextDouble() - 0.5) * (price / dividor)) * 2;
		} else {
			price = price + (random.nextDouble() - 0.5) * (price / dividor);
		}
		return price;
	}
}
