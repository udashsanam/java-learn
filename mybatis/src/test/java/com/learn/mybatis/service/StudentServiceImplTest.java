package com.learn.mybatis.service;

import com.learn.mybatis.entity.CourseEntity;
import com.learn.mybatis.entity.CourseStudentEntity;
import com.learn.mybatis.entity.StudentEntity;
import com.learn.mybatis.repository.CourseRepository;
import com.learn.mybatis.repository.CourseStudentRepository;
import com.learn.mybatis.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceImplTest {

    private StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
    private CourseRepository courseRepository = Mockito.mock(CourseRepository.class);

    private CourseStudentRepository courseStudentRepository = Mockito.mock(CourseStudentRepository.class);


    @Test@DisplayName("Testing the student course save ")
    void saveAllStudentTest () {
        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository, courseRepository, courseStudentRepository);
        // mocking the repsiroy behaviour
        StudentEntity student = new StudentEntity("sanam", 1, "address");
        student.setId(1l);

        Mockito.when(studentRepository.save(ArgumentMatchers.any(StudentEntity.class))).thenReturn(student);
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(1l);
        courseEntity.setCourseCode("code");
        courseEntity.setCourseName("course");

        CourseEntity courseEntity1 = new CourseEntity();
        courseEntity.setId(1l);
        courseEntity.setCourseCode("code");
        courseEntity.setCourseName("course");

        Mockito.when(courseRepository.save(ArgumentMatchers.any(CourseEntity.class))).thenReturn(courseEntity);

        Mockito.when(courseStudentRepository.save(ArgumentMatchers.any(CourseStudentEntity.class))).thenReturn(null);

        Mockito.when(studentRepository.findAll()).thenReturn(Arrays.asList(student, student));

        Mockito.when(courseRepository.findAll()).thenReturn(Arrays.asList(courseEntity1, courseEntity));

        Mockito.when(courseStudentRepository.save(ArgumentMatchers.any(CourseStudentEntity.class))).thenReturn(null);



        assertEquals("Successful", studentService.saveAllStudent());
        // testing how many times the mock method is called

        Mockito.verify(studentRepository, Mockito.times(10)).save(ArgumentMatchers.any(StudentEntity.class));
        Mockito.verify(courseRepository, Mockito.times(10)).save(ArgumentMatchers.any(CourseEntity.class));
        Mockito.verify(courseStudentRepository,Mockito.times(2)).save(ArgumentMatchers.any(CourseStudentEntity.class));



    }
}