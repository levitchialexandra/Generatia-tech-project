package ro.digitalnation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import basic.Materie;
import basic.MaterieRepository;


@Controller
public class MaterieController {
	ArrayList<Materie> materii=new ArrayList<Materie>();
	
    //@Autowired(required = true)
	MaterieRepository matRepo;
	
    @Autowired
    public MaterieController(MaterieRepository matRepo) {
        this.matRepo = matRepo;
    }
	@GetMapping("/adminmateriepage")
	public String Materii(Model model) {
		model.addAttribute("materii",matRepo.findAll());
		model.addAttribute(new Materie());
		return "adminmateriepage";
				
	}
	
	@PostMapping("materieAdd")
	public String StudentAdd(@ModelAttribute Materie materie, Model model) {
		matRepo.save(materie);
		return "redirect:adminmateriepage";
	}

}
