package bills.to.coins.api.service.impl;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import bills.to.coins.api.entity.Coin;
import bills.to.coins.api.entity.Coins;
import bills.to.coins.api.record.CoinRecord;
import bills.to.coins.api.service.BillsToCoinsService;

@Service
public class BillsToCoinsServiceImpl implements BillsToCoinsService {

	protected static final String THE_COINS_ARE_OVER = "The coins are over";
	protected static final String NOT_ENOUGH_COINS = "There is not enough coins for this value";
	
	Coins coins = new Coins();
	
	@Override
	public String generateMostAmountOfChange(int value) {
		if (!hasEnoughCoins(value)) {
			return NOT_ENOUGH_COINS;
		}
		List<Coin> coinsList = coins.getCoins();
		coinsList.sort(Comparator.comparingDouble(Coin::getValue));
		return generateChange(value, coinsList);
	}

	@Override
	public String generateLeastAmountOfChange(int value) {
		if (!hasEnoughCoins(value)) {
			return NOT_ENOUGH_COINS;
		}
		List<Coin> coinsList = coins.getCoins();
		return generateChange(value, coinsList);
	}

	@Override
	public String getCoins() {
		return coins.toString();
	}

	@Override
	public void changeCoinValue(CoinRecord coinRecord) {
		List<Coin> coinsList = coins.getCoins();
		coinsList.forEach(currentCoin -> {
			if (currentCoin.getValue() == coinRecord.value()) {
				currentCoin.setQuantity(coinRecord.quantity());
			}
		});
	}

	protected String generateChange(double value, List<Coin> coinsList) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Total[ ");
		for (int i = 0; i < coinsList.size(); i++) {
			Coin currentCoin = coinsList.get(i);
			value = changeIterator(value, currentCoin, stringBuilder);
			if(value == 0) {
				break;
			}
		}
		if (value > 0) {
			return THE_COINS_ARE_OVER;
		}
		stringBuilder.append(" ]");
		return stringBuilder.toString();
	}

	protected double changeIterator(double value, Coin currentCoin, StringBuilder stringBuilder) {
		double result = value / currentCoin.getValue();
		if (result > 0) {
			if (result > currentCoin.getQuantity()) {
				result = currentCoin.getQuantity();
				currentCoin.setQuantity(0);
				
			} else {
				Integer resultCoins = (int) (currentCoin.getQuantity() - result);
				currentCoin.setQuantity(resultCoins);
			}
			stringBuilder.append(" [ " + currentCoin.getValue() + " cents coins: " + result + "] ");
		}
		return value - (result * currentCoin.getValue());
	}
	
	protected boolean hasEnoughCoins(double value) {
		if (coins.getAvailableValue() >= value) {
			return true;
		}
		return false;
	}
}
