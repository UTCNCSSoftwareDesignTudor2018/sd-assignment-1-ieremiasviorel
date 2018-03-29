package daos;

import java.util.List;

import entities.CourseTeaching;

public interface CourseTeachingDAO_I {

	public void insert(CourseTeaching courseTeaching);

	public CourseTeaching find(Long id);

	public List<CourseTeaching> findAll();

	public void update(CourseTeaching courseTeaching);

	public void delete(Long id);

	public List<CourseTeaching> findByCourseId(Long courseId);

	public List<CourseTeaching> findByTeacherId(Long studentId);
}
