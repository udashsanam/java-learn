package com.learn.mybatis.repository;

import com.learn.mybatis.entity.TeacherCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherCourseRepository extends JpaRepository<TeacherCourseEntity, Long> {
}
