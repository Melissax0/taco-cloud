package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//signifies that this class is a Controller, meaning it will be scanned as a bean in the Spring container
@Controller	
public class HomeController {
	@GetMapping("/")	// handles requests for the root path
	public String home() {
		return "home";	// returns the view name, which will be implemented using Thymeleaf template engine
	}
}
