package basic;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Materie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long subject_id;
	private String denumire;

	@Override
	public String toString() {
		return "Materie [id=" + subject_id + ", denumire=" + denumire + "]";
	}

	public Materie() {

	}

	public Materie(Long id, String denumire) {
		super();
		this.subject_id = id;
		this.denumire = denumire;
	}

	public long getId() {
		return subject_id;
	}

	public void setId(Long id) {
		this.subject_id = id;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

}
