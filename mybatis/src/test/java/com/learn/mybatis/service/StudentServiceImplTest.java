package com.learn.mybatis.service;

import com.learn.mybatis.entity.StudentEntity;
import com.learn.mybatis.repository.CourseRepository;
import com.learn.mybatis.repository.CourseStudentRepository;
import com.learn.mybatis.repository.StudentRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceImplTest {

    private StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
    private CourseRepository courseRepository = Mockito.mock(CourseRepository.class);

    private CourseStudentRepository courseStudentRepository = Mockito.mock(CourseStudentRepository.class);

    @Test@DisplayName("Testing the student course save ")
    void saveAllStudentTest () {
        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository, courseRepository, courseStudentRepository);
        // mocking the repsiroy behaviour
        StudentEntity student = StudentEntity.builder()
                        .rollNo(3).address("address").name("test").build();
        Mockito.when(studentRepository.save(student)).thenReturn(student);
        assertEquals("Unsuccessful", studentService.saveAllStudent());
    }
}