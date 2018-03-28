package entities;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {

	private List<CourseEnrollment> courses;

	public Student() {
		super();
	}

	public Student(String fName, String lName, String uName, String pass, String idNum, String email) {
		super(fName, lName, uName, pass, idNum, email);
		courses = new ArrayList<CourseEnrollment>();
	}

	public Student(String fName, String lName, String uName, String pass, String idNum, String email,
			List<CourseEnrollment> courses) {
		super(fName, lName, uName, pass, idNum, email);
		this.courses = courses;
	}

	public List<CourseEnrollment> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseEnrollment> courses) {
		this.courses = courses;
	}

	public boolean enrollCourse(CourseInformation courseToEnroll) {
		for (CourseEnrollment c : this.courses) {
			if (c.getCourse().equals(courseToEnroll)) {
				return false;
			}
		}
		CourseEnrollment newCourseEnrollment = new CourseEnrollment(this, courseToEnroll);
		this.courses.add(newCourseEnrollment);
		return true;
	}

	public boolean unenrollCourse(CourseInformation courseToRemove) {
		CourseEnrollment courseEnrollmentToRemove = null;
		for (CourseEnrollment c : this.courses) {
			if (c.getCourse().equals(courseToRemove)) {
				courseEnrollmentToRemove = c;
				break;
			}
		}
		if (courseEnrollmentToRemove != null) {
			this.courses.remove(courseEnrollmentToRemove);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + "]";
	}

}
