package controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import basic.Materie;
import basic.Note;

import basic.Profesor;
import basic.Student;
import repository.MaterieRepository;
import repository.ProfesorRepository;
import repository.StudentRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfesorController {
	ArrayList<Profesor> profesori = new ArrayList<Profesor>();

	@Autowired
	ProfesorRepository pRepo;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private MaterieRepository materieRepository;


	
	@GetMapping("/profesor")
	public String getStudents(@RequestParam String clasa, Model model) {

		List<Student> students = clasa != null ? studentRepository.findByClasa(clasa)
				: (List<Student>) studentRepository.findAll();

		model.addAttribute("students", students);
		model.addAttribute("grades", getAvailableGrades());
		model.addAttribute("selectedGrade", clasa);
		model.addAttribute("subjects", (List<Materie>) materieRepository.findAll());

		return "profesor";
	}

	@PostMapping("profesoraddNote")
	public String addNote(@RequestParam Long studentId, @RequestParam String subjectId, @RequestParam double grade,
			@RequestParam String observations, @RequestParam String date) {

		Student student = studentRepository.findById(studentId).orElseThrow();
		Materie subject = materieRepository.findByDenumire(subjectId).orElseThrow();
		
		Note note = new Note();
		note.setGrade(grade);
		note.setDate(LocalDate.parse(date));
		note.setObservations(observations);
		note.setSubject(subject);

		
		student.getNotes().add(note);
		studentRepository.save(student);

		return "redirect:profesor";
	}

	private List<String> getAvailableGrades() {
		return List.of("9A", "9B", "9C", "10A", "10B", "10C", "11A", "11B", "11C", "12A", "12B", "12C");
	}

	@GetMapping("/profesorpage")
	public String listStudents(Model model) {
		model.addAttribute("grades", getAvailableGrades());
		return "profesorpage";
	}

	

	
	
   }