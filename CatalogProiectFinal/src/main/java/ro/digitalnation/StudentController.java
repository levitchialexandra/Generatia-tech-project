package ro.digitalnation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import basic.Student;
import basic.StudentRepository;

@Controller
public class StudentController {

	ArrayList<Student> Studenti = new ArrayList<Student>();
	@Autowired
	StudentRepository sRepo;

	@GetMapping("/adminstudentpage")
	public String Studenti(Model model) {
		model.addAttribute("studenti", sRepo.findAll());
		model.addAttribute(new Student());
		return "adminstudentpage";
	}

	@PostMapping("studentAdd")
	public String studentAdd(@ModelAttribute Student student, Model model) {
		//model.addAllAttributes(Studenti);
		System.out.println(student);
		sRepo.save(student);
		return "redirect:adminstudentpage";
	}

}
