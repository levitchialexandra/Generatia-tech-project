package ro.digitalnation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CommunController {

	@GetMapping("/adminpage")
	public String AdminPage() {
		return "adminpage";
	}

	

}
