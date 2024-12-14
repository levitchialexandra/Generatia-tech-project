package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommunController {

	@GetMapping("/adminpage")
	public String AdminPage() {
		return "adminpage";
	}
	@RequestMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }
	

}
