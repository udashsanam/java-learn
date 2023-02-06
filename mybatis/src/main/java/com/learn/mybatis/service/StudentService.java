package com.learn.mybatis.service;

import com.learn.mybatis.entity.StudentEntity;

public interface StudentService {
    String saveAllStudent();

    String saveAllTeacher();

    StudentEntity findStudentById(Long id);

    String saveStudent(StudentEntity studentEntity);
}
