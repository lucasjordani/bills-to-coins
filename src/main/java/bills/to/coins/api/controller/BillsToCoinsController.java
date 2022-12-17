package bills.to.coins.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bills.to.coins.api.record.CoinRecord;
import bills.to.coins.api.service.BillsToCoinsService;

@RestController
@RequestMapping("/change")
public class BillsToCoinsController {
	
	@Autowired
	BillsToCoinsService billsToCoinsService;

	@GetMapping("/least/{value}")
	public String getLeastAmountOfChange(@PathVariable(required=true) int value) {
		return billsToCoinsService.generateLeastAmountOfChange(value);
	}
	
	@GetMapping("/most/{value}")
	public String getMostAmountOfChange(@PathVariable(required=true) int value) {
		return billsToCoinsService.generateMostAmountOfChange(value);
	}

	@GetMapping("/coins")
	public String getCoins() {
		return billsToCoinsService.getCoins();
	}
	
	@PostMapping("/set")
	public void changeCoinValue(@RequestBody CoinRecord coinRecord) {
		billsToCoinsService.changeCoinValue(coinRecord);
	}
	
}
