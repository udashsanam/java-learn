package com.learn.mybatis.mapper;

import com.learn.mybatis.entity.StudentEntity;
import com.learn.mybatis.pojo.StudentCoursePojo;
import com.learn.mybatis.pojo.StudentTeacherPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<StudentEntity> findAllStudent();

    StudentEntity findById(@Param("id") Long id);

    void insertStudent(@Param("name") String name,@Param("rollNo") int rollNo,@Param("address")String address);

    StudentCoursePojo findAllCourseByStudentId(@Param("studentId") int studentId);

    StudentTeacherPojo findAllStudentAndCourseByCourseId(@Param("courseId") int courseId, @Param("courseId2") int courseId2);

}
