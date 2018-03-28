package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entities.CourseInformation;
import entities.Student;
import services.EnrollmentService;
import services.StudentService;
import services.StudentSessionData;
import services.TeachingService;
import views.StudentView;

public class StudentController {

	private StudentService studentService;
	private EnrollmentService enrollmentService;
	private TeachingService teachingService;
	
	private StudentView studentView;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
		this.enrollmentService = new EnrollmentService();
		this.teachingService = new TeachingService();

		StudentSessionData.setUnEnrolledCourses(
				this.teachingService.getUnEnrolledStudentCourses(StudentSessionData.getStudent()));

		this.studentView = new StudentView();
		this.studentView.updatePersonalInfoTab(StudentSessionData.getStudent());
		this.studentView.updateEnrollmentsTab(StudentSessionData.getCourses());
		this.studentView.updateCoursesTab(StudentSessionData.getUnEnrolledCourses());

		this.studentView.addEnrollmentDetailsActionListener(new CourseListListener());
		this.studentView.addModifyStudentDataActionListener(new ModifyUserNamePasswordListener());
		this.studentView.addEnrollButtonActionListener(new EnrollButtonListener());
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

	class EnrollButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Student student = StudentSessionData.getStudent();
			CourseInformation course = StudentSessionData.getUnEnrolledCourses()
					.get(studentView.getCoursesTableSelectedRow()).getCourse();
			enrollmentService.enrollStudent(student, course);
			
			StudentSessionData.enroll(course);
			
			studentView.updateEnrollmentsTab(StudentSessionData.getCourses());
			studentView.updateTable();
		}
	}
}
