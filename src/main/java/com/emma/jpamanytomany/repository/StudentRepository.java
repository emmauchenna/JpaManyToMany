package com.emma.jpamanytomany.repository;

import com.emma.jpamanytomany.domain.Course;
import com.emma.jpamanytomany.domain.Student;
import com.emma.jpamanytomany.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}



