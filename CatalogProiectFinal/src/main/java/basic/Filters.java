package basic;

import java.time.LocalDate;

public class Filters {
	
	public Boolean sortByDate=false;
	public Boolean dateAsc=false;
	public Boolean sortByGrade=false;
	public Boolean gradeAsc=false;
	public String subject;
	public LocalDate startDate;
	public LocalDate endDate;
	
	public Filters(Boolean sortByDate, Boolean dateAsc, Boolean sortByGrade, Boolean gradeAsc, String subject,
			LocalDate startDate, LocalDate endDate) {
		super();
		this.sortByDate = sortByDate;
		this.dateAsc = dateAsc;
		this.sortByGrade = sortByGrade;
		this.gradeAsc = gradeAsc;
		this.subject = subject;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Filters() {
		
	}
	@Override
	public String toString() {
		return "Filters [sortByDate=" + sortByDate + ", dateAsc=" + dateAsc + ", sortByGrade=" + sortByGrade
				+ ", gradeAsc=" + gradeAsc + ", subject=" + subject + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}
}
