package controllers;

import java.time.LocalDate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestParam;

import basic.Filters;
import basic.Note;
import basic.Student;
import repository.MaterieRepository;
import repository.StudentRepository;

@Controller
public class StudentController {

	@Autowired
	StudentRepository sRepo;

	@Autowired
	MaterieRepository matRepo;

	

	
	@GetMapping("/studentpageVersion1/{studentId}")
	public String studentPage(@PathVariable Long studentId, Model model) {
		Student student = sRepo.findById(studentId).orElse(null);
		model.addAttribute("notes", student.getNotes());
		model.addAttribute("student", student);
		model.addAttribute("subjects", matRepo.findAll());

		return "student";
	}

	
	@GetMapping("/noteStudent")
	public String noteStudent(@RequestParam Long studentId, @RequestParam(required = false) String materie,
			@RequestParam(required = false) LocalDate startDate, @RequestParam(required = false) LocalDate endDate,
			Model model) {

		Student student = sRepo.findById(studentId).orElse(null);

		List<Note> notes = student.getNotes();
		System.out.println(startDate);
		if (student != null) {
			notes = notes.stream().filter(note -> {
				boolean filterBySubject = true;
				boolean filterByDate = true;

				
				if (materie != null && !materie.trim().isEmpty()) {
					filterBySubject = note.getSubject() != null && note.getSubject().getDenumire() != null
							&& note.getSubject().getDenumire().equalsIgnoreCase(materie.trim());
				}

				
				if (startDate != null && endDate != null) {
					System.out.println(note.getDate());
					filterByDate = note.getDate() != null && !note.getDate().isBefore(startDate) && 
																									
																									
							!note.getDate().isAfter(endDate); 
				} else if (startDate != null) {
					filterByDate = note.getDate() != null && !note.getDate().isBefore(startDate); 
																									
				} else if (endDate != null) {
					filterByDate = note.getDate() != null && !note.getDate().isAfter(endDate); 
				}

				
				return filterBySubject && filterByDate;
			}).collect(Collectors.toList());
			System.out.println(notes.size());
			model.addAttribute("notes", notes);
			model.addAttribute("student", student);
			model.addAttribute("selectedSubject", materie);
			model.addAttribute("subjects", matRepo.findAll());
			model.addAttribute("stDate", startDate);
			model.addAttribute("eDate", endDate);

		}
		return "student";
	}

	
	@GetMapping("/studentpage/{studentId}")
	public String studentPageCuFiltrare(@PathVariable Long studentId,
			@RequestParam(required = false) Boolean sortByDate, @RequestParam(required = false) Boolean dateAsc,
			@RequestParam(required = false) Boolean sortByGrade, @RequestParam(required = false) Boolean gradeAsc,
			@RequestParam(required = false) String materie, @RequestParam(required = false) LocalDate startDate,
			@RequestParam(required = false) LocalDate endDate, Model model) {
		Student student = sRepo.findById(studentId).orElse(null);
		List<Note> notes = student.getNotes();
		if (sortByDate != null && sortByDate) {
			
				notes.sort(Comparator.comparing(Note::getDate)); 
			} else {
				notes.sort(Comparator.comparing(Note::getDate).reversed()); 

		}

		if (sortByGrade != null && sortByGrade) {
			
				notes.sort(Comparator.comparingDouble(Note::getGrade)); // Sortare notă ascendentă
			} else {
				notes.sort(Comparator.comparingDouble(Note::getGrade).reversed()); // Sortare notă descendentă
		}
		notes = notes.stream().filter(note -> {
			boolean filterBySubject = true;
			boolean filterByDate = true;

			if (materie != null && !materie.trim().isEmpty()) {
				filterBySubject = note.getSubject() != null && note.getSubject().getDenumire() != null
						&& note.getSubject().getDenumire().equalsIgnoreCase(materie.trim());
			}

			if (startDate != null && endDate != null) {
				System.out.println(note.getDate());
				filterByDate = note.getDate() != null && !note.getDate().isBefore(startDate) &&

						!note.getDate().isAfter(endDate);
			} else if (startDate != null) {
				filterByDate = note.getDate() != null && !note.getDate().isBefore(startDate);
			} else if (endDate != null) {
				filterByDate = note.getDate() != null && !note.getDate().isAfter(endDate);
			}

			return filterBySubject && filterByDate;
		}).collect(Collectors.toList());
		Filters studentFilter = new Filters(sortByDate, dateAsc, sortByGrade, gradeAsc, materie, startDate, endDate);
		System.out.println(studentFilter.toString());
		model.addAttribute("notes", notes);
		model.addAttribute("student", student);
		model.addAttribute("subjects", matRepo.findAll());
		model.addAttribute("filter", studentFilter);

		return "student";
	}

}
