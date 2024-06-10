package tacos;

import lombok.Data;

@Data
public class Ingredient {
	// Lombok creates constructor with id, name, type as arguments since they're 'final'
	private final String id;
	private final String name;
	private final Type type;
	
	// indicates type of ingredient
	public enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}
