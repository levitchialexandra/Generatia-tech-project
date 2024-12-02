package basic;

public class Student {
	private String id;
	private String nume;
	private String prenume;
	private String clasa;
	private String nrMatricol;
	private String cnp;
	
	public String getNrMatricol() {
		return nrMatricol;
	}

	public void setNrMatricol(String nrMatricol) {
		this.nrMatricol = nrMatricol;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public Student() {

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

	public String getClasa() {
		return clasa;
	}

	public void setClasa(String clasa) {
		this.clasa = clasa;
	}

	

	public Student(String id, String nume, String prenume, String clasa, String nrMatricol, String cnp) {
		super();
		this.id = id;
		this.nume = nume;
		this.prenume = prenume;
		this.clasa = clasa;
		this.nrMatricol = nrMatricol;
		this.cnp = cnp;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", nume=" + nume + ", prenume=" + prenume + ", clasa=" + clasa + "]";
	}

	

}
