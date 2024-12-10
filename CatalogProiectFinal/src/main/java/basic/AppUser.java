package basic;

import java.util.Collection;

import jakarta.persistence.*;

@Entity
public class AppUser  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String username;
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToOne(mappedBy = "appUser", fetch = FetchType.LAZY)
	private Student student;

	@OneToOne(mappedBy = "appUser", fetch = FetchType.LAZY)
	private Profesor professor;

	@ElementCollection(fetch = FetchType.EAGER)
	private Collection<String> roles;

	
	public AppUser() {
	}

	public AppUser(String username, String password, Role role) {

		this.username = username;
		this.password = password;
		this.role = role;
	}

	public AppUser(String username, String password, Role role, Student student) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.student = student;
	}

	public AppUser(String username, String password, Role role, Profesor profesor) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.professor = profesor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Profesor getProfessor() {
		return professor;
	}

	public void setProfessor(Profesor professor) {
		this.professor = professor;
	}

	public enum Role {
		ADMIN, PROFESOR, STUDENT
	}

}
