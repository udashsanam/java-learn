package com.learn.mybatis.service;

import com.learn.mybatis.entity.CourseEntity;
import com.learn.mybatis.entity.CourseStudentEntity;
import com.learn.mybatis.entity.StudentEntity;
import com.learn.mybatis.repository.CourseRepository;
import com.learn.mybatis.repository.CourseStudentRepository;
import com.learn.mybatis.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    private final CourseStudentRepository courseStudentRepository;

    @Override
    public String saveAllStudent() {
        boolean isSucess = false;
        boolean isCourseSaved = saveCourseAndStudentEntity();
        if(isCourseSaved){
             isSucess = saveStudentCourse();
        }
        if(isSucess){
            return "Successful";
        }
        return "Unsuccessful";
    }

    private boolean saveCourseAndStudentEntity(){
        for (int i = 0; i < 10; i++) {
            CourseEntity courseEntity = new CourseEntity();
            courseEntity.setCourseName(" course " + i);
            courseEntity.setCourseCode("course code " + i);

            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setAddress("student address " + i);
            studentEntity.setName("student name " + i);
            studentEntity.setRollNo(i);
            try {
                studentRepository.save(studentEntity);
                courseRepository.save(courseEntity);
            } catch (Exception ex) {
                System.out.println("error saving the student");
            }
        }

        return true;
    }

    private boolean saveStudentCourse(){
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
                    } catch (Exception ex) {
                        System.out.println("save student course");
                    }
                }
            }
        }

        return true;
    }


    }

