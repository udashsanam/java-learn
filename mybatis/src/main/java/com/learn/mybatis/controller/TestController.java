package com.learn.mybatis.controller;

import com.learn.mybatis.entity.*;
import com.learn.mybatis.mapper.StudentMapper;
import com.learn.mybatis.pojo.StudentCoursePojo;
import com.learn.mybatis.pojo.StudentTeacherPojo;
import com.learn.mybatis.repository.*;
import com.learn.mybatis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {


    private CourseRepository courseRepository;

    private StudentRepository studentRepository;

    private CourseStudentRepository courseStudentRepository;
    private final TeacherRepository teacherRepository;

    private final TeacherCourseRepository teacherCourseRepository;

    private StudentMapper studentMapper;

    private StudentService studentService;

    @Autowired
    public TestController(CourseRepository courseRepository,
                          StudentRepository  studentRepository,
                          CourseStudentRepository courseStudentRepository,
                          TeacherRepository teacherRepository,
                          TeacherCourseRepository teacherCourseRepository,
                          StudentMapper studentMapper){

        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.courseStudentRepository = courseStudentRepository;
        this.teacherRepository = teacherRepository;
        this.teacherCourseRepository = teacherCourseRepository;
        this.studentMapper = studentMapper;
    }

    @PostMapping("/saveStudentCourse")
    public String saveAllStudent(){
        return studentService.saveAllStudent();

    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(){
    return studentService.saveAllTeacher();
    }

    @GetMapping("/findAllStudent")
    public List<StudentEntity> findAllStudent(){
        List<StudentEntity> studentEntities = studentMapper.findAllStudent();
        return studentEntities;
    }

    @GetMapping("/findById/{id}")
    public StudentEntity getStudentById(@PathVariable("id") Long id){
        StudentEntity studentEntity = studentMapper.findById(id);
        return studentEntity;
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@RequestBody StudentEntity  studentEntity){
       return studentService.saveStudent(studentEntity);
    }

    @GetMapping("/findAllCourseOfStudent")
    public StudentCoursePojo getAllCourseOfStudent(@RequestParam("studentId") int studentId) throws Exception {

//       List<StudentCoursePojo> studentCoursePojo = studentMapper.findAllCourseOfStudent(studentId);
      StudentCoursePojo studentCoursePojo = studentMapper.findAllCourseByStudentId(studentId);
        throw  new Exception("hello");

//        return studentCoursePojo;
//        return studentCoursePojo;
    }

    @GetMapping("/findAllTeacherAndStudentByCourseId")
    public Object findAllStudentAndCourseByCourseId(@RequestParam("courseId") int courseId, @RequestParam("courseId2") int courseId2) throws Exception {

//        List<Object> o = studentMapper.findAllStudentAndCourseByCourseId(courseId);
        StudentTeacherPojo studentTeacherPojo = studentMapper.findAllStudentAndCourseByCourseId(courseId, courseId2);
        return studentMapper.findAllStudentAndCourseByCourseId(courseId,courseId2);
    }

    @GetMapping("/throwingCourse")
    public Object findAllStudentAndCourseByCourseId()  {

        return new CourseEntity( "hello", "hsdl");
    }


}
