package com.learn.mybatis.repository;

import com.learn.mybatis.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
