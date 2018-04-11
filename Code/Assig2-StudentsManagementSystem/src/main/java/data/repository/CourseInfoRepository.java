package data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import data.entity.CourseInfo;

public interface CourseInfoRepository extends JpaRepository<CourseInfo, Long> {

	@Override
	public List<CourseInfo> findAll();
}
