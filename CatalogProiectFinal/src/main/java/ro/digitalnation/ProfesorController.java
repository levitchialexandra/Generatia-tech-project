package ro.digitalnation;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import basic.Profesor;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfesorController {
	ArrayList<Profesor> profesori = new ArrayList<Profesor>();

	@GetMapping("/profesori")
	public String Profesori(Model model) {
		model.addAttribute("profesori", profesori);
		//model.addAttribute("materii", )
		model.addAttribute(new Profesor());
		return "profesor";
	}

	@PostMapping("profesorAdd")
	public String ProfesorAdd(@ModelAttribute Profesor profesor, Model model) {
		model.addAllAttributes(profesori);
		profesori.add(profesor);

		return "redirect:profesori";
	}

}
