package bills.to.coins.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Coin {
	
	private double value;
	private int quantity;


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getAvailableValue() {
		return this.quantity * this.value;
	}
}
