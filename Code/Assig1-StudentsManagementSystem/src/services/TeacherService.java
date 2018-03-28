package services;

import daos.TeacherDAO;
import entities.Teacher;

public class TeacherService {
	
	private TeacherDAO teacherDAO;
	private TeachingService teachingService;
	
	public TeacherService() {
		this.teacherDAO = new TeacherDAO();
		this.teachingService = new TeachingService();
	}
	
	public boolean login(String username, String password) {
		Teacher loggedInTeacher = teacherDAO.findByUsernameAndPassword(username, password);
		if (loggedInTeacher != null) {
			TeacherSessionData.setTeacher(loggedInTeacher);
			TeacherSessionData.setCourses(teachingService.getTeacherCourses(loggedInTeacher));
			return true;
		} else {
			return false;
		}
	}
	
	public void modifyTeacherData(String username, String password) {
		TeacherSessionData.getTeacher().setUserName(username);
		TeacherSessionData.getTeacher().setPassword(password);

		teacherDAO.update(TeacherSessionData.getTeacher());
	}
}
