package com.students_management.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.students_management.data.entity.Course;
import com.students_management.data.repository.CourseJpaRepository;

@Service
public class CourseService {

	@Autowired
	public CourseJpaRepository courseJpaRepository;
	
	public void createCourse(Course course) {
		courseJpaRepository.save(course);
	}
}
