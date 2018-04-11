package data.entity;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

//@Entity
//@Table(name = "student_profile")
@Embeddable
public class StudentProfile {
	
	@ElementCollection
	protected Map<CourseInfo, Float> grades;
	
	@ManyToOne
	protected Group group;	
}
