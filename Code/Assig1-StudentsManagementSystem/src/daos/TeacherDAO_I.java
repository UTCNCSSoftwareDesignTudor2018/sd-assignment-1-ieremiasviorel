package daos;

import java.util.List;

import entities.Teacher;

public interface TeacherDAO_I {

	public void insert(Teacher teacher);
	
	public Teacher find(Long id);
	
	public List<Teacher> findAll();
	
	public void update(Teacher teacher);
	
	public void delete(Long id);
	
	public Teacher findByUsernameAndPassword(String username, String password);
}