package basic;

import java.util.ArrayList;
import java.util.List;

public class Profesor {
	private String id;
	private String name;
	private List<String> subjects;

	@Override
	public String toString() {
		return "Profesor [id=" + id + ", name=" + name + ", subjects=" + subjects + "]";
	}

	public Profesor() {

	}

	public Profesor(String id, String name) {
		this.id = id;
		this.name = name;
		this.subjects = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void addSubject(String subject) {
		subjects.add(subject);
	}
}
