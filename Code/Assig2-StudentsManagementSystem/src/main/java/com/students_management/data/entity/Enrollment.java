package com.students_management.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "enrollments")
public class Enrollment extends BaseEntity {

	private static final long serialVersionUID = -8609108076637770386L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	protected Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	protected Course course;
	
	@Column(name = "grade")
	protected Float grade;
}
