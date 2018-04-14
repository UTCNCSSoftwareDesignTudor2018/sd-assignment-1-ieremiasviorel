package com.students_management.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.students_management.data.entity.Course;

public interface CourseJpaRepository extends JpaRepository<Course, Long> {

}
