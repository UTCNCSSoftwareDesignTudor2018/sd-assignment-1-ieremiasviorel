package services;

import java.util.List;

import daos.CourseEnrollmentDAO;
import daos.CourseEnrollmentDAO_I;
import entities.CourseEnrollment;
import entities.CourseInformation;
import entities.Student;

public class EnrollmentService {

	private CourseEnrollmentDAO_I enrollmentDAO;
	
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
