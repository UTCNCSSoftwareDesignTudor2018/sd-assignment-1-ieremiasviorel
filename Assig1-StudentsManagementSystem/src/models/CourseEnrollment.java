package models;

public class CourseEnrollment extends DBRecord {

	private Student student;
	private CourseInformation course;
	private Float grade;

	public CourseEnrollment() {
		super();
	}

	public CourseEnrollment(Student student, CourseInformation course) {
		super();
		this.student = student;
		this.course = course;
		this.grade = null;
	}

	public CourseEnrollment(Student student, CourseInformation course, Float grade) {
		super();
		this.student = student;
		this.course = course;
		this.grade = grade;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public CourseInformation getCourse() {
		return course;
	}

	public void setCourse(CourseInformation course) {
		this.course = course;
	}

	public Float getGrade() {
		return grade;
	}

	public void setGrade(Float grade) {
		this.grade = grade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseEnrollment other = (CourseEnrollment) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CourseEnrollment [course=" + this.course.getName() + ", grade=" + this.grade + "]";
	}
}
