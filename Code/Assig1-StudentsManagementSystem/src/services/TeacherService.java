package services;

import daos.TeacherDAO;
import daos.TeacherDAO_I;
import entities.Teacher;

public class TeacherService {
	
	private TeacherDAO_I teacherDAO;
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
