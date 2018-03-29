package daos;

import java.util.List;

import entities.CourseInformation;
import entities.Student;

public interface CourseInformationDAO_I {

public void insert(CourseInformation courseInformation);
	
	public CourseInformation find(Long id);
	
	public List<CourseInformation> findAll();
	
	public void update(CourseInformation courseInformation);
	
	public void delete(Long id);
	
	public List<Student> findCourseStudents(Long courseId);
}
