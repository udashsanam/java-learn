package com.learn.mybatis.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "course_student")
public class CourseStudentEntity  extends BaseEntity{

    @ManyToOne
    private CourseEntity courseEntity;

    @ManyToOne
    private StudentEntity studentEntity;

}
