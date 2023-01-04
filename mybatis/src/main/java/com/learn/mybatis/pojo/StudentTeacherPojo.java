package com.learn.mybatis.pojo;

import com.learn.mybatis.entity.CourseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentTeacherPojo {

    private String courseName;

    private String courseCode;

    List<TeacherPojo> teacherPojos;

    List<StudentPojo> studentPojos;


}
