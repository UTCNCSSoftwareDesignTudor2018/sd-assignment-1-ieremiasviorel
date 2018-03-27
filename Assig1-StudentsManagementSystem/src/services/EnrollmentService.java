package services;

import java.util.List;

import daos.CourseEnrollmentDAO;
import models.CourseEnrollment;
import models.Student;

public class EnrollmentService {

	private CourseEnrollmentDAO enrollmentDAO;
	
	public EnrollmentService() {
		this.enrollmentDAO = new CourseEnrollmentDAO();
	}
	
	public List<CourseEnrollment> getStudentEnrollments(Student student) {
		return enrollmentDAO.findStudentCourses(student.getId());
	}
}
