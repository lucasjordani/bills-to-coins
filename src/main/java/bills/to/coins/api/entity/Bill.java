package bills.to.coins.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Bill {
	
	private int value;
	private int quantity;


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
