package basic;

public class Materie {
	private String id;
	private String denumire;

	@Override
	public String toString() {
		return "Materie [id=" + id + ", denumire=" + denumire + "]";
	}

	public Materie() {

	}

	public Materie(String id, String denumire) {
		super();
		this.id = id;
		this.denumire = denumire;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

}
