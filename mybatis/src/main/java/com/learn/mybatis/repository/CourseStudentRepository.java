package com.learn.mybatis.repository;

import com.learn.mybatis.entity.CourseStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudentEntity, Long> {
}
