package ro.digitalnation;

import java.util.ArrayList;
import basic.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MVCController {
ArrayList<Student> students=new ArrayList<Student>();
Integer id=0;
//@GetMapping("student")
//public String Student(@RequestParam String name, Model model) {
	//model.addAttribute("nume",name);
	//students.add(new Student((id++).toString(), name));
   // return "student";
//}

	@GetMapping("/students")
	public String Studenti(Model model) {
		model.addAttribute("students",students);
		model.addAttribute(new Student());
		return "student";
				
	}
	
	/*
	 * @PostMapping("studentAdd") public String StudentAdd(@ModelAttribute Student
	 * student, Model model) { model.addAllAttributes(students);
	 * students.add(student );
	 * 
	 * return "redirect:students"; }
	 */
	
	
}
