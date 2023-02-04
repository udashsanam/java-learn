package com.learn.mybatis.service;

import com.learn.mybatis.entity.*;
import com.learn.mybatis.mapper.StudentMapper;
import com.learn.mybatis.repository.*;
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

    private TeacherRepository teacherRepository = Mockito.mock(TeacherRepository.class);

    private TeacherCourseRepository teacherCourseRepository = Mockito.mock(TeacherCourseRepository.class);

    private StudentMapper studentMapper = Mockito.mock(StudentMapper.class);


    @Test@DisplayName("Testing the student course save ")
    void saveAllStudentTest () {
        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository, courseRepository, courseStudentRepository, teacherRepository,teacherCourseRepository, studentMapper);
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

    @Test
    @DisplayName("Testing save teacher method ")
    void saveTeacherServiceTest() {
        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository, courseRepository,courseStudentRepository, teacherRepository, teacherCourseRepository, studentMapper);

        Mockito.when(teacherRepository.save(ArgumentMatchers.any(TeacherEntity.class))).thenReturn(null);

        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setId(1l);
        teacherEntity.setName("hello");
        teacherEntity.setAddress("address");
        TeacherEntity teacherEntity1 = new TeacherEntity();
        teacherEntity.setId(2l);
        teacherEntity.setName("hello");
        teacherEntity.setAddress("address");
        Mockito.when(teacherRepository.findAll()).thenReturn(Arrays.asList(teacherEntity,teacherEntity1));

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseName("course");
        courseEntity.setId(1l);
        courseEntity.setCourseCode("code");
        CourseEntity courseEntity1 = new CourseEntity();
        courseEntity1.setCourseName("course");
        courseEntity1.setId(1l);
        courseEntity1.setCourseCode("code");
        Mockito.when(courseRepository.findAll()).thenReturn(Arrays.asList(courseEntity, courseEntity1));

        Mockito.when(teacherCourseRepository.save(ArgumentMatchers.any(TeacherCourseEntity.class))).thenReturn(null);



        assertEquals("Successful", studentService.saveAllTeacher());

        Mockito.verify(teacherRepository, Mockito.times(10)).save(ArgumentMatchers.any(TeacherEntity.class));
        Mockito.verify(courseRepository, Mockito.times(1)).findAll();
        Mockito.verify(teacherRepository, Mockito.times(1)).findAll();
        Mockito.verify(teacherCourseRepository, Mockito.times(2)).save(ArgumentMatchers.any(TeacherCourseEntity.class));

    }

    @Test
    void findStudentByIdTest() {
        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository, courseRepository,courseStudentRepository, teacherRepository, teacherCourseRepository, studentMapper);
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(1l);
        studentEntity.setName("sanam");

        Mockito.when(studentMapper.findById(1l)).thenReturn(studentEntity);

        assertEquals(studentEntity,studentService.findStudentById(1l));
    }
}