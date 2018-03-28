package services;

import java.util.List;
import java.util.stream.Collectors;

import entities.CourseEnrollment;
import entities.CourseInformation;
import entities.CourseTeaching;
import entities.Student;

public class StudentSessionData {

	private static Student student;

	private static List<CourseTeaching> unEnrolledCourses;

	public static Student getStudent() {
		return student;
	}

	public static void setStudent(Student stud) {
		student = stud;
	}

	public static List<CourseEnrollment> getCourses() {
		return student.getCourses();
	}

	public static void setCourses(List<CourseEnrollment> studentCourses) {
		student.setCourses(studentCourses);
	}

	public static List<CourseTeaching> getUnEnrolledCourses() {
		return unEnrolledCourses;
	}

	public static void setUnEnrolledCourses(List<CourseTeaching> unEnrolledCourses) {
		StudentSessionData.unEnrolledCourses = unEnrolledCourses;
	}

	public static void enroll(CourseInformation course) {

		CourseEnrollment enrollment = new CourseEnrollment(student, course);
		student.getCourses().add(enrollment);

		unEnrolledCourses = unEnrolledCourses.stream().filter(e -> !e.getCourse().equals(course))
				.collect(Collectors.toList());
	}
}
