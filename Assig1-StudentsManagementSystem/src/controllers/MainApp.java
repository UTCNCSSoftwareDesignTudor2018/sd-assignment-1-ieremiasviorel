package controllers;

import java.beans.PropertyVetoException;
import java.util.List;

import daos.CourseEnrollmentDAO;
import daos.CourseTeachingDAO;
import models.CourseEnrollment;
import models.CourseInformation;
import models.Student;
import models.Teacher;

public class MainApp {

	public static void main(String[] args) throws PropertyVetoException {

//		StudentDAO sDAO = new StudentDAO();
//		TeacherDAO tDAO = new TeacherDAO();
//		CourseInformationDAO cDAO = new CourseInformationDAO();
//
//		Student s = sDAO.findByUsernameAndPassword("jdoe", ".");
//		Teacher t = tDAO.findByUsernameAndPassword("bmcallister", ".");
//		List<Student> studentsEnrolled = cDAO.findCourseStudents(Long.valueOf(4));
//		List<Teacher> teachers = cDAO.findCourseTeachers(Long.valueOf(4));
//
//		if (s != null) {
//			for (CourseEnrollment c : s.getCourses()) {
//				System.out.println(c.toString());
//			}
//		}
//
//		if (t != null) {
//			for (CourseTeaching c : t.getCourses()) {
//				System.out.println(c.toString());
//			}
//		}
//
//		System.out.println(studentsEnrolled.toString());
//		System.out.println(teachers.toString());
		
		new LoginController();
//		CourseInformationDAO courseDAO = new CourseInformationDAO();
//		List<Student> courseStudents = courseDAO.findCourseStudents(Long.valueOf(2));
//		List<Teacher> courseTeachers = courseDAO.findCourseTeachers(Long.valueOf(2));
//		for (Student s : courseStudents) {
//			System.out.println(s.toString());
//		}
//		for (Teacher t : courseTeachers) {
//			System.out.println(t.toString());
//		}
		CourseEnrollmentDAO dao = new CourseEnrollmentDAO();
		CourseTeachingDAO dao2 = new CourseTeachingDAO();
		List<CourseEnrollment> courses = dao.findStudentCourses(Long.valueOf(1));
		List<Student> students = dao.findCourseStudents(Long.valueOf(4));
		List<Teacher> teachers = dao2.findCourseTeachers(Long.valueOf(4));
		List<CourseInformation> courses2 = dao2.findTeacherCourses(Long.valueOf(2));
		for (CourseEnrollment c : courses) {
			System.out.println(c.toString());
		}
		for (Student s : students) {
			System.out.println(s.toString());
		}
		for (CourseInformation c : courses2) {
			System.out.println(c.toString());
		}
		for (Teacher t : teachers) {
			System.out.println(t.toString());
		}
	}
}
