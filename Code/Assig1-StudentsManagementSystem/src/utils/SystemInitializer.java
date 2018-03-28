package utils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import daos.CourseEnrollmentDAO;
import daos.CourseInformationDAO;
import daos.CourseTeachingDAO;
import daos.StudentDAO;
import daos.TeacherDAO;
import entities.CourseEnrollment;
import entities.CourseInformation;
import entities.CourseTeaching;
import entities.Student;
import entities.Teacher;

public class SystemInitializer {

	private StudentDAO sDAO = new StudentDAO();
	private TeacherDAO tDAO = new TeacherDAO();
	private CourseInformationDAO cDAO = new CourseInformationDAO();
	private CourseEnrollmentDAO eDAO = new CourseEnrollmentDAO();
	private CourseTeachingDAO pDAO = new CourseTeachingDAO();

	List<Student> students;
	List<Teacher> teachers;
	List<CourseInformation> courses;

	private String[] firstNames = new String[] { "Hashim", "Julian", "Cullen", "Chancellor", "Jared", "Rahim", "Lester",
			"Geoffrey", "Merrill", "Castor", "Mark", "Travis", "Aurora", "Yeo", "Lani", "Rhea", "Ava", "Illiana",
			"Fallon", "Yael", "Olympia", "Fiona" };
	private String[] lastNames = new String[] { "Cain", "Mathis", "Hoover", "Frederick", "Rich", "Barber", "Tucker",
			"Hull", "Gillespie", "Diaz", "Patel", "Sanford", "Barton", "Foley", "Gilliam", "Collins", "Rice", "Byrd",
			"Mcmahon", "Cooley" };

	private String[] courseNames = new String[] { "Logic Programming", "Functional Programming", "Software Engineering",
			"Artificial Intelligence", "Graphics Systems", "Microprocessor Design", "Software Design",
			"Intelligent Systems", "Formal Languages", "Computer Systems", "Image Processing",
			"Management & Communication" };

	private String[] courseCodes = new String[] { "LP", "FP", "SE", "IAI", "GPS", "DMP", "SD", "IS", "FLT", "SCS", "IP",
			"MC" };

	public void init() {
		for (int i = 0; i < 15; i++) {
			sDAO.insert(new Student(firstNames[i], lastNames[i], (firstNames[i].charAt(0) + lastNames[i]).toLowerCase(),
					UUID.randomUUID().toString().substring(0, 7), UUID.randomUUID().toString(),
					(firstNames[i].charAt(0) + lastNames[i]).toLowerCase() + "@email.com"));
		}

		for (int i = 15; i < 20; i++) {
			tDAO.insert(new Teacher(firstNames[i], lastNames[i], (firstNames[i].charAt(0) + lastNames[i]).toLowerCase(),
					UUID.randomUUID().toString().substring(0, 7), UUID.randomUUID().toString(),
					(firstNames[i].charAt(0) + lastNames[i]).toLowerCase() + "@email.com"));
		}

		for (int i = 0; i < 12; i++) {
			LocalDate startDate = LocalDate.now();
			LocalDate endDate = LocalDate.now();
			if (i < 6) {
				startDate.minusMonths(7);
				endDate.minusMonths(2);
				
			} else {
				startDate.minusMonths(1);
				endDate.plusMonths(4);
			} 
			cDAO.insert(new CourseInformation(courseNames[i], courseCodes[i],
					"A good description gives the interested students an insight into the subject of the course",
					startDate, endDate, endDate));
		}
		
		students = sDAO.findAll();
		teachers = tDAO.findAll();
		courses = cDAO.findAll();
		
		System.out.println(students.size() + " ||| " + teachers.size() + " ||| " + courses.size());
		
		for (int i = 0; i < 16; i++) {
			eDAO.insert(new CourseEnrollment(students.get(i), courses.get((17 * i + 10) % 12), Float.valueOf((17 * i + 10) % 11)));
			eDAO.insert(new CourseEnrollment(students.get(i), courses.get((17 * i + 11) % 12), Float.valueOf((17 * i + 11) % 11)));
			eDAO.insert(new CourseEnrollment(students.get(i), courses.get((17 * i + 12) % 12), Float.valueOf((17 * i + 12) % 11)));
			eDAO.insert(new CourseEnrollment(students.get(i), courses.get((17 * i + 13) % 12), Float.valueOf((17 * i + 13) % 11)));
		}
		
		for (int i = 0; i < 6; i++) {
			pDAO.insert(new CourseTeaching(teachers.get(i), courses.get((17 * i + 10) % 12)));
			pDAO.insert(new CourseTeaching(teachers.get(i), courses.get((17 * i + 11) % 12)));
			pDAO.insert(new CourseTeaching(teachers.get(i), courses.get((17 * i + 12) % 12)));
			pDAO.insert(new CourseTeaching(teachers.get(i), courses.get((17 * i + 13) % 12)));
		}
	}
}