package tacos;

import java.util.List;
import java.util.ArrayList;
import lombok.Data;

@Data
public class TacoOrder {
	// delivery info
	private String deliveryName;
	private String deliveryStreet;
	private String deliveryCity;
	private String deliveryState;
	private String deliveryZip;

	// payment info
	private String ccNumber;
	private String ccExpiration;
	private String ccCVV;
	
	// list of tacos in the order
	private List<Taco> tacos = new ArrayList<>();
	
	// used to add tacos to the order list
	public void addTaco (Taco taco) {
		this.tacos.add(taco);
	}
}
