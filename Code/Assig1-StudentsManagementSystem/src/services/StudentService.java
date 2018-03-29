package services;

import daos.StudentDAO;
import daos.StudentDAO_I;
import entities.Student;

public class StudentService {

	private StudentDAO_I studentDAO;
	private EnrollmentService enrollmentService;

	public StudentService() {
		this.studentDAO = new StudentDAO();
		this.enrollmentService = new EnrollmentService();
	}

	public boolean login(String username, String password) {
		Student loggedInStudent = studentDAO.findByUsernameAndPassword(username, password);
		if (loggedInStudent != null) {
			StudentSessionData.setStudent(loggedInStudent);
			StudentSessionData.setCourses(enrollmentService.getStudentEnrollments(loggedInStudent));
			return true;
		} else {
			return false;
		}
	}

	public void modifyStudentData(String username, String password) {
		StudentSessionData.getStudent().setUserName(username);
		StudentSessionData.getStudent().setPassword(password);

		studentDAO.update(StudentSessionData.getStudent());
	}
}
