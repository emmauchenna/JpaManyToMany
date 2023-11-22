package com.emma.jpamanytomany.repository;

import com.emma.jpamanytomany.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
