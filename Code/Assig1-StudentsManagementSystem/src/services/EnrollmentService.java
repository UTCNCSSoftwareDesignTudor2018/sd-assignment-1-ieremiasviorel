package services;

import java.util.List;

import daos.CourseEnrollmentDAO;
import entities.CourseEnrollment;
import entities.CourseInformation;
import entities.Student;

public class EnrollmentService {

	private CourseEnrollmentDAO enrollmentDAO;
	
	public EnrollmentService() {
		this.enrollmentDAO = new CourseEnrollmentDAO();
	}
	
	public List<CourseEnrollment> getStudentEnrollments(Student student) {
		return enrollmentDAO.findByStudentId(student.getId());
	}
	
	public void enrollStudent(Student student, CourseInformation course) {
		this.enrollmentDAO.insert(new CourseEnrollment(student, course));
	}
}
