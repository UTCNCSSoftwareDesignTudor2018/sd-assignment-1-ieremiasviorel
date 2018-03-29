package services;

import java.time.LocalDate;
import java.util.List;

import daos.CourseInformationDAO;
import daos.CourseInformationDAO_I;
import entities.CourseInformation;

public class CourseService {

	private CourseInformationDAO_I courseDAO;
	
	public CourseService() {
		this.courseDAO = new CourseInformationDAO();
	}
	
	public List<CourseInformation> getAllCourses() {
		return this.courseDAO.findAll();
	}
	
	public void modifyCourseExamDate(CourseInformation course, LocalDate newExamDate) {
		course.setExamDate(newExamDate);
		this.courseDAO.update(course);
	}
	
	public void createCourse(CourseInformation course) {
		this.courseDAO.insert(course);
	}
}
