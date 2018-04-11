package data.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

//@Entity
//@Table(name = "teacher_profile")
@Embeddable
public class TeacherProfile {

	@ElementCollection
	protected List<CourseInfo> courses;
}
