package tacos;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest (HomeController.class) // Web test for HomeController
public class HomeControllerTest {
	@Autowired
	private MockMvc mockMvc; // injects MockMvc
	
	@Test
	public void testHomePage() throws Exception {
		mockMvc.perform(get("/"))						// Performs GET /
			.andExpect(status().isOk())					// Expects HTTP 200
			.andExpect(view().name("home"))				// Expects home view
			.andExpect(content().string(				// Expects "Welcome to ..." 
					containsString("Welcome to ... Taco Cloud!")));
	}
	
}
