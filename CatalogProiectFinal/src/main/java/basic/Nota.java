package basic;

import java.util.Date;

public class Nota {
	private Student student;
	private Materie subject;
	private double value;
	private Date data;

	public Nota() {
	
	}

	public Nota(Student student, Materie subject, double value, Date data) {
		this.student = student;
		this.subject = subject;
		this.value = value;
		this.data = data;
	}

	public Student getStudent() {
		return student;
	}

	public Materie getSubject() {
		return subject;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Nota [student=" + student + ", subject=" + subject + ", value=" + value + ", data=" + data + "]";
	}

}
