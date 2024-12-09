package ro.digitalnation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import basic.Filters;
import basic.MaterieRepository;
import basic.Note;
import basic.Student;
import basic.StudentRepository;

@Controller
public class StudentController {

	@Autowired
	StudentRepository sRepo;

	@Autowired
	MaterieRepository matRepo;

	@GetMapping("/adminstudentpage")
	public String Studenti(Model model) {
		model.addAttribute("studenti", sRepo.findAll());
		model.addAttribute(new Student());
		return "adminstudentpage";
	}

	@PostMapping("studentAdd")
	public String studentAdd(@ModelAttribute Student student, Model model) {
		sRepo.save(student);
		return "redirect:adminstudentpage";
	}

	@GetMapping("/studentpageVersion1/{studentId}")
	public String studentPage(@PathVariable Long studentId, Model model) {
		Student student = sRepo.findById(studentId).orElse(null);
		model.addAttribute("notes", student.getNotes());
		model.addAttribute("student", student);
		model.addAttribute("subjects", matRepo.findAll());

		return "student";
	}

	// Metodă pentru a obține toate notele unui student
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

				// Filtrare după materie (dacă materie nu este null sau gol)
				if (materie != null && !materie.trim().isEmpty()) {
					filterBySubject = note.getSubject() != null && note.getSubject().getDenumire() != null
							&& note.getSubject().getDenumire().equalsIgnoreCase(materie.trim());
				}

				// Filtrare după interval de date (dacă startDate și endDate nu sunt null)
				if (startDate != null && endDate != null) {
					System.out.println(note.getDate());
					filterByDate = note.getDate() != null && !note.getDate().isBefore(startDate) && // nota nu este
																									// înainte de
																									// startDate
							!note.getDate().isAfter(endDate); // nota nu este după endDate
				} else if (startDate != null) {
					filterByDate = note.getDate() != null && !note.getDate().isBefore(startDate); // nota nu este
																									// înainte de
																									// startDate
				} else if (endDate != null) {
					filterByDate = note.getDate() != null && !note.getDate().isAfter(endDate); // nota nu este după
																								// endDate
				}

				// Returnează nota doar dacă îndeplinește ambele condiții
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

	@GetMapping("/student/{id}/notes1")
	public String getNotesForStudent(@PathVariable Long id, @RequestParam(required = false) Boolean sortByDate,
			@RequestParam(required = false) Boolean dateAsc, @RequestParam(required = false) Boolean sortByGrade,
			@RequestParam(required = false) Boolean gradeAsc,

			Model model) {

		Student student = sRepo.findById(id).orElse(null);
		List<Note> notes = student.getNotes();

		if (sortByDate != null && sortByDate) {
			if (dateAsc != null && dateAsc) {
				notes.sort(Comparator.comparing(Note::getDate)); // Sortare dată ascendentă
			} else
				notes.sort(Comparator.comparing(Note::getDate).reversed()); // Sortare dată descendentă

		}

		if (sortByGrade != null && sortByGrade) {
			if (gradeAsc != null && gradeAsc) {
				notes.sort(Comparator.comparingDouble(Note::getGrade)); // Sortare notă ascendentă
			} else
				notes.sort(Comparator.comparingDouble(Note::getGrade).reversed()); // Sortare notă descendentă
		}

		model.addAttribute("notes", notes);
		model.addAttribute("sortByDate", sortByDate);
		model.addAttribute("dateAsc", dateAsc);
		model.addAttribute("sortByGrade", sortByGrade);
		model.addAttribute("gradeAsc", gradeAsc);
		model.addAttribute("notes", student.getNotes());
		model.addAttribute("student", student);
		model.addAttribute("subjects", matRepo.findAll());

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
