package ro.digitalnation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import basic.Materie;
import basic.MaterieRepository;
import basic.Profesor;
import basic.ProfesorRepository;
import basic.Student;
import basic.StudentRepository;

@Controller
public class AdminController {

	@Autowired
	ProfesorRepository pRepo;
	@Autowired
	StudentRepository sRepo;
	@Autowired
	MaterieRepository matRepo;

	@GetMapping("/admin/profesorpage")
	public String Profesori(Model model) {
		model.addAttribute("profesori", pRepo.findAll());
		model.addAttribute("profesor", new Profesor());
		return "adminprofesorpage";
	}

	@PostMapping("admin/profesor/add")
	public String ProfesorAdd(@ModelAttribute Profesor profesor, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("profesori", (List<Profesor>) pRepo.findAll());
		pRepo.save(profesor);
		redirectAttributes.addFlashAttribute("message", "Profesor adaugat cu succes!");
		return "redirect:/admin/profesorpage";
	}

	@GetMapping("/admin/profesor/showEdit/{id}")
	public String showEditProfesorForm(@PathVariable Long id, Model model) {
		Optional<Profesor> prof = pRepo.findById(id);
		model.addAttribute("profesor", new Student());
		model.addAttribute("profesorEdit", prof);
		model.addAttribute("profesori", pRepo.findAll());
		model.addAttribute("showEditForm", true);

		return "adminprofesorpage";
	}

	@PostMapping("/admin/profesor/edit")
	public String editProfesorForm(@ModelAttribute Profesor profesorEdit, Model model,
			RedirectAttributes redirectAttributes) {

		if (profesorEdit.getId() != null && pRepo.existsById(profesorEdit.getId())) {
			pRepo.save(profesorEdit);
		}
		model.addAttribute("profesor", new Profesor());
		model.addAttribute("profesori", pRepo.findAll());

		redirectAttributes.addFlashAttribute("message", "Profesor modificat cu succes!");
		return "redirect:/admin/profesorpage";
	}

	@GetMapping("/admin/profesor/delete/{id}")
	public String deleteProfesor(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		pRepo.deleteById(id);
		model.addAttribute("profesor", new Profesor());
		model.addAttribute("profesori", pRepo.findAll());

		redirectAttributes.addFlashAttribute("message", "Profesor șters cu succes!");
		return "redirect:/admin/profesorpage";
	}
	
	
	@GetMapping("/admin/studentpage")
	public String Studenti(Model model) {
		model.addAttribute("students", sRepo.findAll());
		model.addAttribute("student", new Student());
		model.addAttribute("grades", getAvailableGrades());
		return "adminstudentpage";
	}

	@PostMapping("/admin/student/add")
	public String studentAdd(@ModelAttribute Student student, Model model, RedirectAttributes redirectAttributes) {
		sRepo.save(student);
		model.addAttribute("students", sRepo.findAll());
		model.addAttribute("grades", getAvailableGrades());
		redirectAttributes.addFlashAttribute("message", "Student adaugat cu succes!");
		return "redirect:/admin/studentpage";
	}

	@GetMapping("/admin/student/showEdit/{id}")
	public String showEditStudentForm(@PathVariable Long id, Model model) {
		Student student = sRepo.getStudentById(id);
		model.addAttribute("student", new Student());
		model.addAttribute("studentEdit", student);
		model.addAttribute("students", sRepo.findAll());
		model.addAttribute("showEditForm", true);
		model.addAttribute("grades", getAvailableGrades());
		return "adminstudentpage";
	}

	@PostMapping("/admin/student/edit")
	public String editStudentForm(@ModelAttribute Student studentEdit, Model model,
			RedirectAttributes redirectAttributes) {
		System.out.println(studentEdit.getId());
		if (studentEdit.getId() != null && sRepo.existsById(studentEdit.getId())) {
			sRepo.save(studentEdit);
		}
		model.addAttribute("student", new Student());
		model.addAttribute("students", sRepo.findAll());
		model.addAttribute("grades", getAvailableGrades());
		redirectAttributes.addFlashAttribute("message", "Student modificat cu succes!");
		return "redirect:/admin/studentpage";
	}

	@GetMapping("/admin/student/delete/{id}")
	public String deleteStudent(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {

		model.addAttribute("student", new Student());
		sRepo.deleteById(id);
		model.addAttribute("students", sRepo.findAll());
		model.addAttribute("grades", getAvailableGrades());
		redirectAttributes.addFlashAttribute("message", "Studentul a fost șters cu succes!");
		return "redirect:/admin/studentpage";
	}

	
	
	@GetMapping("/admin/materiepage")
	public String Materii(Model model) {
		model.addAttribute("materii", matRepo.findAll());
		model.addAttribute(new Materie());
		return "adminmateriepage";

	}

	@PostMapping("/admin/materie/add")
	public String materieAdd(@ModelAttribute Materie materie, Model model, RedirectAttributes redirectAttributes) {
		matRepo.save(materie);
		model.addAttribute("materii", matRepo.findAll());
		model.addAttribute(new Materie());
		redirectAttributes.addFlashAttribute("message", "Materie adaugata cu succes!");
		return "redirect:/admin/materiepage";
	}

	@GetMapping("/admin/materie/showEdit/{id}")
	public String showEditMaterieForm(@PathVariable String id, Model model) {
		Materie mat = matRepo.findByDenumire(id).orElse(null);;
		model.addAttribute("materii", matRepo.findAll());
		model.addAttribute(new Materie());
		model.addAttribute("materieEdit", mat);		
		model.addAttribute("showEditForm", true);
		return "adminstudentpage";
	}

	@PostMapping("/admin/materie/edit")
	public String editMaterieForm(@ModelAttribute Materie materieEdit, Model model,
			RedirectAttributes redirectAttributes) {
		
		if (materieEdit != null && matRepo.existsById(materieEdit.getId())) {
			matRepo.save(materieEdit);
		}
		model.addAttribute("materii", matRepo.findAll());
		model.addAttribute(new Materie());
		redirectAttributes.addFlashAttribute("message", "Materie modificata cu succes!");
		return "redirect:/admin/materiepage";
	}

	@GetMapping("/admin/materie/delete/{id}")
	public String deleteMaterie(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
		Materie mat = (Materie) matRepo.findByDenumire(id).orElse(null);;
		
		matRepo.delete(mat);
		model.addAttribute("materii", matRepo.findAll());
		model.addAttribute(new Materie());
		redirectAttributes.addFlashAttribute("message", "Materie stearsa cu succes!");
		return "redirect:/admin/materiepage";
	}
	private List<String> getAvailableGrades() {
		return List.of("9A", "9B", "9C", "10A", "10B", "10C", "11A", "11B", "11C", "12A", "12B", "12C");
	}
}
