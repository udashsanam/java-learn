package com.learn.mybatis.controller;

import com.learn.mybatis.entity.*;
import com.learn.mybatis.mapper.StudentMapper;
import com.learn.mybatis.pojo.StudentCoursePojo;
import com.learn.mybatis.pojo.StudentTeacherPojo;
import com.learn.mybatis.repository.*;
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

        for (int i = 0; i < 10; i++) {
            CourseEntity courseEntity  = new CourseEntity();

            courseEntity.setCourseName( " course " + i);
            courseEntity.setCourseCode("course code " + i);

            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setAddress("student address " + i);
            studentEntity.setName("student name " + i);
            studentEntity.setRollNo(i);

            try{
                studentRepository.save(studentEntity);
                courseRepository.save(courseEntity);
            } catch (Exception ex){
                System.out.println("error saving the student");
            }

        }

        List<StudentEntity> studentEntities = studentRepository.findAll();
        List<CourseEntity> courseEntityList = courseRepository.findAll();

        for (StudentEntity studentEntity :
                studentEntities) {
            for (int i = 0; i < studentEntities.size(); i++) {
                for (int j = 0; j < i; j++) {

                    CourseStudentEntity courseStudentEntity = new CourseStudentEntity();
                    courseStudentEntity.setCourseEntity(courseEntityList.get(j));
                    courseStudentEntity.setStudentEntity(studentEntity);

                    try {
                        courseStudentRepository.save(courseStudentEntity);
                    } catch (Exception ex){
                        System.out.println("save student course");
                    }
                }
            }

        }


        return "saved student and course";

    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(){

        for (int i = 0; i < 10; i++) {

            TeacherEntity teacherEntity = new TeacherEntity();
            teacherEntity.setAddress("teacher address " + i);
            teacherEntity.setName("teacher name " + i);
            try {
                teacherRepository.save(teacherEntity);
            } catch (Exception ex){
                System.out.println("saving teacher");
            }

        }

        List<TeacherEntity> teacherEntities = teacherRepository.findAll();
        List<CourseEntity> courseEntityList = courseRepository.findAll();
        for (TeacherEntity teacherEntity :
                teacherEntities) {
            for (int i = 0; i < teacherEntities.size(); i++) {
                for (int j = 0; j < i; j++) {
                    TeacherCourseEntity courseEntity = new TeacherCourseEntity();
                    courseEntity.setTeacherEntity(teacherEntity);
                    courseEntity.setCourseEntity(courseEntityList.get(j));

                    try{

                    } catch (Exception ex){
                        System.out.println("Error saving the teacher course");
                    }
                }
            }
        }


        return "save teacher course";

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

        studentMapper.insertStudent(studentEntity.getName(), studentEntity.getRollNo(), studentEntity.getAddress());


        return "Successfully  install";


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
