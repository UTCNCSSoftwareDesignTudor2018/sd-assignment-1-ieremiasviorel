package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entities.CourseInformation;
import services.CourseService;
import services.TeacherService;
import services.TeacherSessionData;
import views.TeacherView;

public class TeacherController {

	private TeacherService teacherService;
	private CourseService courseService;

	private TeacherView teacherView;

	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
		this.courseService = new CourseService();

		this.teacherView = new TeacherView();

		this.teacherView.updatePersonalInfoTab(TeacherSessionData.getTeacher());
		this.teacherView.updateTeachingTab(TeacherSessionData.getCourses());

		this.teacherView.addCourseDetailsActionListener(new CourseListListener());
		this.teacherView.addModifyTeacherDataActionListener(new ModifyUserNamePasswordListener());
		this.teacherView.addModifyCourseActionListener(new ModifyCourseActionListener());
	}

	class CourseListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			teacherView.updateCourseDetails();
		}
	}

	class ModifyUserNamePasswordListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			List<String> modifiedData = teacherView.getUserNameAndPassword();
			teacherService.modifyTeacherData(modifiedData.get(0), modifiedData.get(1));
		}
	}

	class ModifyCourseActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String courseName = teacherView.getSelectedCourse();
			CourseInformation courseToModify = TeacherSessionData.getCourses().stream()
					.filter(c -> c.getCourse().getName().equals(courseName))
					.collect(Collectors.toList()).get(0).getCourse();
			
			courseService.modifyCourseExamDate(courseToModify,
					LocalDate.parse(teacherView.getNewExamDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		}
	}
}
