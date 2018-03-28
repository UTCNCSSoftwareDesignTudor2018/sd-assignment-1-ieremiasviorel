package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Teacher extends User {

	private List<CourseTeaching> courses;

	public Teacher() {
		super();
	}

	public Teacher(String fName, String lName, String uName, String pass, String idNum, String email) {
		super(fName, lName, uName, pass, idNum, email);
		this.courses = new ArrayList<CourseTeaching>();
	}

	public Teacher(String fName, String lName, String uName, String pass, String idNum, String email,
			List<CourseTeaching> courses) {
		super(fName, lName, uName, pass, idNum, email);
		this.courses = courses;
	}

	public List<CourseTeaching> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseTeaching> courses) {
		this.courses = courses;
	}

	public boolean addCourse(CourseInformation courseToAdd) {
		for (CourseTeaching c : this.courses) {
			if (c.getCourse().equals(courseToAdd)) {
				return false;
			}
		}
		CourseTeaching newCourseTeaching = new CourseTeaching(this, courseToAdd);
		this.courses.add(newCourseTeaching);
		return true;
	}

	public boolean removeCourse(CourseInformation courseToRemove) {
		CourseTeaching courseTeachingToRemove = null;
		for (CourseTeaching c : this.courses) {
			if (c.getCourse().equals(courseToRemove)) {
				courseTeachingToRemove = c;
				break;
			}
		}
		if (courseTeachingToRemove != null) {
			this.courses.remove(courseTeachingToRemove);
			return true;
		} else {
			return false;
		}
	}

	public boolean setExamDate(CourseInformation course, LocalDate examDate) {
		for (CourseTeaching c : this.courses) {
			if (c.getCourse().equals(course)) {
				c.getCourse().setExamDate(examDate);
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Teacher [courses=" + courses + ", firstName=" + firstName + ", lastName=" + lastName + ", id=" + id
				+ "]";
	}
}
