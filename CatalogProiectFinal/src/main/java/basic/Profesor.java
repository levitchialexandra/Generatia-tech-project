package basic;

import java.util.List;

public class Profesor extends Materie {
	private String id;
	private String nume;
	private String prenume;
	private List<Materie> materii;

	public Profesor() {

	}

	public List<Materie> getSubjects() {
		return materii;
	}

	public void addSubject(Materie materie) {
		materii.add(materie);
	}

	public Profesor(String id, String nume, String prenume, List<Materie> materii) {
		super();
		this.id = id;
		this.nume = nume;
		this.prenume = prenume;
		this.materii = materii;
	}



	@Override
	public String toString() {
		return "Profesor [id=" + id + ", nume=" + nume + ", prenume=" + prenume + ", materii=" + materii + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

}
