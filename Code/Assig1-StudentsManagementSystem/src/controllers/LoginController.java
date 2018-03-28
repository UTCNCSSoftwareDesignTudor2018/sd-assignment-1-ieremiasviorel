package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import services.StudentService;
import services.TeacherService;
import views.LoginView;

public class LoginController {

	private LoginView loginView;

	public LoginController() {
		this.loginView = new LoginView();
		this.loginView.addStudentLoginActionListener(new StudentLoginListener());
		this.loginView.addTeacherLoginActionListener(new TeacherLoginListener());
	}

	class StudentLoginListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			StudentService studentService = new StudentService();
			String[] loginData = loginView.getLoginData();
			if (studentService.login(loginData[0], loginData[1])) {
				new StudentController(studentService);
			} else {
				loginView.displayErrorMessage();
			}
		}
	}

	class TeacherLoginListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			TeacherService teacherService = new TeacherService();
			String[] loginData = loginView.getLoginData();
			if (teacherService.login(loginData[0], loginData[1])) {
				new TeacherController(teacherService);
			} else {
				loginView.displayErrorMessage();
			}
		}
	}
}
