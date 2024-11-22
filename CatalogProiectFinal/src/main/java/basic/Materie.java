package basic;

public class Materie {
	private String name;
    private Profesor profesor;
	public Materie() {
		
	}
	public Materie(String name, Profesor profesor) {
        this.name = name;
        this.profesor = profesor;
    }

    public String getName() {
        return name;
    }

    public Profesor getProfesor() {
        return profesor;
    }
	@Override
	public String toString() {
		return "Materie [name=" + name + ", profesor=" + profesor + "]";
	}
}
