package services;

import daos.StudentDAO;
import models.Student;

public class StudentService {

	private Student loggedInStudent;
	private StudentDAO studentDAO;
	private EnrollmentService enrollmentService;

	public StudentService() {
		this.loggedInStudent = null;
		this.studentDAO = new StudentDAO();
		this.enrollmentService = new EnrollmentService();
	}
	
	public Student getLoggedInStudent() {
		return loggedInStudent;
	}

	public boolean login(String username, String password) {
		this.loggedInStudent = studentDAO.findByUsernameAndPassword(username, password);
		if (this.loggedInStudent != null) {
			this.loggedInStudent.setCourses(enrollmentService.getStudentEnrollments(this.loggedInStudent));
			return true;
		} else {
			return false;
		}
	}
	
	public void modifyStudentData(String username, String password) {
		this.loggedInStudent.setUserName(username);
		this.loggedInStudent.setPassword(password);
		
		studentDAO.update(this.loggedInStudent);
	}
}
