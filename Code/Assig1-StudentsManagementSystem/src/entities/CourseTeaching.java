package entities;

public class CourseTeaching extends DBRecord {

	private Teacher teacher;
	private CourseInformation course;

	public CourseTeaching() {
		super();
	}

	public CourseTeaching(Long id, Teacher teacher, CourseInformation course) {
		super(id);
		this.teacher = teacher;
		this.course = course;
	}
	
	public CourseTeaching(Teacher teacher, CourseInformation course) {
		super();
		this.teacher = teacher;
		this.course = course;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public CourseInformation getCourse() {
		return course;
	}

	public void setCourse(CourseInformation course) {
		this.course = course;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
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
		CourseTeaching other = (CourseTeaching) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CourseTeaching [teacher=" + teacher.getFirstName() + " " + teacher.getLastName() + ", course="
				+ course.getName() + "]";
	}
}
