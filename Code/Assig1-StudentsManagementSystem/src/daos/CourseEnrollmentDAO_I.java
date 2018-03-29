package daos;

import java.util.List;
import entities.CourseEnrollment;

public interface CourseEnrollmentDAO_I {

	public void insert(CourseEnrollment courseEnrollment);

	public CourseEnrollment find(Long id);

	public List<CourseEnrollment> findAll();

	public void update(CourseEnrollment courseEnrollment);

	public void delete(Long id);

	public List<CourseEnrollment> findByCourseId(Long courseId);

	public List<CourseEnrollment> findByStudentId(Long studentId);
}