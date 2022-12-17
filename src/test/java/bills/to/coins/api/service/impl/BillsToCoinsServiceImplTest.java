package bills.to.coins.api.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import bills.to.coins.api.entity.Coins;
import bills.to.coins.api.record.CoinRecord;

public class BillsToCoinsServiceImplTest {

	@InjectMocks
	BillsToCoinsServiceImpl billsToCoinsService;
	
	Coins coins = new Coins();
	
	@BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testGenerateMostAmountReturns100UnitsOfAllCoins() {
		String result = billsToCoinsService.generateMostAmountOfChange(41);
		
		assertEquals("Total[  [ 0.01 cents coins: 100.0]  [ 0.05 cents coins: 100.0]  [ 0.1 cents coins: 100.0]  [ 0.25 cents coins: 100.0]  ]", result);
	}
	
	@Test
	public void testGenerateMostAmountReturns100Units1CentCoin100Units5CentsCoin40Units1CentCoin() {
		String result = billsToCoinsService.generateMostAmountOfChange(10);
		
		assertEquals("Total[  [ 0.01 cents coins: 100.0]  [ 0.05 cents coins: 100.0]  [ 0.1 cents coins: 40.0]  ]", result);
	}
	
	@Test
	public void testGenerateMostAmountReturnsNotEnoughCoins() {
		String result = billsToCoinsService.generateMostAmountOfChange(100);
		
		assertEquals(billsToCoinsService.NOT_ENOUGH_COINS, result);
	}
	
	@Test
	public void testGenerateLeastAmountReturns100UnitsOfAllCoins() {
		String result = billsToCoinsService.generateLeastAmountOfChange(41);
		
		assertEquals("Total[  [ 0.25 cents coins: 100.0]  [ 0.1 cents coins: 100.0]  [ 0.05 cents coins: 100.0]  [ 0.01 cents coins: 100.0]  ]", result);
	}
	
	@Test
	public void testGenerateLeastAmountReturns40Units25CentsCoins() {
		String result = billsToCoinsService.generateLeastAmountOfChange(10);
		
		assertEquals("Total[  [ 0.25 cents coins: 40.0]  ]", result);
	}
	
	@Test
	public void testGenerateLeastAmountReturnsNotEnoughCoins() {
		String result = billsToCoinsService.generateLeastAmountOfChange(100);
		
		assertEquals(billsToCoinsService.NOT_ENOUGH_COINS, result);
	}
	
	@Test
	public void testChangeIteratorReturnsTheCoinsOver() {
		String result = billsToCoinsService.generateChange(43, coins.getCoins());
		
		assertEquals(billsToCoinsService.THE_COINS_ARE_OVER, result);
	}
	
	@Test
	public void testHasNotEnoughCoinsReturnFalse() {
		boolean result = billsToCoinsService.hasEnoughCoins(45);
		
		assertFalse(result);
	}
	
	@Test
	public void testHasNotEnoughCoinsReturnTrue() {
		boolean result = billsToCoinsService.hasEnoughCoins(23);
		
		assertTrue(result);
	}
	
	@Test
	public void testChangeCoinValue() {
		CoinRecord coinRecord = new CoinRecord(0.10, 230);
		billsToCoinsService.changeCoinValue(coinRecord);
		
		String result = billsToCoinsService.getCoins();
		
		assertEquals("Coins [  [0.25 cents coins: 100]    [0.1 cents coins: 230]    [0.05 cents coins: 100]    [0.01 cents coins: 100]  ]", result);
	}
	

	
	
}
