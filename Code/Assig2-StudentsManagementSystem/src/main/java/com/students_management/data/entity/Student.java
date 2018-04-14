package com.students_management.data.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends User {

	private static final long serialVersionUID = 8114821921433187492L;

	@ManyToOne
	@JoinColumn(name = "group_id")
	protected Group group;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
	protected List<Enrollment> courses;
}
