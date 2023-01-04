package com.learn.mybatis.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "teacher_course")
public class TeacherCourseEntity extends BaseEntity {

    @ManyToOne
    private TeacherEntity teacherEntity;

    @ManyToOne
    private CourseEntity courseEntity;
}
