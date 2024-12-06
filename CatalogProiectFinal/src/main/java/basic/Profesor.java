package basic;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Profesor  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nume;
	private String prenume;
	//private List<Materie> materii;

	public Profesor() {

	}

	public Profesor(Long id, String nume, String prenume, List<Materie> materii) {
		super();
		this.id = id;
		this.nume = nume;
		this.prenume = prenume;
		//this.materii = materii;
	}



	@Override
	public String toString() {
		return "Profesor [id=" + id + ", nume=" + nume + ", prenume=" + prenume + "]";
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

}
