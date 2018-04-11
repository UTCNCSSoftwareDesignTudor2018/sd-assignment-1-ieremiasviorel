package data.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends User {
	
	@Embedded
	protected StudentProfile studentProfile; 
}
