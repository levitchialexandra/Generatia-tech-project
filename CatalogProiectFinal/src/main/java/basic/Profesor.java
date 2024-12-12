package basic;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Profesor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nume;
	private String prenume;

	@OneToOne
	@JoinColumn(name = "appUserId")
	private AppUser appUser;

	public Profesor() {

	}

	public Profesor(Long id, String nume, String prenume, AppUser appUser) {
		super();
		this.id = id;
		this.nume = nume;
		this.prenume = prenume;
		this.appUser = appUser;

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

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public String getFullName() {
		return nume + " " + prenume;
	}

}
