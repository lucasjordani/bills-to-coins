package bills.to.coins.api.entity;

import java.util.ArrayList;
import java.util.List;

public class Coins {
	
	List<Coin> coins;
	
	public Coins() {
		coins = new ArrayList<>();
		coins.add(new Coin(0.25, 100));
		coins.add(new Coin(0.1, 100));
		coins.add(new Coin(0.05, 100));
		coins.add(new Coin(0.01, 100));
	}


	public List<Coin> getCoins() {
		return coins;
	}
	
	public double getAvailableValue() {
		double totalValueAvailable = 0;
		for (int i = 0; i < coins.size(); i++) {
			totalValueAvailable += coins.get(i).getAvailableValue();
		};
		return totalValueAvailable;
	}


	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Coins [");
		coins.forEach(coin -> {
			stringBuilder.append("  [" + coin.getValue() + " cents coins: " + coin.getQuantity() + "]  ");
		});
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
	
	
}
