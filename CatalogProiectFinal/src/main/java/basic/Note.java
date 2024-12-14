package basic;


import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double grade;
    private LocalDate date;
    private String observations;

    @ManyToOne
    @JoinColumn(name = "subjectid")
    private Materie subject;  

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public void setDateFromString(String dateString) {
        this.date = LocalDate.parse(dateString);  
    }
    
    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Materie getSubject() {
        return subject;
    }

    public void setSubject(Materie subject) {
        this.subject = subject;
    }
}
