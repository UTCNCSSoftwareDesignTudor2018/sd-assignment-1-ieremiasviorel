package services;

import java.util.List;

import daos.CourseInformationDAO;
import models.CourseInformation;

public class CourseService {

	private CourseInformationDAO courseDAO;
	
	public CourseService() {
		this.courseDAO = new CourseInformationDAO();
	}
	
	public List<CourseInformation> getAllCourses() {
		return courseDAO.findAll();
	}
}
