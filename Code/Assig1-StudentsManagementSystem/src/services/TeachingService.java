package services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import daos.CourseTeachingDAO;
import daos.CourseTeachingDAO_I;
import entities.CourseEnrollment;
import entities.CourseInformation;
import entities.CourseTeaching;
import entities.Student;
import entities.Teacher;

public class TeachingService {

	private CourseTeachingDAO_I courseTeachingDAO;

	public TeachingService() {
		this.courseTeachingDAO = new CourseTeachingDAO();
	}

	public List<CourseTeaching> getTeacherCourses(Teacher teacher) {
		return courseTeachingDAO.findByTeacherId(teacher.getId());
	}

	public List<CourseTeaching> getAllCourseTeachers() {
		return courseTeachingDAO.findAll();
	}

	public List<CourseTeaching> getUnEnrolledStudentCourses(Student student) {

		List<CourseTeaching> allCourses = getAllCourseTeachers();

		List<CourseEnrollment> studentEnrollments = student.getCourses();
		List<CourseInformation> studentCourses = studentEnrollments.stream().map(e -> e.getCourse())
				.collect(Collectors.toList());

		List<CourseTeaching> filteredList = new ArrayList<CourseTeaching>();
		for (CourseTeaching teaching : allCourses) {
			if (!studentCourses.contains(teaching.getCourse())) {
				filteredList.add(teaching);
			}
		}
		return filteredList;
	}
	
	public void addCourseTeaching(CourseTeaching teaching) {
		this.courseTeachingDAO.insert(teaching);
	}
}
