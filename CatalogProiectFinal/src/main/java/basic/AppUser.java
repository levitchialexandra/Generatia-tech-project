package basic;

import jakarta.persistence.*;

@Entity
public class AppUser  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String username;
	private String password;

	private String role;

	@OneToOne(mappedBy = "appUser", fetch = FetchType.LAZY)
	private Student student;

	@OneToOne(mappedBy = "appUser", fetch = FetchType.LAZY)
	private Profesor professor;

	public AppUser() {
	}

	public AppUser( String username, String password, String role, Student student, Profesor professor) {
		super();
		
		this.username = username;
		this.password = password;
		this.role = role;
		this.student = student;
		this.professor = professor;
	}
	
	public boolean checkPassword(String enteredPassword) {
        return this.password.equals(enteredPassword); 
    }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
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

	

}
