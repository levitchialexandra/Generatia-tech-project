package ro.digitalnation;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import basic.Materie;


@Controller
public class MaterieController {
	ArrayList<Materie> materii=new ArrayList<Materie>();
	
	@GetMapping("/materii")
	public String Materii(Model model) {
		model.addAttribute("materii",materii);
		model.addAttribute(new Materie());
		return "materie";
				
	}
	
	@PostMapping("materieAdd")
	public String StudentAdd(@ModelAttribute Materie materie, Model model) {
		model.addAllAttributes(materii);
		materii.add(materie);
		
		return "redirect:materii";
	}

}
