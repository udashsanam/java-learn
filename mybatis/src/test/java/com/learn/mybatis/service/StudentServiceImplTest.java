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

    @Captor
    private ArgumentCaptor<StudentEntity> studentEntityArgumentCaptor;



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

    @Test
    @DisplayName("Testing whether the student save service works or not")
    void saveStudentTest() {
        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository, courseRepository,courseStudentRepository, teacherRepository, teacherCourseRepository, studentMapper);
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(1l);
        studentEntity.setRollNo(1);
        studentEntity.setName("sanam");
        studentEntity.setAddress("address");

        // mockin the method which do return nothing but takes the input
        Mockito.doAnswer((param) -> {
            System.out.println("Student roll no  = " + param.getArgument(1));
            assertTrue("sanam".equals(param.getArgument(0)));
            return null;
        }).when(studentMapper).insertStudent(Mockito.anyString(), Mockito.anyInt(), Mockito.anyString());

        assertEquals("Successfully saved", studentService.saveStudent(studentEntity));
    }

    @Test
    void savingStudentValidationTest() {
        StudentServiceImpl studentService = new StudentServiceImpl(studentRepository, courseRepository,courseStudentRepository, teacherRepository, teacherCourseRepository, studentMapper);
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(1l);
        studentEntity.setRollNo(1);
        studentEntity.setName("sanam");
        studentEntity.setAddress("address");

        // mockin the method which do return nothing but takes the input
        Mockito.doAnswer((param) -> {
            System.out.println("Student roll no  = " + param.getArgument(1));
            System.out.println("Student nam " + param.getArgument(2));
            assertTrue("sanam".equals(param.getArgument(0)));
            return null;
        }).when(studentMapper).insertStudent(Mockito.anyString(), Mockito.anyInt(), Mockito.anyString());

        assertEquals("Successfully saved", studentService.saveStudent(studentEntity));
//        studentService.saveStudent(studentEntity);

        // getting the  value afte invoking the method
//        Mockito.verify(studentService, Mockito.times(1)).saveStudent(studentEntityArgumentCaptor.capture());
//        assertEquals("sanam", studentEntityArgumentCaptor.getValue().getName());

        // initializing the argument capture inside method
        ArgumentCaptor<String> addressArgumentCapture = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> intArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

        Mockito.verify(studentMapper, Mockito.times(1)).insertStudent(stringArgumentCaptor.capture(),intArgumentCaptor.capture(),addressArgumentCapture.capture());

        assertEquals("sanam", stringArgumentCaptor.getValue());
        assertEquals(1, intArgumentCaptor.getValue());
        assertEquals("address", addressArgumentCapture.getValue());


    }
}