package services;

import java.util.List;

import daos.StudentDAO;
import models.CourseEnrollment;
import models.Student;

public class StudentService {

	private Student loggedInStudent;
	private StudentDAO studentDAO;

	public StudentService() {
		this.loggedInStudent = null;
		this.studentDAO = new StudentDAO();
	}
	
	public Student login(String username, String password) {
		loggedInStudent = studentDAO.findByUsernameAndPassword(username, password);
		return loggedInStudent;
	}
	
	public Student getLoggedInStudent() {
		return this.loggedInStudent;
	}
	
	public List<CourseEnrollment> getCourses() {
		if (loggedInStudent != null) {
			return loggedInStudent.getCourses();
		} else {
			return null;
		}
	}
}
