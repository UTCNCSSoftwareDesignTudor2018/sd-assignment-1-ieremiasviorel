package controllers;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import services.StudentService;
import views.StudentView;

public class StudentController {

	private StudentService studentService;
	private StudentView studentView;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
		this.studentView = new StudentView(this.studentService);
		this.studentView.initializeView();
		this.studentView.addEnrollmentDetailsActionListener(new CourseListListener());
	}
	
	class CourseListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			studentView.updateEnrollmentDetails();
		}
	}
}
