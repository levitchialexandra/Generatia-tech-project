package basic;

import java.util.*;

public class Catalog {


	private List<Student> students;
    private List<Profesor> professors;
    private List<Materie> subjects;
    private List<Nota> grades;

    public Catalog() {
        students = new ArrayList<>();
        professors = new ArrayList<>();
        subjects = new ArrayList<>();
        grades = new ArrayList<>();
    }

    // Adaugă un student
    public void addStudent(Student student) {
        students.add(student);
    }

    // Adaugă un profesor
    public void addProfesor(Profesor professor) {
        professors.add(professor);
    }

    // Adaugă o materie
    public void addSubject(Materie subject) {
        subjects.add(subject);
    }

    // Adaugă o notă
    public void addGrade(Nota grade) {
        grades.add(grade);
    }

    // Afișează notele unui student la toate materiile
    public void displayStudentGrades(String studentId) {
        System.out.println("Note pentru studentul cu ID-ul: " + studentId);
        for (Nota grade : grades) {
            if (grade.getStudent().getId().equals(studentId)) {
                System.out.println(grade);
            }
        }
    }

    // Afișează media unui student
    public void displayStudentAverage(String studentId) {
        double sum = 0;
        int count = 0;
        for (Nota grade : grades) {
            if (grade.getStudent().getId().equals(studentId)) {
                sum += grade.getValue();
                count++;
            }
        }
        if (count > 0) {
            System.out.println("Media studentului cu ID-ul " + studentId + " este: " + (sum / count));
        } else {
            System.out.println("Studentul nu are note.");
        }
    }

    // Afișează toate materiile predate de un profesor
    public void displayProfessorSubjects(String professorId) {
        System.out.println("Materii predate de profesorul cu ID-ul: " + professorId);
        for (Materie subject : subjects) {
            if (subject.getProfesor().getId().equals(professorId)) {
                System.out.println(subject.getName());
            }
        }
    }
}
