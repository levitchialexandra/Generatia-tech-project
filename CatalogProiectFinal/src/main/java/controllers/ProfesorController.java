package controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import basic.*;
import jakarta.servlet.http.HttpSession;
import repository.MaterieRepository;
import repository.NoteRepository;
import repository.StudentRepository;

@Controller
public class ProfesorController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	private MaterieRepository subjectRepository;
	@Autowired
	private HttpSession session;

	@GetMapping("/profesorpage")
	public String listStudents(Model model) {
		if(!CheckIfUserIsLoggedIn()) return "redirect:/login";
		model.addAttribute("classRooms", getAvailableClassRooms());
		model.addAttribute("students", studentRepository.findAll());
		model.addAttribute("profesorName",GetUserName());
		return "profesorpage";
	}

	
	@GetMapping("/class/{clasa}/students")
	public String showStudents(@RequestParam String clasa, Model model) {
		if(!CheckIfUserIsLoggedIn()) return "redirect:/login";
		List<Student> students = (clasa != null && clasa != "") ? studentRepository.findByClasa(clasa)
				: (List<Student>) studentRepository.findAll();

		model.addAttribute("students", students);
		model.addAttribute("selectedGrade", clasa);
		model.addAttribute("classRooms", getAvailableClassRooms());
		return "students";
	}

	@GetMapping("/class/{clasa}/studentsGr")
	public String showStudentsVar(@PathVariable String clasa, Model model) {
		if(!CheckIfUserIsLoggedIn()) return "redirect:/login";
		List<Student> students = (clasa != null && clasa != "") ? studentRepository.findByClasa(clasa)
				: (List<Student>) studentRepository.findAll();

		model.addAttribute("students", students);
		model.addAttribute("selectedGrade", clasa);
		model.addAttribute("classRooms", getAvailableClassRooms());
		return "profesorpage";
	}

	@GetMapping("/students/search")
	public String searchStudents(@RequestParam String query,
			@RequestParam String clasa, Model model) {
		if(!CheckIfUserIsLoggedIn()) return "redirect:/login";
		List<Student> students;
		if (query != null && !query.isEmpty() && clasa != null && !clasa.isEmpty()) {
			students = studentRepository.findByNumeContainingIgnoreCaseOrPrenumeContainingIgnoreCaseAndClasa(query,
					query, clasa);
		}

		else if (query != null && !query.isEmpty()) {
			students = studentRepository.findByNumeContainingIgnoreCaseOrPrenumeContainingIgnoreCase(query, query);
		}

		else if (clasa != null && !clasa.isEmpty()) {
			students = studentRepository.findByClasa(clasa);
		} else {

			students = (List<Student>) studentRepository.findAll();
		}

		model.addAttribute("students", students);
		model.addAttribute("query", query);
		model.addAttribute("clasa", clasa);
		model.addAttribute("selectedClasa", clasa);
		model.addAttribute("classRooms", getAvailableClassRooms());

		return "profesorpage";
	}

	
	
	@GetMapping("/student/{studentId}/grades")
	public String showGrades(@PathVariable Long studentId, Model model) {
		if(!CheckIfUserIsLoggedIn()) return "redirect:/login";
		Student student = studentRepository.findById(studentId).orElse(null);
		List<Note> notes = student != null ? student.getNotes() : new ArrayList<>();
		List<Materie> subjects = (List<Materie>) subjectRepository.findAll();

		model.addAttribute("student", student);
		model.addAttribute("notes", notes);
		model.addAttribute("subjects", subjects);
		return "grades";
	}


	@GetMapping("/note/{noteId}/edit/{studentId}")
	public String editNote(@PathVariable Long noteId, @PathVariable Long studentId, Model model) {
		if(!CheckIfUserIsLoggedIn()) return "redirect:/login";
		Student student = studentRepository.findById(studentId).orElse(null);
		List<Note> notes = student != null ? student.getNotes() : new ArrayList<>();
		List<Materie> subjects = (List<Materie>) subjectRepository.findAll();

		model.addAttribute("selectedNoteId", noteId);
		model.addAttribute("student", student);
		model.addAttribute("notes", notes);
		model.addAttribute("subjects", subjects);
		model.addAttribute("showEditForm", true);
		model.addAttribute("note", student.getNotaById(noteId));
		return "grades";
	}

	
	@PostMapping("/student/{studentId}/addGrade")
	public String addGrade(@PathVariable Long studentId, @RequestParam String subjectId,
			@RequestParam Double grade, @RequestParam String observations,
			@RequestParam String date) {
		if(!CheckIfUserIsLoggedIn()) return "redirect:/login";
		Long stId = Long.valueOf(studentId);

		Student student = studentRepository.findById(stId).orElse(null);
		Materie subject = subjectRepository.findByDenumire(subjectId).orElse(null);

		if (student != null && subject != null) {
			Note note = new Note();
			note.setGrade(grade);
			note.setDate(LocalDate.parse(date));
			note.setObservations(observations);
			note.setSubject(subject);

			
			student.getNotes().add(note);
			studentRepository.save(student);
			noteRepository.save(note);
		}
		return "redirect:/student/" + studentId + "/grades";
	}

	
	@PutMapping("/note/{noteId}/update/{studentId}")
	public String updateGrade(@PathVariable String noteId, @PathVariable Long studentId,
			@RequestParam Double grade, @RequestParam String observations,
			@RequestParam String date) {
		if(!CheckIfUserIsLoggedIn()) return "redirect:/login";
		Long nId = Long.parseLong(noteId);
		
		System.out.println(observations);
		Student student = studentRepository.findById(studentId).orElse(null);
		Note note = noteRepository.findById(nId).orElse(null);
		if (student != null && note != null) {

			note.setGrade(grade);
			note.setObservations(observations);
			note.setDate(LocalDate.parse(date));

			student.updateNota(nId, note);

			studentRepository.save(student);
			noteRepository.save(note);
			student = studentRepository.findById(studentId).orElse(null);

		}
		return "redirect:/student/" + studentId + "/grades";
	}

	
	@DeleteMapping("/note/{noteId}/delete")
	public String deleteGrade(@PathVariable Long noteId, @RequestParam Long studentId,
			RedirectAttributes redirectAttributes) {
		if(!CheckIfUserIsLoggedIn()) return "redirect:/login";
		Note note = noteRepository.findById(noteId).orElse(null);
		Student student = studentRepository.findById(studentId).orElse(null);
		System.out.println(noteId);
		if (student != null && note != null) {

			student.getNotes().remove(note);

			studentRepository.save(student);
			noteRepository.delete(note);

			redirectAttributes.addFlashAttribute("message", "Nota a fost ștearsă cu succes!");
		}
		return "redirect:/student/" + studentId + "/grades";
	}

	private List<String> getAvailableClassRooms() {
		return List.of("9A", "9B", "9C", "10A", "10B", "10C", "11A", "11B", "11C", "12A", "12B", "12C");
	}
	
	public Boolean CheckIfUserIsLoggedIn() {
		AppUser sessionUser = (AppUser) session.getAttribute("sessionUser");
		
		return sessionUser!=null;
	}
	
	private String GetUserName() {
		AppUser sessionUser = (AppUser) session.getAttribute("sessionUser");
		return sessionUser.getProfessor().getFullName();
	}
}
