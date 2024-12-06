package basic;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Student {
	@Id
	@GeneratedValue
	private Long id;
	private String nume;
	private String prenume;
	private String clasa;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Note> notes;

	public Student() {
	}

	public Student(Long id, String nume, String prenume, String clasa) {
		super();
		this.id = id;
		this.nume = nume;
		this.prenume = prenume;
		this.clasa = clasa;
	}

	public String FullName() {
		return nume + " " + prenume;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public String getClasa() {
		return clasa;
	}

	public void setClasa(String clasa) {
		this.clasa = clasa;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public void updateNota(Long notaId, Double newValoare) {
		for (Note nota : notes) {
			if (nota.getId().equals(notaId)) {
				nota.setGrade(newValoare);
				break;
			}
		}
	}

	public void updateNota(Long notaId, Note newValoare) {
		for (Note nota : notes) {
			
			if (nota.getId().equals(notaId)) {
				
				nota.setGrade(newValoare.getGrade());
				nota.setObservations(newValoare.getObservations());
				nota.setDate(newValoare.getDate());
				break;
			}
			
		}
	}
	
	public Note getNotaById(Long notaId) {
		for (Note nota : notes) {
			
			if (nota.getId().equals(notaId)) {
				
				return nota;
			}
			
		}
		return new Note();
	}

	public void stergeNota(Long notaId) {
		for (Note nota : notes) {
			if (nota.getId().equals(notaId)) {
				notes.remove(nota);
				break;
			}
		}

	}
}
