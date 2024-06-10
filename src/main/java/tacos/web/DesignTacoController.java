package tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.TacoOrder;


@Slf4j	// creates log*
@Controller	// creates identifier that this class is a component of the Spring container
@RequestMapping("/design")	// specifies that this class will handle requests beginning with "/design"
@SessionAttributes("tacoOrder")	// specifies that tacoOrder objects need to stay in session thru mult requests
public class DesignTacoController {
	@ModelAttribute	// this method will run every time there is a request for this class
	public void addIngredientsToModel(Model model) { // model is object that moves data btwn controller & view
		List<Ingredient> ingredients = Arrays.asList( // hard-coded list of ingredients
				new Ingredient("FLTO", "Flour Tortilla", Type.WRAP), 
				new Ingredient("COTO", "Corn Tortilla", Type.WRAP), 
				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN), 
				new Ingredient("CARN", "Carnitas", Type.PROTEIN), 
				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES), 
				new Ingredient("LETC", "Lettuce", Type.VEGGIES), 
				new Ingredient("CHED", "Cheddar", Type.CHEESE), 
				new Ingredient("JACK", "Monterrey Jack", Type.CHEESE), 
				new Ingredient("SLSA", "Salsa", Type.SAUCE), 
				new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
		);
		
		Type[] types = Ingredient.Type.values(); // list of all types of ingredients
		for (Type type : types) { // organize ingredients by type into lists & add lists as attributes to model
			model.addAttribute(type.toString().toLowerCase(), 
					filterByType(ingredients, type));
		}
	}
	
	@ModelAttribute(name = "tacoOrder") // tacoOrder will hold state while the order is being built (?)
	public TacoOrder order() {
		return new TacoOrder();
	}
	
	@ModelAttribute(name = "taco") // taco added to model so that the view is rendered w/ non-null object
	public Taco taco() {
		return new Taco();
	}
	
	@GetMapping // this method is paired with @RequestMapping, it will run every time there is a request here
	public String showDesignForm() {
		return "design";
	}
	
	@PostMapping // needed to process post requests for /design (after the submit taco button is pressed)
	public String processTaco (Taco taco, @ModelAttribute TacoOrder tacoOrder) { // tacoOrder from model
		tacoOrder.addTaco(taco);
		log.info("Processing taco: {}", taco);
		
		return "redirect:/orders/current"; // user's browser should be redirected to this relative path
	}
	private Iterable<Ingredient> filterByType (List<Ingredient> ingredients, Type type) { // helper method
			return ingredients	// returns list of all ingredients of the same type as in the parameter
					.stream()
					.filter(x -> x.getType().equals(type))
					.collect(Collectors.toList());
	}
}
