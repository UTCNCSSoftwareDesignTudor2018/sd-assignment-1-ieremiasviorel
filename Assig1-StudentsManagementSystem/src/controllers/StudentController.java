package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import services.CourseService;
import services.StudentService;
import views.StudentView;

public class StudentController {

	private StudentService studentService;
	private CourseService courseService;
	private StudentView studentView;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
		this.courseService = new CourseService();
		this.studentView = new StudentView(this.studentService, this.courseService);
		this.studentView.initializeView();
		this.studentView.addEnrollmentDetailsActionListener(new CourseListListener());
		this.studentView.addModifyStudentDataActionListener(new ModifyUserNamePasswordListener());
	}
	
	class CourseListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			studentView.updateEnrollmentDetails();
		}
	}
	
	class ModifyUserNamePasswordListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			List<String> modifiedData = studentView.getUserNameAndPassword();
			studentService.modifyStudentData(modifiedData.get(0), modifiedData.get(1));
		}
	}
}
