package basic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Materie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long subjectId;
	 @Column(unique = true)
	private String denumire;

	@Override
	public String toString() {
		return "Materie [id=" + subjectId + ", denumire=" + denumire + "]";
	}

	public Materie() {

	}

	public Materie(Long id, String denumire) {
		super();
		this.subjectId = id;
		this.denumire = denumire;
	}

	public long getId() {
		return subjectId;
	}

	public void setId(Long id) {
		this.subjectId = id;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

}
