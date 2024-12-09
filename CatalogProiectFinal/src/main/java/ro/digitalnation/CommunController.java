package ro.digitalnation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunController {

	@GetMapping("/adminpage")
	public String AdminPage() {
		return "adminpage";
	}

	

}
