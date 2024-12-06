package ro.digitalnation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import basic.*;

@Controller
public class GradeController {

    private static final boolean Note = false;

	@Autowired
    private StudentRepository studentRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private MaterieRepository subjectRepository;

	@GetMapping("/profesorstudents")
	public String listStudents(Model model) {
		model.addAttribute("classRooms", getAvailableClassRooms());
		return "students";
	}
    
	// Vizualizare elevi dintr-o anumită clasă
    @GetMapping("/class/{clasa}/students")
    public String showStudents(@RequestParam String clasa, Model model) {
        List<Student> students = clasa != null ? studentRepository.findByClasa(clasa)
				: (List<Student>) studentRepository.findAll();
      
        model.addAttribute("students", students);
        model.addAttribute("selectedGrade", clasa);
        model.addAttribute("classRooms", getAvailableClassRooms());
        return "students";
    }
    
   
    // Vizualizare note pentru un elev și o materie
    @GetMapping("/student/{studentId}/grades")
    public String showGrades(@PathVariable Long studentId, Model model) {
        Student student = studentRepository.findById(studentId).orElse(null);
        List<Note> notes = student != null ? student.getNotes() : new ArrayList<>();
        List<Materie> subjects = (List<Materie>) subjectRepository.findAll();
        
        model.addAttribute("student", student);
        model.addAttribute("notes", notes);
        model.addAttribute("subjects", subjects);
        return "grades";
    }
    
    @GetMapping("/note/{noteId}/edit/{studentId}")
    public String editNote(@PathVariable Long noteId,@PathVariable Long studentId, Model model) {
        
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

    // Adăugare notă pentru un elev și o materie
    @PostMapping("/student/{studentId}/addGrade")
    public String addGrade(@PathVariable Long studentId, @RequestParam("subjectId") String subjectId, 
                           @RequestParam("grade") Double grade, @RequestParam("observations") String observations, 
                           @RequestParam("date") String date) {
    	
    	Long stId=Long.valueOf(studentId);
    	
        Student student = studentRepository.findById(stId).orElse(null);
        Materie subject = subjectRepository.findByDenumire(subjectId).orElse(null);
        
        if (student != null && subject != null) {
            Note note = new Note();
            note.setGrade(grade);
    		note.setDate(LocalDate.parse(date));
    		note.setObservations(observations);
    		note.setSubject(subject);

    		// Adăugăm nota la elev
    		student.getNotes().add(note);
    		studentRepository.save(student);
            noteRepository.save(note);
        }
        return "redirect:/student/" + studentId + "/grades";
    }

    // Modificare notă
    @PostMapping("/note/{noteId}/update/{studentId}")
    public String updateGrade(@PathVariable String noteId,@PathVariable Long studentId, @RequestParam("grade") Double grade, 
                              @RequestParam("observations") String observations, @RequestParam("date") String date) {
    	Long nId=Long.parseLong(noteId);
    	//Long sId=Long.parseLong(studentId);
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

    // Ștergere notă
    @GetMapping("/note/{noteId}/delete")
    public String deleteGrade(@PathVariable Long noteId,@RequestParam Long studentId,RedirectAttributes redirectAttributes) {
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
}
