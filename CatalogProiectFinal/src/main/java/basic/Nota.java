package basic;

public class Nota {
	 private Student student;
	    private Materie subject;
	    private double value;
	public Nota() {
		// TODO Auto-generated constructor stub
	}
	public Nota(Student student, Materie subject, double value) {
        this.student = student;
        this.subject = subject;
        this.value = value;
    }

    public Student getStudent() {
        return student;
    }

    public Materie getSubject() {
        return subject;
    }

    public double getValue() {
        return value;
    }
	@Override
	public String toString() {
		return "Nota [student=" + student + ", subject=" + subject + ", value=" + value + "]";
	}

}
