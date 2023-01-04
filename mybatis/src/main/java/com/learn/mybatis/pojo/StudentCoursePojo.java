package com.learn.mybatis.pojo;


import com.learn.mybatis.entity.CourseEntity;
import com.learn.mybatis.entity.StudentEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentCoursePojo {

    private int studentId;

    private String studentName;


    List<CoursePojo> coursePojos;

}
