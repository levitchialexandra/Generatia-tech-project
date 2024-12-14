package controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import basic.AppUser;
import basic.Materie;
import basic.Profesor;
import basic.Student;
import repository.AppUserRepository;
import repository.MaterieRepository;
import repository.ProfesorRepository;
import repository.StudentRepository;

@Controller
public class AdminController {

	@Autowired
	ProfesorRepository pRepo;

	@Autowired
	StudentRepository sRepo;

	@Autowired
	MaterieRepository matRepo;

	@Autowired
	AppUserRepository userRep;

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
	public String studentAdd(@ModelAttribute Student student, Model model, RedirectAttributes redirectAttributes)
			throws UnsupportedEncodingException {
		try {
			sRepo.save(student);
			model.addAttribute("students", sRepo.findAll());
			model.addAttribute("grades", getAvailableGrades());
			// add the login for student
			//sRepo.findByNumeContainingIgnoreCaseOrPrenumeContainingIgnoreCase(student.getNume(), student.getPrenume());
			//AppUser user = new AppUser(student.FullName(), "password123", "STUDENT", student, null);
			//userRep.save(user);
			redirectAttributes.addFlashAttribute("message", "Student adaugat cu succes!");
			return "redirect:/admin/studentpage";
		} catch (DataIntegrityViolationException e) {
			String message = URLEncoder.encode("Materia nu poate fi stearsa deoarece are note asociate.", "UTF-8");
			String refPage = URLEncoder.encode("/admin/materiepage", "UTF-8");
			return "redirect:/errorpage?message=" + message + "&referer=" + refPage;
		} catch (Exception e) {

			e.printStackTrace();
			return "redirect:/error?message=There was an unexpected error while trying to delete the subject.";
		}
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

		var fUser = userRep.findByStudentId(studentEdit.getId()).orElse(null);
		System.out.println(fUser);
		if (fUser != null) {
			studentEdit.setAppUser(fUser);
		}
		
		if (studentEdit.getId() != null && sRepo.existsById(studentEdit.getId())) {
			var student=sRepo.findById(studentEdit.getId()).orElse(null);
			if(student!=null) {
				studentEdit.setNotes(student.getNotes());
			}
			sRepo.save(studentEdit);
		}
		model.addAttribute("student", new Student());
		model.addAttribute("students", sRepo.findAll());
		model.addAttribute("grades", getAvailableGrades());
		redirectAttributes.addFlashAttribute("message", "Student modificat cu succes!");
		return "redirect:/admin/studentpage";
	}

	@GetMapping("/admin/student/delete/{id}")
	public String deleteStudent(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes)
			throws UnsupportedEncodingException {
		try {
			model.addAttribute("student", new Student());
			sRepo.deleteById(id);
			model.addAttribute("students", sRepo.findAll());
			model.addAttribute("grades", getAvailableGrades());
			redirectAttributes.addFlashAttribute("message", "Studentul a fost șters cu succes!");
			return "redirect:/admin/studentpage";
		} catch (DataIntegrityViolationException e) {
			String message = URLEncoder.encode("Studentul nu poate fi stears deoarece are note asociate.", "UTF-8");
			String refPage = URLEncoder.encode("/admin/studentpage", "UTF-8");
			return "redirect:/errorpage?message=" + message + "&referer=" + refPage;
		} catch (Exception e) {

			e.printStackTrace();
			return "redirect:/error?message=There was an unexpected error while trying to delete the subject.";
		}
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
		Materie mat = matRepo.findByDenumire(id).orElse(null);
		;
		model.addAttribute("materii", matRepo.findAll());
		model.addAttribute(new Materie());
		model.addAttribute("materieEdit", mat);
		model.addAttribute("showEditForm", true);
		return "adminmateriepage";
	}

	@PostMapping("/admin/materie/edit")
	public String editMaterieForm(@ModelAttribute Materie materieEdit, Model model,
			RedirectAttributes redirectAttributes) {
		Materie mat = matRepo.findByDenumire(materieEdit.getDenumire()).orElse(null);
		System.out.println(materieEdit.getId());
		if (materieEdit != null && mat != null) {
			materieEdit.setId(mat.getId());

			materieEdit.setDenumire(materieEdit.getDenumire());
			matRepo.save(materieEdit);
		}
		model.addAttribute("materii", matRepo.findAll());
		model.addAttribute(new Materie());
		redirectAttributes.addFlashAttribute("message", "Materie modificata cu succes!");
		return "redirect:/admin/materiepage";
	}

	@DeleteMapping("/admin/materie/delete/{id}")
	public String deleteMaterie(@PathVariable String id, Model model, RedirectAttributes redirectAttributes)
			throws UnsupportedEncodingException {

		try {
			Materie mat = (Materie) matRepo.findByDenumire(id).orElse(null);

			matRepo.delete(mat);
			model.addAttribute("materii", matRepo.findAll());
			model.addAttribute(new Materie());
			redirectAttributes.addFlashAttribute("message", "Materie stearsa cu succes!");
			return "redirect:/admin/materiepage";
		} catch (DataIntegrityViolationException e) {
			String message = URLEncoder.encode("Materia nu poate fi stearsa deoarece are note asociate.", "UTF-8");
			String refPage = URLEncoder.encode("/admin/materiepage", "UTF-8");
			return "redirect:/errorpage?message=" + message + "&referer=" + refPage;
		} catch (Exception e) {

			e.printStackTrace();
			return "redirect:/error?message=There was an unexpected error while trying to delete the subject.";
		}
	}

	private List<String> getAvailableGrades() {
		return List.of("9A", "9B", "9C", "10A", "10B", "10C", "11A", "11B", "11C", "12A", "12B", "12C");
	}
}
