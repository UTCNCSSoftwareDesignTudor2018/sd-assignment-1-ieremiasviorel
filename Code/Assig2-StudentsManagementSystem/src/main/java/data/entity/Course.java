package data.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course extends DBRecord {

	@ManyToOne
	protected CourseInfo courseInfo;
	
	@ElementCollection(fetch = FetchType.LAZY)
	protected List<User> teachers;
	
	@ElementCollection(fetch = FetchType.LAZY)
	protected List<User> students;
	
	@Column(name = "start_date")
	protected Date startDate;
	
	@Column(name = "end_date")
	protected Date endDate;
	
	@Column(name = "exam_date")
	protected Date examDate;
}
