package bills.to.coins.api.service;

import bills.to.coins.api.record.CoinRecord;

public interface BillsToCoinsService {
	
	public String generateLeastAmountOfChange(int value);
	
	public String generateMostAmountOfChange(int value);
	
	public String getCoins();

	public void changeCoinValue(CoinRecord coin);

}
