package data.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

//@Entity
//@Table(name = "course_info")
@Entity
@Embeddable
public class CourseInfo extends DBRecord {

	@Column(name = "name")
	protected String name;
	
	@Column(name = "code")
	protected String code;
	
	@Column(name = "description")
	protected String description;
	
	public CourseInfo() {
		super();
	}

	public CourseInfo(String name, String code, String description) {
		super();
		this.name = name;
		this.code = code;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
