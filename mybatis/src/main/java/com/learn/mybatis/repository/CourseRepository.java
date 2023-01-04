package com.learn.mybatis.repository;

import com.learn.mybatis.annotation.CustomAnnotation;
import com.learn.mybatis.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@CustomAnnotation
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
