package controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomErrorController {
	// @RequestMapping(value = "/error", method = RequestMethod.GET)
	@GetMapping("/errorpage")
	public String handleError(@RequestParam(value = "message", required = false) String message,
			@RequestParam(value = "referer", required = false) String referer, Model model) {
		System.out.println("Mesaj de eroare: " + message);
		System.out.println("Referer: " + referer);
		// Set a default error message if none is provided
		if (message == null) {
			message = "An unexpected error occurred.";
		}

		// Add the error message and referer URL to the model
		model.addAttribute("errorMessage", message);
		model.addAttribute("referer", referer != null ? referer : "/"); // Default to root if no referer is provided

		return "error";
	}
}
