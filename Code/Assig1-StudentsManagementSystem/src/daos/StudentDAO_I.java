package daos;

import java.util.List;

import entities.Student;

public interface StudentDAO_I {

	public void insert(Student student);
	
	public Student find(Long id);
	
	public List<Student> findAll();
	
	public void update(Student student);
	
	public void delete(Long id);
	
	public Student findByUsernameAndPassword(String username, String password);
}
