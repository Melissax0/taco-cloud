package tacos;

import java.util.List;
import lombok.Data;

@Data	// Lombok auto generates essential JavaBean methods at compile time
public class Taco {
	// name of taco
	private String name;
	
	// list of ingredients for the taco
	private List<Ingredient> ingredients;
}
