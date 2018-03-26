package controllers;

import java.beans.PropertyVetoException;

public class Test {

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
	}
}
