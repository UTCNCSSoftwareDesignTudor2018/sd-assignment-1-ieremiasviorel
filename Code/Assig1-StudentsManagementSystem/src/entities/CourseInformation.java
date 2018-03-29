package entities;

import java.time.LocalDate;

public class CourseInformation extends DBRecord {

	private String name;
	private String code;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate examDate;

	public CourseInformation() {
		super();
	}

	public CourseInformation(String name, String code, String description, LocalDate startDate,
			LocalDate endDate, LocalDate examDate) {
		super();
		this.name = name;
		this.code = code;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.examDate = examDate;
	}

	public CourseInformation(String name, String code, String description, LocalDate startDate,
			LocalDate endDate) {
		super();
		this.name = name;
		this.code = code;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.examDate = null;
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getExamDate() {
		return examDate;
	}

	public void setExamDate(LocalDate examLocalDate) {
		this.examDate = examLocalDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		CourseInformation other = (CourseInformation) obj;

		if (code.equals(other.code)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "CourseInformation [name=" + name + ", code=" + code + "]";
	}
}
