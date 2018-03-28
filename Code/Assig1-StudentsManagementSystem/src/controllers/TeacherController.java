package controllers;

import services.CourseService;
import services.TeacherService;
import services.TeacherSessionData;
import services.TeachingService;
import views.TeacherView;

public class TeacherController {

	private TeacherService teacherService;
	private TeachingService teachingService;
	private CourseService courseService;
	
	private TeacherView teacherView;
	
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
		this.teachingService = new TeachingService();
		this.courseService = new CourseService();
		
		this.teacherView = new TeacherView();
		
		this.teacherView.updatePersonalInfoTab(TeacherSessionData.getTeacher());
		this.teacherView.updateTeachingTab(TeacherSessionData.getCourses());
	}
}
