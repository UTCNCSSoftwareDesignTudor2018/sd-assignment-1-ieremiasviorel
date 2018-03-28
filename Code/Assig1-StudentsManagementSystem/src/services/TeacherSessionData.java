package services;

import java.util.List;

import entities.CourseTeaching;
import entities.Teacher;

public class TeacherSessionData {

	private static Teacher teacher;
	
	public static Teacher getTeacher() {
		return teacher;
	}
	
	public static void setTeacher(Teacher teach) {
		teacher = teach;
	}
	
	public static List<CourseTeaching> getCourses() {
		return teacher.getCourses();
	}
	
	public static void setCourses(List<CourseTeaching> courses) {
		teacher.setCourses(courses);
	}
}
